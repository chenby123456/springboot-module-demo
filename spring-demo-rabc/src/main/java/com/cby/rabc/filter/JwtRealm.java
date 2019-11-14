package com.cby.rabc.filter;

import cn.hutool.core.util.StrUtil;
import com.cby.constant.ErrorCode;
import com.cby.constant.LoginConstant;
import com.cby.constant.LogoutTypeEnums;
import com.cby.exception.AuthcException;
import com.cby.exception.BusinessException;
import com.cby.model.UserInfo;
import com.cby.service.IPermissionsInfoService;
import com.cby.service.IUserInfoService;
import com.cby.utils.JwtUtil;
import com.cby.utils.RedisClient;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class JwtRealm extends AuthorizingRealm {

    @Autowired
    @Lazy  //延迟加载bean，否则会导致该service事务失效  https://segmentfault.com/a/1190000018778645?utm_source=tag-newest
    private IUserInfoService userInfoService;
    @Autowired
    private RedisClient redisClient;
    @Autowired
    private IPermissionsInfoService iPermissionsInfoService;

    private static Logger log = LoggerFactory.getLogger(JwtRealm.class);

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 校验权限的时候调用该方法，checkRole
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        Integer userId = (Integer) principalCollection.getPrimaryPrincipal();
        List<String> permission = iPermissionsInfoService.getPermissionByUserId(userId);
        simpleAuthorizationInfo.addStringPermissions(permission);
        return simpleAuthorizationInfo;
    }

    /**
     * 验证用户名和密码正确性
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getCredentials();
        if (StrUtil.isBlank(token)) {
            // 此情况属于前端错误，请求头未带TOKEN标识
            log.info("令牌为空~");
            throw new AuthcException("未认证，令牌不存在！");
        }
        String username = JwtUtil.getUsername(token);
        UserInfo userInfo = userInfoService.finByUserName(username);
        boolean isExpired = JwtUtil.isTokenExpired(token);
        if(isExpired){
            // Token 缓存存在则说明用户一直在操作,则颁发新令牌(新令牌可能为刚颁发的)
            if (redisClient.hasKey(LoginConstant.tokenExists(userInfo.getId()))) {
                token = userInfoService.refreshToken(token, userInfo.getId());
            } else {
                // 登出该用户，清理缓存
                try{
                    userInfoService.clearAuthCache(userInfo.getId(),null, LogoutTypeEnums.CACHE_TIME_OUT);
                }catch (Exception e){
                    throw new BusinessException(ErrorCode.SYSTEM_INNER_EXCEPTION.getMsg(), ErrorCode.SYSTEM_INNER_EXCEPTION.getCode());
                }
                log.info("[ id={} userName={}] 令牌超时失效,请重新登录！", userInfo.getId(), username);
                throw new AuthcException("登录信息已超时,请重新登录！");
            }
        }else{

        }
        if(userInfo == null){
            throw new BusinessException(ErrorCode.USER_UN_ENABLE.getMsg(),ErrorCode.USER_UN_ENABLE.getCode());
        }
        boolean result = JwtUtil.verify(token, userInfo.getUserName(), userInfo.getPassWord());
        if(!result){
            throw new AuthcException("认证失败，用户名或密码错误！");
        }
        return new SimpleAuthenticationInfo(userInfo.getId(),token,getName());

    }
}

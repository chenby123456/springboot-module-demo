package com.cby.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.cby.constant.ErrorCode;
import com.cby.constant.LoginConstant;
import com.cby.constant.LogoutTypeEnums;
import com.cby.dao.mapper.UserInfoMapper;
import com.cby.exception.BusinessException;
import com.cby.model.UserInfo;
import com.cby.service.IPermissionsInfoService;
import com.cby.service.IRoleInfoService;
import com.cby.service.IUserInfoService;
import com.cby.utils.JwtUtil;
import com.cby.utils.RedisClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @ClassName:
 * @Description:
 * @Author: bingyang.chen
 * @Date: 2019/10/22
 * @Version: 1.0.0
 */
@Service
public class UserInfoServiceImpl implements IUserInfoService {

    private final static Logger log = LoggerFactory.getLogger(UserInfoServiceImpl.class);
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private IRoleInfoService roleInfoService;
    @Autowired
    private IPermissionsInfoService permissionsInfoService;
    @Autowired
    RedisClient redisClient;

    @Override
    public UserInfo findById(Integer userId) {
        return userInfoMapper.findById(userId);
    }

    @Override
    public UserInfo finByUserName(String username) {
        return userInfoMapper.finByUserName(username);
    }

    @Override
    public String doLogin(String userName, String passWord) {
        UserInfo userInfo = userInfoMapper.finByUserName(userName);
        if(userInfo == null){
            throw new BusinessException(ErrorCode.USER_UN_ENABLE.getMsg(),ErrorCode.USER_UN_ENABLE.getCode());
        }
        if(!passWord.equals(userInfo.getPassWord())){
            log.info("用户[{}]密码错误！",userInfo.getUserName());
            throw new BusinessException(ErrorCode.ERROR_ACCOUNT.getMsg(),ErrorCode.ERROR_ACCOUNT.getCode());
        }
        List<String> permissions = permissionsInfoService.getPermissionByUserId(userInfo.getId());
        String token = JwtUtil.sign(userName,passWord,permissions);
        redisClient.set(LoginConstant.tokenUid(userInfo.getId()),token,LoginConstant.TOKEN_EXPIRE_SECOND);
        return token;
    }

    @Override
    public String refreshToken(String token, Integer userId) {
        UserInfo userInfo = userInfoMapper.findById(userId);
        String latelyToken = latelyToken(userId);
        if (StrUtil.isNotBlank(latelyToken)) {
            return latelyToken;
        } else {
            //得到用户权限
            List<String> permissions = permissionsInfoService.getPermissionByUserId(userId);
            latelyToken = createToken(userInfo,permissions);
        }
        return latelyToken;
    }

    public String createToken(UserInfo userInfo,List<String> permissions) {
        String token = JwtUtil.sign(userInfo.getUserName(),userInfo.getPassWord(), permissions);
        // 标识用户在操作ii
        redisClient.set(LoginConstant.tokenExists(userInfo.getId()), DateTime.now(), LoginConstant.TOKEN_CACHE_SECOND);
        // 新创建的Token绑定用户用来优化Token刷新机制(复用刷新Token)
        redisClient.zAdd(LoginConstant.tokenUid(userInfo.getId()), token, System.currentTimeMillis());
        return token;
    }

    /**
     * @Description: 用户最近颁发的有效token
     * @MethodName: latelyToken
     * @Params: [uid]
     * @Return: java.lang.String
     */
    public String latelyToken(Integer uid) {
        Set<Object> set= redisClient.zRevRange(LoginConstant.tokenUid(uid), 0, 0);
        if (Objects.isNull(set)||set.isEmpty()) {
            return null;
        }
        // 倒叙时获取第一个数据即为最新颁发的Token
        String latelyToken = set.iterator().next().toString();
        boolean expired = JwtUtil.isTokenExpired(latelyToken);
        boolean shouldRefresh = this.shouldRefresh(latelyToken);
        // 最近的Token是刚刷新、创建的Token则直接返回
        if (!expired && !shouldRefresh) {
            return latelyToken;
        }
        return null;
    }

    public static boolean shouldRefresh(String token) {
        LocalDateTime expireTime = LocalDateTime.ofInstant(getExpiresAt(token).toInstant(), ZoneId.systemDefault());
        return LocalDateTime.now().plusSeconds(LoginConstant.TOKEN_REFRESH_SECOND).isAfter(expireTime);
    }

    public static Date getExpiresAt(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getExpiresAt();
        } catch (JWTDecodeException e) {
            throw new RuntimeException("token 解析失败");
        }
    }

    /**
     * @Description: 登出指定用户
     * @MethodName: clearAuthCache
     * @Param: [id, o, doLogin]
     * @Return: void
     * @Date: 2019/8/24 0024 下午 2:12
     */
    @Override
    public void clearAuthCache(Integer userId, Integer adminId, LogoutTypeEnums logoutType) {
        UserInfo userInfo = userInfoMapper.findById(userId);
        if (null != userInfo) {
            String userName = userInfo.getUserName();
            switch (logoutType) {
                case DO_LOGIN: {
                    doLogin(userName,userInfo.getPassWord());
                    break;
                }
                case DO_LOGOUT: {
                    doLogout(userId, userName);
                    break;
                }
                case DO_LOGOUT_PASSIVE: {
                    doLogoutPassive(userId, adminId, userName);
                    break;
                }
                default: {
                    log.info("[{}] 用户[id={} userName={} ]  {}", new Date(), userId, userName, logoutType.getDesc());
                }
            }
            redisClient.del(LoginConstant.AUTH_CACHE_AUTH + userId);
            redisClient.del(LoginConstant.AUTH_CACHE_USER + userId);
            redisClient.del(LoginConstant.AUTH_CACHE_ROLE + userId);
            redisClient.del(LoginConstant.AUTH_CACHE_RESOURCE + userId);
            Set<String> keys = redisClient.keys(LoginConstant.tokenUid(userId));
            redisClient.del(keys);
        }else {
            log.warn("用户[id={} logout={}]  无效登出操作 ",  userId, logoutType.getDesc());
        }
    }

    @Override
    public void doLogout(Integer userId, String userName) {
        Set<Object> set = redisClient.zRange(LoginConstant.tokenUid(userId), 0, 0);
        if (Objects.nonNull(set)&&!set.isEmpty()) {
            // 只记录已登录用户的退出
            log.info("用户[id={} userName={} loginTime={}] 用户登出成功！", userId, userName, new Date());
        }
    }

    @Override
    public void doLogoutPassive(Integer userId,Integer adminId, String userName) {
        Set<Object> set = redisClient.zRange(LoginConstant.tokenUid(userId), 0, 0);
        if (Objects.nonNull(set)&&!set.isEmpty()) {
            // 被踢出用户已登录时
            log.info("用户[id={} userName={} loginTime={}] 被管理员[id={}] 踢出", userId, userName, new Date(), adminId);
        } else {
            // 被踢出用户未登录时
            log.info("用户[id={} userName={}] 被管理员[id={}] 踢出", userId, userName, adminId);
        }
    }
}

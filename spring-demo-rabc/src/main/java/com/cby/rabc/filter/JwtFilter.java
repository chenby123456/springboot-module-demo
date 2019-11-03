package com.cby.rabc.filter;

import cn.hutool.http.HttpStatus;
import cn.hutool.json.JSONUtil;
import com.cby.constant.ErrorCode;
import com.cby.constant.LoginConstant;
import com.cby.utils.IpUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName:
 * @Description:
 * @Author: bingyang.chen
 * @Date: 2019/10/22
 * @Version: 1.0.0
 */
public class JwtFilter extends BasicHttpAuthenticationFilter{

    private static Logger log = LoggerFactory.getLogger(JwtFilter.class);
    public JwtFilter() {
        this.setLoginUrl("/login/doLogin");
    }

    /**
     * preHandle  == true  ----> doFilter ----> postHandle
     * preHandle  == false  ----> postHandle
     * preHandle{}= onPreHandle---->isAccessAllowed||onAccessDenied
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        fillCorsHeader(httpServletRequest, WebUtils.toHttp(response));
        //对于OPTION请求做拦截，不做token校验
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            return false;
        }
        return super.preHandle(request, response);
    }

    @Override
    protected void postHandle(ServletRequest request, ServletResponse response) {
    }

    /**
     * executeLogin ----> realm ---> matcher --->onLogin
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        // 不拦截登录请求
        if (isLoginAttempt(request, response)) {
            return true;
        }
        // 不拦截二次请求
        if (Boolean.valueOf(getAlreadyFilteredAttributeName())) {
            return true;
        }
        boolean allowed = false;
        try {
            allowed = executeLogin(request, response);
        } catch (IllegalStateException e) {
            log.info("无效Token请求>>>>>>>>> {}", WebUtils.toHttp(request).getRequestURL());
        } catch (Exception e) {
            log.error("token 登录异常", e);
        }
        return allowed || super.isPermissive(mappedValue);
    }

    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        return this.pathsMatch(this.getLoginUrl(), request);
    }

    /**
     * @Description: 直接创建Token,认证校验统一交给realm
     * @MethodName: createToken
     * @Params: [servletRequest, servletResponse]
     * @Return: org.apache.shiro.authc.AuthenticationToken
     */
    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) {
        String jwtToken = getAuthzHeader(servletRequest);
        return new JwtToken(IpUtil.getIpFromRequest(WebUtils.toHttp(servletRequest)), jwtToken);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) {
        return false;
    }

    /**
     * shiro登录认证成功的处理方法
     */
    @Override
    protected boolean onLoginSuccess(AuthenticationToken authenticationToken, Subject subject, ServletRequest request, ServletResponse response) {
        HttpServletResponse httpResponse = WebUtils.toHttp(response);
        String token = ((JwtToken) authenticationToken).getToken();
        httpResponse.setHeader(AUTHORIZATION_HEADER, token);
        return true;
    }

    /**
     * shiro登录认证失败的处理方法
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        log.info("请求失败,请求IP={},异常={}", IpUtil.getIpFromRequest(WebUtils.toHttp(request)), e.getLocalizedMessage());
        HttpServletResponse httpResponse = WebUtils.toHttp(response);
        httpResponse.setCharacterEncoding("UTF-8");
        httpResponse.setContentType("application/json;charset=UTF-8");
        httpResponse.setStatus(HttpStatus.HTTP_NOT_AUTHORITATIVE);
        try (PrintWriter out = httpResponse.getWriter()) {
            Map<String, Object> map = new HashMap<>(2, 1);
            map.put("code", ErrorCode.UN_AUTHC.getCode());
            map.put("msg", e.getMessage());
            out.print(JSONUtil.parseObj(map));
            out.flush();
        } catch (IOException ioe) {
            log.error("", ioe);
        }
        return false;
    }

    /**
     * @Description:
     * @MethodName: fillCorsHeader
     * @Params: [request, response]
     * @Return: void
     */
    private void fillCorsHeader(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", request.getMethod());
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Expose-Headers", LoginConstant.TOKEN_HEADER);
        response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
    }
}

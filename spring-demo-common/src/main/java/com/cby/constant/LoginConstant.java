package com.cby.constant;


import com.cby.utils.ShiroMd5Util;

import java.util.regex.Pattern;

/**
 * @ClassName: LoginConstant
 * @Description: 登陆相关常量
 * @Date: 2019/8/25 15:00
 * @Version: 1.0.0
 */
public interface LoginConstant {

    String X_FORWARDED_FOR = "x-forwarded-for";
    String UNKNOWN = "unknown";
    String WL_PROXY_CLIENT_IP = "WL-Proxy-Client-IP";
    String HTTP_CLIENT_IP = "HTTP_CLIENT_IP";
    String HTTP_X_FORWARDED_FOR = "HTTP_X_FORWARDED_FOR";
    String LOCAL_IP = "127.0.0.1";
    String IP_255 = "(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";
    Pattern PATTERN = Pattern.compile("^(?:" + IP_255 + "\\.){3}" + IP_255 + "$");
    String IP = "10.0.0.0";
    String IP1 = "10.255.255.255";
    String IP2 = "172.16.0.0";
    String IP3 = "172.31.255.255";
    String IP4 = "192.168.0.0";
    String IP5 = "192.168.255.255";

    String TOKEN_HEADER = "Authorization";
    String HM_TOKEN_PREFIX = "token:uid:";
    String AUTH_CACHE_AUTH = "auth::authUser_";
    String AUTH_CACHE_USER = "auth::userVo_";
    String AUTH_CACHE_ROLE = "auth::authRole_";
    String AUTH_CACHE_RESOURCE = "auth::authResource_";

    /**
     * 初始密码
     */
    String INITIAL_PASSWORD = "123456";
    /**
     * token 加密秘钥
     */
    String SECRET = ShiroMd5Util.md5("Chenby", "Chenxc");

    /**
     * token 颁发者
     */
    String ISSUER = "chenby";

    /**
     * Token 有效时长
     */
    int TOKEN_EXPIRE_SECOND = 2 * 60 * 60;

    /**
     * Token 操作缓存时长，每次操作均会重置该时长，标志用户在一直操作
     */
    int TOKEN_CACHE_SECOND = 2 * 60 * 60;

    /**
     * token 常用参数名称
     */
    String PARAM_LOGIN_NAME = "loginName";

    String PARAM_USER_NAME = "userName";

    String PARAM_USER_ID = "userId";

    String PERMISSIONS = "roles";

    /**
     * token 刷新间隔,[TOKEN_EXPIRE_SECOND - TOKEN_REFRESH_SECOND,TOKEN_EXPIRE_SECOND]之间会刷新Token
     */
    long TOKEN_REFRESH_SECOND = 3600;

    /**
     * 验证码KEY前缀
     */
    String CAPTCHA_KEY_PREFIX = "captcha:key:";

    /**
     * 验证码有效期,单位秒
     */
    Integer CAPTCHA_AGE = 600;

    /**
     * 常用魔法值
     */
    String STR_0 = "0";

    /**
     * 常用魔法值
     */
    String STR_1 = "1";

    /**
     * 常用魔法值
     */
    String STR_2 = "2";

    /**
     * 验证码验证成功之后等待有效期,单位秒
     */
    Integer CAPTCHA_SUCCESS_AGE = 60;

    /**
     * 用于随机选的字符和数字,去除容易混乱的字符,1--i,l-1,0-o
     */
    String BASE_CHAR_NUMBER_SIMPLE = "23456789abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ";


    /**
     * @Description: 用户ID记录令牌缓存Key
     * @MethodName: tokenUid
     * @Param: [uid]
     * @Return: java.lang.String
     * @Date: 2019/8/24 0024 下午 2:55
     */
    static String tokenUid(Integer uid) {
        return HM_TOKEN_PREFIX + uid;
    }

    /**
     * @Description: 标志用户在操作
     * @MethodName: tokenExists
     * @Param: [uid]
     * @Return: java.lang.String
     * @Date: 2019/8/24 0024 下午 4:55
     */
    static String tokenExists(Integer uid) {
        return HM_TOKEN_PREFIX + uid + ":exists";
    }
}

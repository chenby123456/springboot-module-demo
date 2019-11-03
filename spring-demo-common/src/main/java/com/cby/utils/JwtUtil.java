package com.cby.utils;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.cby.constant.LoginConstant;
import com.sun.javafx.binding.LongConstant;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class JwtUtil {

    @Autowired
    private RedisClient redisClient;

    /**
     * 验证token加密串是否正确
     * @param token
     * @param userName
     * @param password
     * @return
     */
    public static boolean verify(String token,String userName,String password){
        try{
            //根据密码生成校验器
            Algorithm algorithm = Algorithm.HMAC256(LoginConstant.SECRET);
            JWTVerifier jwtVerifier = JWT.require(algorithm).withClaim(LoginConstant.PARAM_USER_NAME, userName).build();
            jwtVerifier.verify(token);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     *
     * @return token中包含的用户名
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(LoginConstant.PARAM_USER_NAME).asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     *
     * @return token中包含的用户id
     */
    public static Long getUserId(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(LoginConstant.PARAM_USER_ID).asLong();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 生成签名,5min后过期
     * @param username 用户名
     * @return 加密的token
     */
    public static String sign(String username,String passWord,List<String> permissions) {
        Algorithm algorithm = Algorithm.HMAC256(LoginConstant.SECRET);
        // 附带username信息
        return JWT.create()
                .withClaim(LoginConstant.PARAM_USER_NAME, username)
                .withArrayClaim(LoginConstant.PERMISSIONS,permissions.toArray(new String[permissions.size()]))
                .withExpiresAt(DateUtil.offsetSecond(new Date(), LoginConstant.TOKEN_EXPIRE_SECOND))
                .sign(algorithm);

    }

    /**
     * token是否过期,true 为过期
     * @param token
     * @return
     */
    public static boolean isTokenExpired(String token){
        DecodedJWT decode = JWT.decode(token);
        return decode.getExpiresAt().before(new Date());
    }


}

package com.cby.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @ClassName: ShiroMd5Util
 * @Description: TODO
 * @Date: 2019/8/24 0024 上午 11:52
 * @Version: 1.0.0
 */
public class ShiroMd5Util {
    /**
      * @Description: Md5加密方法
      * @MethodName: md5
      * @Param: [content, salt]
      * @Return: java.lang.String
      * @Date: 2019/8/24 0024 上午 11:53
     */
    public static String md5(String content, String salt) {
        String algorithmName = "MD5";
        ByteSource saltSource = ByteSource.Util.bytes(salt);
        int hashIterations = 1024;
        SimpleHash hash = new SimpleHash(algorithmName, content, saltSource, hashIterations);
        return hash.toString();
    }
}

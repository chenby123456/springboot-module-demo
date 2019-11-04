package com.cby.utils;

import cn.hutool.core.util.StrUtil;
import com.cby.constant.LoginConstant;

import javax.servlet.http.HttpServletRequest;
import java.util.StringTokenizer;

/**
 * @ClassName: IpUtil
 * @Description:
 * @Date: 2019/8/25 14:56
 * @Version: 1.0.0
 */
public class IpUtil {
    public static String getIpFromRequest(HttpServletRequest request) {
        String ip;
        boolean found = false;
        if ((ip = request.getHeader(LoginConstant.X_FORWARDED_FOR)) != null) {
            StringTokenizer tokenizer = new StringTokenizer(ip, ",");
            while (tokenizer.hasMoreTokens()) {
                ip = tokenizer.nextToken().trim();
                if (isIPv4Valid(ip) && !isIPv4Private(ip)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                if (StrUtil.isBlank(ip) || LoginConstant.UNKNOWN.equalsIgnoreCase(ip)) {
                    ip = request.getHeader(LoginConstant.WL_PROXY_CLIENT_IP);
                }
                if (StrUtil.isBlank(ip) || LoginConstant.UNKNOWN.equalsIgnoreCase(ip)) {
                    ip = request.getHeader(LoginConstant.HTTP_CLIENT_IP);
                }
                if (StrUtil.isBlank(ip) || LoginConstant.UNKNOWN.equalsIgnoreCase(ip)) {
                    ip = request.getHeader(LoginConstant.HTTP_X_FORWARDED_FOR);
                }
            }
        }
        if (StrUtil.isBlank(ip) || LoginConstant.UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return "0:0:0:0:0:0:0:1".equals(ip) ? LoginConstant.LOCAL_IP : ip;
    }

    private static boolean isIPv4Valid(String ip) {
        return LoginConstant.PATTERN.matcher(ip).matches();
    }

    private static boolean isIPv4Private(String ip) {
        long longIp = ipV4ToLong(ip);
        return (longIp >= ipV4ToLong(LoginConstant.IP) && longIp <= ipV4ToLong(LoginConstant.IP1))
                || (longIp >= ipV4ToLong(LoginConstant.IP2) && longIp <= ipV4ToLong(LoginConstant.IP3))
                || longIp >= ipV4ToLong(LoginConstant.IP4) && longIp <= ipV4ToLong(LoginConstant.IP5);
    }

    private static long ipV4ToLong(String ip) {
        String[] octets = ip.split("\\.");
        return (Long.parseLong(octets[0]) << 24) + (Integer.parseInt(octets[1]) << 16
        )
                + (Integer.parseInt(octets[2]) << 8) + Integer.parseInt(octets[3]);
    }
}

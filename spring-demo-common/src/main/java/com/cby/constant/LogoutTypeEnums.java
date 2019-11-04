package com.cby.constant;

/**
 * @ClassName: LogoutTypeEnums
 * @Description: 登出类型
 * @Date: 2019/8/24 0024 下午 2:08
 * @Version: 1.0.0
 */
public enum LogoutTypeEnums {
    /**
     * 注销登陆信息枚举
     */
    DO_LOGIN("登陆时主动清除以往缓存信息"),
    DO_LOGOUT("主动登出导致退出"),
    DO_LOGOUT_PASSIVE("被动登出导致退出"),
    CACHE_TIME_OUT("缓存过期导致退出");

    private String desc;

    LogoutTypeEnums(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}

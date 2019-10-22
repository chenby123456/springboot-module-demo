package com.cby.exception;

/**
 * @ClassName:
 * @Description:
 * @Author: bingyang.chen
 * @Date: 2019/10/22
 * @Version: 1.0.0
 */
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = -6302251250243729700L;
    private String friendlyTips;
    private int code;
    private String about;

    public BusinessException(String friendlyTips, int code) {
        this.friendlyTips = friendlyTips;
        this.code = code;
    }

    public BusinessException(String friendlyTips, int code, String about) {
        this.friendlyTips = friendlyTips;
        this.code = code;
        this.about = about;
    }

    public BusinessException(String message, String friendlyTips, int code, String about) {
        super(message);
        this.friendlyTips = friendlyTips;
        this.code = code;
        this.about = about;
    }

    public BusinessException(String message, Throwable cause, String friendlyTips, int code, String about) {
        super(message, cause);
        this.friendlyTips = friendlyTips;
        this.code = code;
        this.about = about;
    }

    public BusinessException(Throwable cause, String friendlyTips, int code, String about) {
        super(cause);
        this.friendlyTips = friendlyTips;
        this.code = code;
        this.about = about;
    }

    public BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String friendlyTips, int code, String about) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.friendlyTips = friendlyTips;
        this.code = code;
        this.about = about;
    }

    public String getFriendlyTips() {
        return friendlyTips;
    }

    public void setFriendlyTips(String friendlyTips) {
        this.friendlyTips = friendlyTips;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}

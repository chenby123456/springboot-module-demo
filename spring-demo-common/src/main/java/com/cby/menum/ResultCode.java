package com.cby.menum;

public enum ResultCode {

    SUCCESS(200,"成功"),
    SERVER_ERROR(500,"服务器内部异常");

    private Integer code;
    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }


}

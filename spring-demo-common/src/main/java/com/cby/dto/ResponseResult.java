package com.cby.dto;


import com.cby.menum.ResultCode;

import java.io.Serializable;
import java.util.Objects;
public class ResponseResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private int code;
    private String msg;
    private T data;

    public ResponseResult(ResultCode resultCode, T data) {
        this.code = resultCode.code();
        this.msg = resultCode.message();
        this.data = data;
    }

    public static <T> ResponseResult<T> ok() {
        return (new ResponseResult()).setCode(1);
    }
    public static <T> ResponseResult<T> ok(T data) {
        return (new ResponseResult()).setCode(1).setData(data);
    }

    public static <T> ResponseResult<T> fail() {
        return (new ResponseResult()).setCode(0);
    }
    public static <T> ResponseResult<T> fail(String message) {
        return (new ResponseResult()).setCode(0).setMsg(message);
    }

    public static <T> ResponseResult<T> create(int code) {
        return new ResponseResult(code);
    }

    public ResponseResult(int code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public T getData() {
        return this.data;
    }


    public ResponseResult<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public ResponseResult<T> setData(T data) {
        this.data = data;
        return this;
    }


    public ResponseResult() {
    }

    public int getCode() {
        return code;
    }

    public ResponseResult<T> setCode(int code) {
        this.code = code;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResponseResult<?> that = (ResponseResult<?>) o;
        return code == that.code &&
                Objects.equals(msg, that.msg) &&
                Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {

        return Objects.hash(code, msg, data);
    }

    @Override
    public String toString() {
        return "ResponseResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}



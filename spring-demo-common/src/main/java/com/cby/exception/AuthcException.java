package com.cby.exception;

import com.cby.constant.ErrorCode;
import org.apache.shiro.authc.AuthenticationException;

/**
 * @ClassName: AuthcException
 * @Description:
 * @Date: 2019/8/25 15:44
 * @Version: 1.0.0
 */
public class AuthcException extends AuthenticationException {
    private static final long serialVersionUID = -7576016034567854198L;
    private static final String MESSAGE = ErrorCode.UN_AUTHC.getMsg();
    private Integer code = ErrorCode.UN_AUTHC.getCode();

    public AuthcException() {
        super(MESSAGE);
    }

    public AuthcException(String message) {
        super(message);
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}

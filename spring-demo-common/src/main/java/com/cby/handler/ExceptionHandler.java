package com.cby.handler;

import com.cby.dto.ResponseResult;
import com.cby.exception.BusinessException;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
/**
 * @ClassName:
 * @Description:
 * @Author: bingyang.chen
 * @Date: 2019/10/22
 * @Version: 1.0.0
 */
@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = BusinessException.class)
    public @ResponseBody ResponseResult businessExceptionHandler(BusinessException businessException){
        ResponseResult result = new ResponseResult();
        result.setCode(businessException.getCode());
        result.setMsg(businessException.getMessage());
        return result;
    }

}

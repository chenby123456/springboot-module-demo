package com.cby.controller;

import com.cby.annotation.ResponseResultAnnotation;
import com.cby.dto.ResponseResult;
import com.cby.exception.BusinessException;
import com.cby.model.UserInfo;
import com.cby.service.ITestService;
import com.cby.service.IUserInfoService;
import com.cby.utils.ReflectUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "demo")
public class WebDemo {

    @Autowired
    private ITestService testService;
    @Autowired
    private IUserInfoService iUserInfoService;

    @RequestMapping(value = "findById")
    @ResponseResultAnnotation
    public UserInfo getUserInfo(Integer userId){
        try{
            UserInfo userInfo = iUserInfoService.findById(userId);
            int i = 1/0;
            return userInfo;
        }catch (Exception e){
            throw e;
        }
    }

    @RequiresPermissions(value = {"USER_MANAGE","USER_EDIT"},logical = Logical.OR)
    @RequestMapping(value = "test")
    public String test() throws InterruptedException {
        List<UserInfo> userInfo1 = testService.getUserInfo();
        return userInfo1.get(0).getUserName();
    }

    @RequestMapping(value = "testAnnotation")
    public String testAnnotation() throws InterruptedException {
        UserInfo userInfo = new UserInfo();
        ReflectUtils.test(UserInfo.class);
        return "111";
    }

    @RequestMapping(value = "testExceptionHandler")
    public @ResponseBody ResponseResult<String> testExceptionHandler() throws InterruptedException {
        if(false){
            throw new BusinessException("错误",500,"系统错误");
        }
        return ResponseResult.ok();
    }
}

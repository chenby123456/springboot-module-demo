package com.cby.controller;

import com.cby.model.UserInfo;
import com.cby.service.ITestService;
import com.cby.utils.ReflectUtils;
import com.cby.utils.TestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "demo")
public class WebDemo {

    @Autowired
    private ITestService testService;

    @RequestMapping(value = "test")
    public String test() throws InterruptedException {
        String sayHello = testService.sayHello();
        String hello = TestUtils.sayHello();
        List<UserInfo> userInfo1 = testService.getUserInfo();
        return sayHello+"=="+hello;
    }

    @RequestMapping(value = "testAnnotation")
    public String testAnnotation() throws InterruptedException {
        UserInfo userInfo = new UserInfo();
        ReflectUtils.test(UserInfo.class);
        return userInfo.getUserName();
    }
}

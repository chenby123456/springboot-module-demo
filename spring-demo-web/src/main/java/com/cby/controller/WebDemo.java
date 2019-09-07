package com.cby.controller;

import com.cby.service.ITestService;
import com.cby.user.UserInfo;
import com.cby.utils.TestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "demo")
public class WebDemo {

    @Autowired
    private ITestService testService;


    @RequestMapping(value = "test")
    public String test() throws InterruptedException {
        //TestUtils.test();
        UserInfo userInfo = new UserInfo();
        String sayHello = testService.sayHello();
        String hello = TestUtils.sayHello();
        return sayHello+"=="+hello;
    }

}

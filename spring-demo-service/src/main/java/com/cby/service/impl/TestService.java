package com.cby.service.impl;


import com.cby.dao.mapper.UserInfoMapper;
import com.cby.model.UserInfo;
import com.cby.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService implements ITestService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    public String sayHello() {
        return "Hello Baby!";
    }

    public List<UserInfo> getUserInfo(){
        return userInfoMapper.getUser();
    }
}

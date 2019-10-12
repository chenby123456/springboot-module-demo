package com.cby.service.impl;


import com.cby.dao.mapper.UserInfoMapper;
import com.cby.model.UserInfo;
import com.cby.service.ITestService;
import com.cby.utils.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService implements ITestService {

    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private RedisClient redisUtils;

    @Override
    public String sayHello() {
        return "Hello Baby!";
    }

    @Override
    public List<UserInfo> getUserInfo(){
        String name = redisUtils.get("name");
        return userInfoMapper.getUser();
    }
}

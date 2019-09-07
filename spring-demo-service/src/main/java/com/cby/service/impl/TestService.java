package com.cby.service.impl;


import com.cby.service.ITestService;
import org.springframework.stereotype.Service;

@Service
public class TestService implements ITestService {

    public String sayHello() {
        return "Hello Baby!";
    }
}

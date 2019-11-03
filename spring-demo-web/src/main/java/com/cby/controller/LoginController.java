package com.cby.controller;


import com.cby.dto.ResponseResult;
import com.cby.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "login")
public class LoginController {

    @Autowired
    private IUserInfoService userInfoService;

    @RequestMapping(value = "doLogin")
    public @ResponseBody ResponseResult<String> doLogin(String userName,String passWord){
        String token = userInfoService.doLogin(userName,passWord);
        return ResponseResult.ok(token);
    }



    @RequestMapping(value = "logout")
    public @ResponseBody ResponseResult<String> logout(String userName,String passWord){
        String token = userInfoService.doLogin(userName,passWord);
        return ResponseResult.ok(token);
    }
}

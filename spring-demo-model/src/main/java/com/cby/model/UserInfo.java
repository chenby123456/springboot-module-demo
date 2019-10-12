package com.cby.model;

import com.cby.annotation.MineAnnotation;

public class UserInfo {
    @MineAnnotation(value = "用户ID")
    private Integer id;
    @MineAnnotation(value = "用户姓名")
    private String userName;
    @MineAnnotation(value = "用户密码")
    private String passWord;
    @MineAnnotation(value = "用户手机号码")
    private String mobile;
    @MineAnnotation(value = "用户邮箱地址")
    private String email;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

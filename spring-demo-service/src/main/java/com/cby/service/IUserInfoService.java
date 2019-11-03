package com.cby.service;

import com.cby.constant.LogoutTypeEnums;
import com.cby.model.UserInfo;

/**
 * @ClassName:
 * @Description:
 * @Author: bingyang.chen
 * @Date: 2019/10/22
 * @Version: 1.0.0
 */
public interface IUserInfoService {
    public UserInfo findById(Integer userId);

    public UserInfo finByUserName(String username);

    public String doLogin(String userName, String passWord);

    public String refreshToken(String token, Integer id);

    public void clearAuthCache(Integer id,Integer adminId, LogoutTypeEnums logoutType);

    public void doLogout(Integer userId, String userName) ;

    public void doLogoutPassive(Integer userId,Integer adminId, String userName) ;
}

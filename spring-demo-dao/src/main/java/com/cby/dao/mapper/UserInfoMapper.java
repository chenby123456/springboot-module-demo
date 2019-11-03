package com.cby.dao.mapper;

import com.cby.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component("userInfoMapper")
public interface UserInfoMapper {
    public List<UserInfo> getUser();

    public UserInfo findById(Integer userId);

    public UserInfo finByUserName(String username);
}

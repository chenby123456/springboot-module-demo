package com.cby.dao.mapper;

import com.cby.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserInfoMapper {
    public List<UserInfo> getUser();
}

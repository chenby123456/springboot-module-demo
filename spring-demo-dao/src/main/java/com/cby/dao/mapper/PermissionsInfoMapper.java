package com.cby.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component("permissionsInfoMapper")
public interface PermissionsInfoMapper {
    List<String> getPermissionByUserId(Integer userId);
}

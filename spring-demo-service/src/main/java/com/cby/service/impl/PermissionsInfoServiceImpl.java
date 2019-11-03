package com.cby.service.impl;

import com.cby.dao.mapper.PermissionsInfoMapper;
import com.cby.service.IPermissionsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionsInfoServiceImpl implements IPermissionsInfoService {
    @Autowired
    private PermissionsInfoMapper permissionsInfoMapper;
    @Override
    public List<String> getPermissionByUserId(Integer userId) {
        return permissionsInfoMapper.getPermissionByUserId(userId);
    }
}

package com.cby.service;

import java.util.List;

public interface IPermissionsInfoService {

    public List<String> getPermissionByUserId(Integer userId);
}

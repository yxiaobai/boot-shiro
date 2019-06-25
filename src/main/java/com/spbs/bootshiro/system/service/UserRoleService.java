package com.spbs.bootshiro.system.service;

import com.spbs.bootshiro.common.service.IService;
import com.spbs.bootshiro.system.domain.TUserRole;

public interface UserRoleService extends IService<TUserRole> {
    void deleteUserRolesByUserId(String ids);
}

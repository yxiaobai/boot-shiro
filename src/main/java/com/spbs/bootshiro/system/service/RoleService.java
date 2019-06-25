package com.spbs.bootshiro.system.service;

import com.spbs.bootshiro.common.service.IService;
import com.spbs.bootshiro.system.domain.TRole;

import java.util.List;

public interface RoleService extends IService<TRole> {
    List<TRole> findUserRole(String userName);
}

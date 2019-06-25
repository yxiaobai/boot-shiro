package com.spbs.bootshiro.system.service.impl;

import com.spbs.bootshiro.common.service.impl.BaseService;
import com.spbs.bootshiro.system.dao.TRoleMapper;
import com.spbs.bootshiro.system.domain.TRole;
import com.spbs.bootshiro.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("roleService")
public class RoleServiceImpl extends BaseService<TRole> implements RoleService {

    @Autowired
    private TRoleMapper roleMapper;

    @Override
    public List<TRole> findUserRole(String userName) {
        return this.roleMapper.findUserRole(userName);
    }

}

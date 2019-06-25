package com.spbs.bootshiro.system.service.impl;

import com.spbs.bootshiro.common.service.impl.BaseService;
import com.spbs.bootshiro.system.domain.TUserRole;
import com.spbs.bootshiro.system.service.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service("userRoleService")
public class UserRoleServiceImpl extends BaseService<TUserRole> implements UserRoleService {


    @Override
    public void deleteUserRolesByUserId(String ids) {
        List<String> list = Arrays.asList(ids.split(","));
        this.batchDelete(list, "userId", TUserRole.class);
    }
}

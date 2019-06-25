package com.spbs.bootshiro.system.dao;

import com.spbs.bootshiro.common.config.MyMapper;
import com.spbs.bootshiro.system.domain.TRole;

import java.util.List;

public interface TRoleMapper extends MyMapper<TRole> {

    List<TRole> findUserRole(String userName);

}
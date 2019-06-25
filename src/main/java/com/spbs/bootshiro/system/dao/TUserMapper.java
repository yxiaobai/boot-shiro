package com.spbs.bootshiro.system.dao;

import com.spbs.bootshiro.common.config.MyMapper;
import com.spbs.bootshiro.system.domain.TUser;

import java.util.List;

import com.spbs.bootshiro.system.domain.UserWithRole;
import org.apache.ibatis.annotations.Param;

public interface TUserMapper extends MyMapper<TUser> {
    TUser findUserProfile(TUser user);

    List<UserWithRole> findUserWithRole(Integer userId);
}
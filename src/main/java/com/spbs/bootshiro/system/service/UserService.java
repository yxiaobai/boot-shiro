package com.spbs.bootshiro.system.service;

import com.spbs.bootshiro.common.domain.QueryRequest;
import com.spbs.bootshiro.common.service.IService;
import com.spbs.bootshiro.system.domain.TUser;
import com.spbs.bootshiro.system.domain.UserWithRole;

import java.util.List;

public interface UserService extends IService<TUser> {
    TUser findByName(String userName);

    void updateLoginTime(String userName);

    UserWithRole findById(Integer userId);

    List<TUser> findUserWithDept(TUser user, QueryRequest request);

    void registUser(TUser user);

    void addUser(TUser user, Integer[] roles);

    void updateUser(TUser user, Integer[] roles);

    void deleteUsers(String ids);

    void updatePassword(String password);

    TUser findUserProfile(TUser user);

    void updateUserProfile(TUser user);
}

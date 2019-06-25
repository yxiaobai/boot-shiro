package com.spbs.bootshiro.system.service.impl;

import com.spbs.bootshiro.common.domain.QueryRequest;
import com.spbs.bootshiro.common.service.impl.BaseService;
import com.spbs.bootshiro.common.utils.MD5Utils;
import com.spbs.bootshiro.system.dao.TUserMapper;
import com.spbs.bootshiro.system.dao.TUserRoleMapper;
import com.spbs.bootshiro.system.domain.TUser;
import com.spbs.bootshiro.system.domain.TUserRole;
import com.spbs.bootshiro.system.domain.UserWithRole;
import com.spbs.bootshiro.system.service.UserRoleService;
import com.spbs.bootshiro.system.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service("userService")
public class UserServiceImpl extends BaseService<TUser> implements UserService {

    @Autowired
    private TUserMapper userMapper;

    @Autowired
    private TUserRoleMapper userRoleMapper;

    @Autowired
    private UserRoleService userRoleService;

    @Override
    public TUser findByName(String userName) {
        Example example = new Example(TUser.class);
        example.createCriteria().andCondition("lower(user_name)=", userName.toLowerCase());
        List<TUser> list = this.selectByExample(example);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    @Transactional
    public void updateLoginTime(String userName) {
        Example example = new Example(TUser.class);
        example.createCriteria().andCondition("lower(user_name)=", userName.toLowerCase());
        TUser user = new TUser();
        user.setLastLoginTime(new Date());
        this.userMapper.updateByExampleSelective(user, example);
    }


    @Override
    public UserWithRole findById(Integer userId) {
        List<UserWithRole> list = this.userMapper.findUserWithRole(userId);
        List<Integer> roleList = list.stream().map(UserWithRole::getRoleId).collect(Collectors.toList());
        if (list.isEmpty()){
            return null;
        }
        UserWithRole userWithRole = list.get(0);
        userWithRole.setRoleIds(roleList);
        return userWithRole;
    }

    @Override
    public List<TUser> findUserWithDept(TUser user, QueryRequest request) {
        return null;
    }

    @Override
    public void registUser(TUser user) {
        user.setCreateDate(new Date());
        user.setTheme(TUser.DEFAULT_THEME);
        user.setAvatar(TUser.DEFAULT_AVATAR);
        user.setSex(TUser.SEX_UNKNOW);
        user.setPassword(MD5Utils.encrypt(user.getUserName(), user.getPassword()));
        this.save(user);
        TUserRole ur = new TUserRole();
        ur.setUserId(user.getId());
        ur.setRoleId((int) 3L);
        this.userRoleMapper.insert(ur);

    }

    @Override
    public void addUser(TUser user, Integer[] roles) {
        user.setCreateDate(new Date());
        user.setTheme(TUser.DEFAULT_THEME);
        user.setAvatar(TUser.DEFAULT_AVATAR);
        user.setPassword(MD5Utils.encrypt(user.getUserName(), user.getPassword()));
        this.save(user);
        setUserRoles(user, roles);

    }

    @Override
    public void updateUser(TUser user, Integer[] roles) {
        user.setPassword(null);
        user.setUserName(null);
        user.setUpdateDate(new Date());
        this.updateNotNull(user);
        Example example = new Example(TUserRole.class);
        example.createCriteria().andCondition("user_id=", user.getId());
        this.userRoleMapper.deleteByExample(example);
        setUserRoles(user, roles);

    }

    @Override
    public void deleteUsers(String ids) {
        List<String> list = Arrays.asList(ids.split(","));
        this.batchDelete(list, "userId", TUser.class);

        this.userRoleService.deleteUserRolesByUserId(ids);

    }

    @Override
    public void updatePassword(String password) {
        TUser user = (TUser) SecurityUtils.getSubject().getPrincipal();
        Example example = new Example(TUser.class);
        example.createCriteria().andCondition("user_name=", user.getUserName());
        String newPassword = MD5Utils.encrypt(user.getUserName().toLowerCase(), password);
        user.setPassword(newPassword);
        this.userMapper.updateByExampleSelective(user, example);
    }

    @Override
    public TUser findUserProfile(TUser user) {
        return this.userMapper.findUserProfile(user);
    }

    @Override
    public void updateUserProfile(TUser user) {
        user.setUserName(null);
        user.setPassword(null);
        if (user.getDeptId() == null){
            user.setDeptId((int) 0L);
        }
        this.updateNotNull(user);
    }


    private void setUserRoles(TUser user, Integer[] roles) {
        Arrays.stream(roles).forEach(roleId -> {
            TUserRole ur = new TUserRole();
            ur.setUserId(user.getId());
            ur.setRoleId(roleId);
            this.userRoleMapper.insert(ur);
        });
    }
}

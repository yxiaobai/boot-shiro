package com.spbs.bootshiro.system.service.impl;

import com.spbs.bootshiro.common.service.impl.BaseService;
import com.spbs.bootshiro.system.dao.TMenuMapper;
import com.spbs.bootshiro.system.domain.TMenu;
import com.spbs.bootshiro.system.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("menuService")
public class MenuServiceImpl extends BaseService<TMenu> implements MenuService {

    @Autowired
    private TMenuMapper menuMapper;


    @Override
    public List<TMenu> findUserPermissions(String userName) {
        return this.menuMapper.findUserPermissions(userName);
    }

}

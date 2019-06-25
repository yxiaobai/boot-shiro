package com.spbs.bootshiro.system.service;

import com.spbs.bootshiro.common.service.IService;
import com.spbs.bootshiro.system.domain.TMenu;

import java.util.List;

public interface MenuService extends IService<TMenu> {
    List<TMenu> findUserPermissions(String userName);
}

package com.spbs.bootshiro.system.service;

import com.spbs.bootshiro.common.service.IService;
import com.spbs.bootshiro.system.domain.TRoleMenu;

public interface RoleMenuServie extends IService<TRoleMenu> {

	void deleteRoleMenusByRoleId(String roleIds);

	void deleteRoleMenusByMenuId(String menuIds);
}

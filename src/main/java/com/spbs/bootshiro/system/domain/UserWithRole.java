package com.spbs.bootshiro.system.domain;

import java.util.List;

public class UserWithRole extends TUser{
	
	private static final long serialVersionUID = -5680235862276163462L;
	
	private Integer roleId;
	
	private List<Integer> roleIds;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public List<Integer> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(List<Integer> roleIds) {
		this.roleIds = roleIds;
	}
}
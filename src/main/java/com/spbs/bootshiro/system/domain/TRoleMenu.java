package com.spbs.bootshiro.system.domain;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "t_role_menu")
public class TRoleMenu implements Serializable {
    /**
     * 角色ID
     */
    @Column(name = "role_id")
    private Long roleId;

    /**
     * 菜单/按钮ID
     */
    @Column(name = "menu_id")
    private Long menuId;

    /**
     * 获取角色ID
     *
     * @return role_id - 角色ID
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * 设置角色ID
     *
     * @param roleId 角色ID
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取菜单/按钮ID
     *
     * @return menu_id - 菜单/按钮ID
     */
    public Long getMenuId() {
        return menuId;
    }

    /**
     * 设置菜单/按钮ID
     *
     * @param menuId 菜单/按钮ID
     */
    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }
}
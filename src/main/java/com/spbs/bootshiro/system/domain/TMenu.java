package com.spbs.bootshiro.system.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_menu")
public class TMenu implements Serializable {
    /**
     * 菜单/按钮ID
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 上级菜单ID
     */
    @Column(name = "parent_id")
    private Integer parentId;

    /**
     * 菜单/按钮名称
     */
    @Column(name = "menu_name")
    private String menuName;

    /**
     * 菜单URL
     */
    private String url;

    /**
     * 权限标识
     */
    private String perms;

    /**
     * 图标
     */
    private String icon;

    /**
     * 类型 0菜单 1按钮
     */
    private String type;

    /**
     * 排序
     */
    @Column(name = "order_num")
    private Integer orderNum;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新日期
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 获取菜单/按钮ID
     *
     * @return id - 菜单/按钮ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置菜单/按钮ID
     *
     * @param id 菜单/按钮ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取上级菜单ID
     *
     * @return parent_id - 上级菜单ID
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 设置上级菜单ID
     *
     * @param parentId 上级菜单ID
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取菜单/按钮名称
     *
     * @return menu_name - 菜单/按钮名称
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * 设置菜单/按钮名称
     *
     * @param menuName 菜单/按钮名称
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    /**
     * 获取菜单URL
     *
     * @return url - 菜单URL
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置菜单URL
     *
     * @param url 菜单URL
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * 获取权限标识
     *
     * @return perms - 权限标识
     */
    public String getPerms() {
        return perms;
    }

    /**
     * 设置权限标识
     *
     * @param perms 权限标识
     */
    public void setPerms(String perms) {
        this.perms = perms == null ? null : perms.trim();
    }

    /**
     * 获取图标
     *
     * @return icon - 图标
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置图标
     *
     * @param icon 图标
     */
    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    /**
     * 获取类型 0菜单 1按钮
     *
     * @return type - 类型 0菜单 1按钮
     */
    public String getType() {
        return type;
    }

    /**
     * 设置类型 0菜单 1按钮
     *
     * @param type 类型 0菜单 1按钮
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 获取排序
     *
     * @return order_num - 排序
     */
    public Integer getOrderNum() {
        return orderNum;
    }

    /**
     * 设置排序
     *
     * @param orderNum 排序
     */
    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新日期
     *
     * @return update_time - 更新日期
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新日期
     *
     * @param updateTime 更新日期
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
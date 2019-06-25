package com.spbs.bootshiro.system.dao;

import com.spbs.bootshiro.common.config.MyMapper;
import com.spbs.bootshiro.system.domain.TMenu;

import java.util.List;

public interface TMenuMapper extends MyMapper<TMenu> {

    List<TMenu> findUserPermissions(String userName);

    // 删除父节点，子节点变成顶级节点（根据实际业务调整）
    void changeToTop(List<String> menuIds);
}
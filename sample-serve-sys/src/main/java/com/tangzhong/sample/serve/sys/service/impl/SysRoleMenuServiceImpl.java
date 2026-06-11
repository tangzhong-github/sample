package com.tangzhong.sample.serve.sys.service.impl;

import com.tangzhong.sample.framework.mybatis.service.impl.BaseServiceImpl;
import com.tangzhong.sample.serve.sys.entity.SysRoleMenu;
import com.tangzhong.sample.serve.sys.service.ISysRoleMenuService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * @author tangzhong
 * @date   2026-06-04 14:12
 * @since  V1.0.0
 */
@Service
public class SysRoleMenuServiceImpl extends BaseServiceImpl<SysRoleMenu> implements ISysRoleMenuService {

    @Override
    public List<Long> getMenuIds(Collection<Long> roleIds) {
        List<SysRoleMenu> list = list(SysRoleMenu::getRoleId, roleIds);
        return list.stream().map(SysRoleMenu::getMenuId).distinct().toList();
    }
}
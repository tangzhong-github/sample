package com.tangzhong.sample.serve.sys.service.impl;

import com.tangzhong.sample.common.constant.RoleKeyConstants;
import com.tangzhong.sample.framework.mybatis.service.impl.BaseServiceImpl;
import com.tangzhong.sample.serve.sys.entity.SysRole;
import com.tangzhong.sample.serve.sys.service.ISysRoleService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Collection;

/**
 * @author tangzhong
 * @date   2026-06-04 14:12
 * @since  V1.0.0
 */
@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole> implements ISysRoleService {

    @Override
    public SysRole getAdminRole() {
        SysRole adminRole = getOne(SysRole::getRoleKey, RoleKeyConstants.ADMIN);
        Assert.notNull(adminRole, "查找不到管理员角色配置，请联系系统运维人员！");
        return adminRole;
    }

    @Override
    public boolean existsAdminRole(Collection<Long> roleIds) {
        Long adminRoleId = getAdminRole().getId();
        return roleIds.contains(adminRoleId);
    }
}
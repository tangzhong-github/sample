package com.tangzhong.sample.serve.sys.service.impl;

import com.tangzhong.sample.framework.mybatis.service.impl.BaseServiceImpl;
import com.tangzhong.sample.serve.sys.entity.SysUserRole;
import com.tangzhong.sample.serve.sys.service.ISysUserRoleService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author tangzhong
 * @date   2026-06-04 14:12
 * @since  V1.0.0
 */
@Service
public class SysUserRoleServiceImpl extends BaseServiceImpl<SysUserRole> implements ISysUserRoleService{

    private List<SysUserRole> getUserRoles(Long userId){
        return list(SysUserRole::getUserId, Collections.singletonList(userId));
    }

    @Override
    public List<Long> getUserRoleIds(Long userId) {
        return getUserRoles(userId).stream().map(SysUserRole::getRoleId).distinct().toList();
    }

}
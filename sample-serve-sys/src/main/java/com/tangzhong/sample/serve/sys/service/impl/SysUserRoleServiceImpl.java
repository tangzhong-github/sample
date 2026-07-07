package com.tangzhong.sample.serve.sys.service.impl;

import com.tangzhong.sample.framework.mybatis.service.impl.BaseServiceImpl;
import com.tangzhong.sample.serve.sys.api.dto.SysRoleAuthorizeDTO;
import com.tangzhong.sample.serve.sys.api.dto.SysUserGrantDTO;
import com.tangzhong.sample.serve.sys.entity.SysUserRole;
import com.tangzhong.sample.serve.sys.mapper.SysUserRoleMapper;
import com.tangzhong.sample.serve.sys.service.ISysUserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tangzhong
 * @date   2026-06-04 14:12
 * @since  V1.0.0
 */
@Service
public class SysUserRoleServiceImpl extends BaseServiceImpl<SysUserRole, SysUserRoleMapper> implements ISysUserRoleService{

    @Override
    public List<Long> getUserGrantRoleIds(Long userId) {
        return list(SysUserRole::getUserId, Collections.singletonList(userId)).stream().map(SysUserRole::getRoleId).toList();
    }

    @Override
    public List<Long> getRoleAuthorizeUserIds(Long roleId) {
        return list(SysUserRole::getRoleId, Collections.singletonList(roleId)).stream().map(SysUserRole::getUserId).toList();
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void grant(SysUserGrantDTO dto) {
        List<Long> grantedRoleIds = this.getUserGrantRoleIds(dto.getUserId());
        if(!CollectionUtils.isEmpty(grantedRoleIds)){
            dto.getRoleIds().removeAll(grantedRoleIds);
        }
        if(!CollectionUtils.isEmpty(dto.getRoleIds())){
            List<SysUserRole> userRoleList = dto.getRoleIds().stream()
                    .map(roleId -> SysUserRole.of(dto.getUserId(), roleId))
                    .collect(Collectors.toList());
            super.saveBatch(userRoleList);
        }
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void authorize(SysRoleAuthorizeDTO dto) {
        List<Long> authorizedUserIds = this.getRoleAuthorizeUserIds(dto.getRoleId());
        if(!CollectionUtils.isEmpty(authorizedUserIds)){
            dto.getUserIds().removeAll(authorizedUserIds);
        }
        if(!CollectionUtils.isEmpty(dto.getUserIds())){
            List<SysUserRole> userRoleList = dto.getUserIds().stream()
                    .map(userId -> SysUserRole.of(userId, dto.getRoleId()))
                    .collect(Collectors.toList());
            super.saveBatch(userRoleList);
        }
    }

}
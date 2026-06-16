package com.tangzhong.sample.serve.sys.service.impl;

import com.tangzhong.sample.common.util.AssertUtil;
import com.tangzhong.sample.framework.mybatis.service.impl.BaseManageServiceImpl;
import com.tangzhong.sample.serve.sys.constant.SysDictConstants;
import com.tangzhong.sample.serve.sys.converter.SysRoleConverter;
import com.tangzhong.sample.serve.sys.entity.SysRole;
import com.tangzhong.sample.serve.sys.mapper.SysRoleMapper;
import com.tangzhong.sample.serve.sys.pojo.dto.SysRoleDTO;
import com.tangzhong.sample.serve.sys.service.ISysRoleService;
import com.tangzhong.sample.serve.sys.service.ISysUserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

/**
 * @author tangzhong
 * @date   2026-06-04 14:12
 * @since  V1.0.0
 */
@Service
@RequiredArgsConstructor
public class SysRoleServiceImpl extends BaseManageServiceImpl<SysRole, SysRoleDTO, SysRoleConverter, SysRoleMapper> implements ISysRoleService{

    private final ISysUserRoleService sysUserRoleService;

    @Override
    public SysRole getRole(String roleKey) {
        return getOne(SysRole::getRoleKey, roleKey);
    }

    @Override
    public boolean add(SysRoleDTO dto) {
        SysRole stock = this.getRole(dto.getRoleKey());
        AssertUtil.predict(Objects::isNull, stock, "角色Key已存在，请确认！");
        return super.save(SysRole.builder()
                .roleKey(dto.getRoleKey())
                .roleName(dto.getRoleName())
                .status(SysDictConstants.ROLE_STATUS_NORMAL)
                .build()
        );
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean delete(Long id) {
        List<Long> roleAuthorizeUserIds = sysUserRoleService.getRoleAuthorizeUserIds(id);
        AssertUtil.predict(CollectionUtils::isEmpty, roleAuthorizeUserIds, "该角色已授权给用户，请先解除授权！");
        return super.removeById(id);
    }

    @Override
    public boolean enable(Long id) {
        SysRole stock = super.getById(id);
        return super.update(stock.getId(), SysRole::getStatus, SysDictConstants.ROLE_STATUS_NORMAL);
    }

    @Override
    public boolean disable(Long id) {
        SysRole stock = super.getById(id);
        return super.update(stock.getId(), SysRole::getStatus, SysDictConstants.ROLE_STATUS_DISABLE);
    }
}
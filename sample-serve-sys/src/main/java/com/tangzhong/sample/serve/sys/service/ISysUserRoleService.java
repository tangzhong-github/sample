package com.tangzhong.sample.serve.sys.service;

import com.tangzhong.sample.framework.mybatis.service.IBaseService;
import com.tangzhong.sample.serve.sys.entity.SysUserRole;
import com.tangzhong.sample.serve.sys.pojo.dto.SysRoleAuthorizeDTO;
import com.tangzhong.sample.serve.sys.pojo.dto.SysUserGrantDTO;

import java.util.List;

/**
 *
 * @author tangzhong
 * @date   2026-06-04 14:12
 * @since  V1.0.0
 */
public interface ISysUserRoleService extends IBaseService<SysUserRole> {

    List<Long> getUserGrantRoleIds(Long userId);

    List<Long> getRoleAuthorizeUserIds(Long roleId);

    void grant(SysUserGrantDTO dto);

    void authorize(SysRoleAuthorizeDTO dto);

}

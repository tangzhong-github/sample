package com.tangzhong.sample.serve.sys.service;

import com.tangzhong.sample.framework.mybatis.service.IBaseManageService;
import com.tangzhong.sample.serve.sys.entity.SysRole;
import com.tangzhong.sample.serve.sys.pojo.dto.SysRoleDTO;

/**
 *
 * @author tangzhong
 * @date   2026-06-04 14:12
 * @since  V1.0.0
 */
public interface ISysRoleService extends IBaseManageService<SysRole, SysRoleDTO> {

    SysRole getRole(String roleKey);

    boolean enable(Long id);

    boolean disable(Long id);

}

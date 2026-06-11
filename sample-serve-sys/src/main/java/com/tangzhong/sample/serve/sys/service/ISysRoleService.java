package com.tangzhong.sample.serve.sys.service;

import com.tangzhong.sample.framework.mybatis.service.IBaseService;
import com.tangzhong.sample.serve.sys.entity.SysRole;

import java.util.Collection;

/**
 *
 * @author tangzhong
 * @date   2026-06-04 14:12
 * @since  V1.0.0
 */
public interface ISysRoleService extends IBaseService<SysRole> {

    SysRole getAdminRole();

    boolean existsAdminRole(Collection<Long> roleIds);

}

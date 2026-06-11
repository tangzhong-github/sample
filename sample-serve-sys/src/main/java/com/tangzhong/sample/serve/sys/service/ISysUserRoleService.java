package com.tangzhong.sample.serve.sys.service;

import com.tangzhong.sample.framework.mybatis.service.IBaseService;
import com.tangzhong.sample.serve.sys.entity.SysUserRole;

import java.util.List;

/**
 *
 * @author tangzhong
 * @date   2026-06-04 14:12
 * @since  V1.0.0
 */
public interface ISysUserRoleService extends IBaseService<SysUserRole> {

    List<Long> getUserRoleIds(Long userId);

}

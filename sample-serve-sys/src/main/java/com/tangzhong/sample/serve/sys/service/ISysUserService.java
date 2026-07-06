package com.tangzhong.sample.serve.sys.service;

import com.tangzhong.sample.framework.mybatis.service.IBaseManageService;
import com.tangzhong.sample.serve.sys.entity.SysUser;
import com.tangzhong.sample.serve.sys.api.dto.SysUserDTO;
import com.tangzhong.sample.serve.sys.api.dto.SysUserResetPasswordDTO;

/**
 *
 * @author tangzhong
 * @date   2026-06-04 14:12
 * @since  V1.0.0
 */
public interface ISysUserService extends IBaseManageService<SysUser, SysUserDTO> {

    SysUser getByUsername(String username);

    boolean resetPassword(SysUserResetPasswordDTO dto);

    boolean enable(Long id);

    boolean disable(Long id);

}

package com.tangzhong.sample.serve.sys.service.impl;

import com.tangzhong.sample.framework.mybatis.service.impl.BaseServiceImpl;
import com.tangzhong.sample.serve.sys.constant.SysDictConstants;
import com.tangzhong.sample.serve.sys.entity.SysUser;
import com.tangzhong.sample.serve.sys.pojo.dto.SysUserDTO;
import com.tangzhong.sample.serve.sys.service.ISysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * @author tangzhong
 * @date   2026-06-04 14:12
 * @since  V1.0.0
 */
@Service
@RequiredArgsConstructor
public class SysUserServiceImpl extends BaseServiceImpl<SysUser> implements ISysUserService{

    private final PasswordEncoder passwordEncoder;

    @Override
    public SysUser getByUsername(String username) {
        return getOne(SysUser::getUsername, username);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void add(SysUserDTO dto) {
        SysUser stock = this.getByUsername(dto.getUsername());
        Assert.isNull(stock, "用户已存在");
        super.save(SysUser.builder()
                .type(SysDictConstants.USER_TYPE_ORDINARY)
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .status(SysDictConstants.USER_STATUS_NORMAL)
                .build()
        );
    }

}
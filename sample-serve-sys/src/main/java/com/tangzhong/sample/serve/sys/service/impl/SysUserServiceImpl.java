package com.tangzhong.sample.serve.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.tangzhong.sample.common.util.AssertUtil;
import com.tangzhong.sample.framework.mybatis.service.impl.BaseManageServiceImpl;
import com.tangzhong.sample.serve.sys.constant.SysDictConstants;
import com.tangzhong.sample.serve.sys.converter.SysUserConverter;
import com.tangzhong.sample.serve.sys.entity.SysUser;
import com.tangzhong.sample.serve.sys.mapper.SysUserMapper;
import com.tangzhong.sample.serve.sys.api.dto.SysUserDTO;
import com.tangzhong.sample.serve.sys.api.dto.SysUserResetPasswordDTO;
import com.tangzhong.sample.serve.sys.service.ISysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * @author tangzhong
 * @date   2026-06-04 14:12
 * @since  V1.0.0
 */
@Service
@RequiredArgsConstructor
public class SysUserServiceImpl extends BaseManageServiceImpl<SysUser, SysUserDTO, SysUserConverter, SysUserMapper> implements ISysUserService{

    private final PasswordEncoder passwordEncoder;

    @Override
    public SysUser getByUsername(String username) {
        return getOne(SysUser::getUsername, username);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean add(SysUserDTO dto) {
        SysUser stock = this.getByUsername(dto.getUsername());
        AssertUtil.predict(Objects::isNull, stock, "用户已存在");
        stock = super.getOne(SysUser::getMobilePhoneNumber, dto.getMobilePhoneNumber());
        AssertUtil.predict(Objects::isNull, stock, "手机号已存在");

        return super.save(SysUser.builder()
                .type(SysDictConstants.USER_TYPE_ORDINARY)
                .username(dto.getUsername())
                .mobilePhoneNumber(dto.getMobilePhoneNumber())
                .password(passwordEncoder.encode("123456"))
                .status(SysDictConstants.USER_STATUS_NORMAL)
                .build()
        );
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean edit(SysUserDTO dto) {
        SysUser stock = super.getById(dto.getId());
        SysUser stockByCondition = this.getByUsername(dto.getUsername());
        AssertUtil.predict(e->Objects.isNull(e) || Objects.equals(e.getId(), dto.getId()), stockByCondition, "用户已存在");
        stockByCondition = super.getOne(SysUser::getMobilePhoneNumber, dto.getMobilePhoneNumber());
        AssertUtil.predict(e->Objects.isNull(e) || Objects.equals(e.getId(), dto.getId()), stockByCondition, "手机号已存在");

        LambdaUpdateWrapper<SysUser> updateWrapper = updateWrapper(stock.getId())
                .set(SysUser::getUsername, dto.getUsername())
                .set(SysUser::getMobilePhoneNumber, dto.getMobilePhoneNumber());
        return super.update(updateWrapper);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean resetPassword(@Validated SysUserResetPasswordDTO dto) {
        SysUser user = super.getById(dto.getId());
        return super.update(user.getId(), SysUser::getPassword, passwordEncoder.encode(dto.getPassword()));
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean enable(Long id) {
        SysUser user = super.getById(id);
        return super.update(user.getId(), SysUser::getStatus, SysDictConstants.USER_STATUS_NORMAL);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean disable(Long id) {
        SysUser user = super.getById(id);
        return super.update(user.getId(), SysUser::getStatus, SysDictConstants.USER_STATUS_FREEZE);
    }

}
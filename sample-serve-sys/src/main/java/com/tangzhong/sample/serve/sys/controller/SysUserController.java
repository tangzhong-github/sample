package com.tangzhong.sample.serve.sys.controller;

import com.tangzhong.sample.framework.common.api.PageObject;
import com.tangzhong.sample.framework.common.api.R;
import com.tangzhong.sample.framework.common.validation.groups.AddGroup;
import com.tangzhong.sample.framework.common.validation.groups.EditGroup;
import com.tangzhong.sample.framework.log.annotation.OperateLog;
import com.tangzhong.sample.framework.mybatis.entity.Page;
import com.tangzhong.sample.framework.web.BaseController;
import com.tangzhong.sample.serve.sys.api.dto.SysUserDTO;
import com.tangzhong.sample.serve.sys.api.dto.SysUserGrantDTO;
import com.tangzhong.sample.serve.sys.api.dto.SysUserResetPasswordDTO;
import com.tangzhong.sample.serve.sys.api.request.SysUserPageRequest;
import com.tangzhong.sample.serve.sys.api.vo.SysUserDetailVO;
import com.tangzhong.sample.serve.sys.api.vo.SysUserListVO;
import com.tangzhong.sample.serve.sys.service.ISysUserRoleService;
import com.tangzhong.sample.serve.sys.service.ISysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author tangzhong
 * @date   2026-06-04 11:25
 * @since  V1.0.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/sys/user")
public class SysUserController extends BaseController {

    private final ISysUserService sysUserService;
    private final ISysUserRoleService sysUserRoleService;

    @GetMapping("/pageList")
    @PreAuthorize("@ss.hasPermission('sys:user:list')")
    public R<PageObject<SysUserListVO>> pageList(SysUserPageRequest request){
        return R.success(sysUserService.pageList(Page.of(request)));
    }

    @GetMapping("/{id}")
    @PreAuthorize("@ss.hasPermission('sys:user:detail')")
    public R<SysUserDetailVO> detail(@PathVariable Long id){
        return R.success(sysUserService.detail(id));
    }

    @OperateLog("用户管理:新增用户")
    @PostMapping
    //@PreAuthorize("@ss.hasPermission('sys:user:add')")
    @PreAuthorize("hasAuthority('sys:user:add')")
    public R<?> add(@RequestBody @Validated(AddGroup.class) SysUserDTO dto){
        sysUserService.add(dto);
        return R.success();
    }

    @PutMapping
    @PreAuthorize("@ss.hasPermission('sys:user:edit')")
    public R<?> edit(@RequestBody @Validated(EditGroup.class) SysUserDTO dto){
        sysUserService.edit(dto);
        return R.success();
    }

    @PostMapping("/password/reset")
    @PreAuthorize("@ss.hasPermission('sys:user:resetPassword')")
    public R<?> resetPassword(@RequestBody SysUserResetPasswordDTO dto) {
        return R.of(sysUserService.resetPassword(dto));
    }

    @PostMapping("/user/grant")
    @PreAuthorize("@ss.hasPermission('sys:user:assign')")
    public R<?> grant(@RequestBody SysUserGrantDTO dto) {
        sysUserRoleService.grant(dto);
        return R.success();
    }

    @PostMapping("/status/enable/{id}")
    @PreAuthorize("@ss.hasPermission('sys:user:changeStatus')")
    public R<?> enable(@PathVariable Long id){
        return R.of(sysUserService.enable(id));
    }

    @PostMapping("/status/disable/{id}")
    @PreAuthorize("@ss.hasPermission('sys:user:changeStatus')")
    public R<?> disable(@PathVariable Long id){
        return R.of(sysUserService.disable(id));
    }

}
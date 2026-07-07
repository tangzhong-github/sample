package com.tangzhong.sample.serve.sys.controller;

import com.tangzhong.sample.framework.common.api.PageObject;
import com.tangzhong.sample.framework.common.api.R;
import com.tangzhong.sample.framework.mybatis.entity.Page;
import com.tangzhong.sample.framework.web.BaseController;
import com.tangzhong.sample.serve.sys.api.dto.SysRoleAuthorizeDTO;
import com.tangzhong.sample.serve.sys.api.dto.SysRoleDTO;
import com.tangzhong.sample.serve.sys.api.request.SysUserPageRequest;
import com.tangzhong.sample.serve.sys.api.vo.SysUserDetailVO;
import com.tangzhong.sample.serve.sys.api.vo.SysUserListVO;
import com.tangzhong.sample.serve.sys.service.ISysRoleService;
import com.tangzhong.sample.serve.sys.service.ISysUserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping("/sys/role")
public class SysRoleController extends BaseController {

    private final ISysRoleService sysRoleService;
    private final ISysUserRoleService sysUserRoleService;

    @GetMapping("/pageList")
    @PreAuthorize("@ss.hasPermission('sys:role:list')")
    public R<PageObject<SysUserListVO>> pageList(SysUserPageRequest request){
        return R.success(sysRoleService.pageList(Page.of(request)));
    }

    @GetMapping("/{id}")
    @PreAuthorize("@ss.hasPermission('sys:role:detail')")
    public R<SysUserDetailVO> detail(@PathVariable Long id){
        return R.success(sysRoleService.detail(id));
    }

    @PostMapping
    @PreAuthorize("@ss.hasPermission('sys:role:add')")
    public R<?> add(@RequestBody @Validated SysRoleDTO dto){
        sysRoleService.add(dto);
        return R.success();
    }

    @PostMapping("/user/authorize")
    @PreAuthorize("@ss.hasPermission('sys:role:authorize')")
    public R<?> authorize(@RequestBody SysRoleAuthorizeDTO dto) {
        sysUserRoleService.authorize(dto);
        return R.success();
    }

    @PostMapping("/status/enable/{id}")
    @PreAuthorize("@ss.hasPermission('sys:role:changeStatus')")
    public R<?> enable(@PathVariable Long id){
        return R.of(sysRoleService.enable(id));
    }

    @PostMapping("/status/disable/{id}")
    @PreAuthorize("@ss.hasPermission('sys:role:changeStatus')")
    public R<?> disable(@PathVariable Long id){
        return R.of(sysRoleService.disable(id));
    }

}
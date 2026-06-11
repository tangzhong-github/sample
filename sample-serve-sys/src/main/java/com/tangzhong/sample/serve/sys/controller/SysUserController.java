package com.tangzhong.sample.serve.sys.controller;

import com.tangzhong.sample.common.api.response.R;
import com.tangzhong.sample.framework.core.base.controller.BaseController;
import com.tangzhong.sample.serve.sys.entity.SysUser;
import com.tangzhong.sample.serve.sys.pojo.dto.SysUserDTO;
import com.tangzhong.sample.serve.sys.pojo.vo.SysUserDetailVO;
import com.tangzhong.sample.serve.sys.service.ISysUserService;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/sys/user")
public class SysUserController extends BaseController {

    private final ISysUserService sysUserService;

    @GetMapping("/{id}")
    public R<SysUserDetailVO> detail(@PathVariable Long id){
        SysUser user = sysUserService.getById(id);
        return R.success(SysUserDetailVO.builder()
                .id(user.getId())
                .type(user.getType())
                .username(user.getUsername())
                .status(user.getStatus())
                .creatorId(user.getCreatorId())
                .createTime(user.getCreateTime())
                .build()
        );
    }

    @PostMapping
    public R<?> add(@RequestBody @Validated SysUserDTO dto){
        sysUserService.add(dto);
        return R.success();
    }

}
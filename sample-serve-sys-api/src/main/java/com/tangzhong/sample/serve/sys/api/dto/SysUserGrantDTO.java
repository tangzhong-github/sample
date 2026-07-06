package com.tangzhong.sample.serve.sys.api.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

/**
 *
 * @author tangzhong
 * @date   2026-06-05 17:11
 * @since  V1.0.0
 */
@Data
public class SysUserGrantDTO {

    @NotEmpty(message = "用户不能为空")
    private Long userId;

    @NotEmpty(message = "角色不能为空")
    private List<Long> roleIds;

}
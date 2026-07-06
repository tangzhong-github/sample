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
public class SysRoleAuthorizeDTO {

    @NotEmpty(message = "角色不能为空")
    private Long roleId;

    @NotEmpty(message = "用户不能为空")
    private List<Long> userIds;

}
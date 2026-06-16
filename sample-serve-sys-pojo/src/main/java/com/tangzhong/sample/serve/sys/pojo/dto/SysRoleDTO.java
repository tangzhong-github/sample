package com.tangzhong.sample.serve.sys.pojo.dto;

import com.tangzhong.sample.common.api.dto.BaseDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author tangzhong
 * @date   2026-06-05 17:11
 * @since  V1.0.0
 */
@Getter
@Setter
@NoArgsConstructor
public class SysRoleDTO extends BaseDTO {

    /** 角色名 */
    @NotBlank(message = "角色名不能为空")
    private String roleName;

    /** 角色KEY */
    @NotBlank(message = "角色KEY不能为空")
    private String roleKey;

}
package com.tangzhong.sample.serve.sys.pojo.dto;

import com.tangzhong.sample.common.api.dto.BaseDTO;
import com.tangzhong.sample.common.validation.groups.AddGroup;
import com.tangzhong.sample.common.validation.groups.EditGroup;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
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
public class SysUserDTO extends BaseDTO {

    /** ID */
    @Null(message = "ID必须为空", groups = AddGroup.class)
    @NotNull(message = "ID不能为空", groups = EditGroup.class)
    private Long id;

    /** 用户名 */
    @NotBlank(message = "用户名不能为空", groups = {AddGroup.class, EditGroup.class})
    @Size(max = 64, message = "用户名长度不能超过64个字符")
    private String username;

    /** 手机号 */
    @NotBlank(message = "手机号不能为空", groups = {AddGroup.class, EditGroup.class})
    private String mobilePhoneNumber;

}
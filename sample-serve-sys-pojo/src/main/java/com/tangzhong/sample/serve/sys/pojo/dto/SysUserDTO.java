package com.tangzhong.sample.serve.sys.pojo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
public class SysUserDTO {

    /** 用户名 */
    @NotBlank(message = "用户名不能为空")
    @Size(max = 64, message = "用户名长度不能超过64个字符")
    private String username;

    /** 密码 */
    //@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z0-9_.]{6,20}$", message = "密码格式为6-20位，必须包含大小写字母和数字，可包含字母、数字、下划线(_)和点(.)")
    @Pattern(regexp = "^[a-zA-Z0-9_.]{6,20}$", message = "密码格式为6-20位，可包含字母、数字、下划线(_)和点(.)")
    private String password;

}
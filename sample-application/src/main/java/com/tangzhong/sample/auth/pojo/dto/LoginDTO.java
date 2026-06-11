package com.tangzhong.sample.auth.pojo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author tangzhong
 * @date   2026-06-04 17:30
 * @since  V1.0.0
 */
@Getter
@Setter
@NoArgsConstructor
public class LoginDTO {

    private String username;

    private String password;

}
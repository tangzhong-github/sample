package com.tangzhong.sample.serve.auth.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author tangzhong
 * @date   2026-06-04 17:31
 * @since  V1.0.0
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
public class LoginVO implements Serializable {

    private String token;

}
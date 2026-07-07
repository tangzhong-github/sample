package com.tangzhong.sample.framework.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 * @author tangzhong
 * @date   2026-07-06 16:29
 * @since  V1.0.0
 */
@Getter
@AllArgsConstructor
public enum BizExceptionCode {

    SERVE_ERROR(4000, "服务异常"),
    NO_AUTHENTICATION_INFORMATION(4001, "账号或密码错误"),
    BAD_CREDENTIALS(4002, "账号或密码错误"),
    AUTH_DENIED(4003, "操作无权限"),
    ;

    /** 错误码 */
    private final int code;

    /** 错误码描述 */
    private final String description;

}

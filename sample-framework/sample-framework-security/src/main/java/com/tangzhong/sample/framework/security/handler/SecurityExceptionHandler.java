package com.tangzhong.sample.framework.security.handler;

import com.tangzhong.sample.framework.common.api.R;
import com.tangzhong.sample.framework.common.exception.BizExceptionCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Security异常处理器
 * @author tangzhong
 * @date   2026-06-05 13:53
 * @since  V1.0.0
 */
@Slf4j
@RestControllerAdvice
public class SecurityExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    public R<?> handleAuthEx(AuthenticationException e) {
        log.warn("用户认证失败：{}", e.getMessage(), e);
        return R.fail(BizExceptionCode.BAD_CREDENTIALS.getCode(), "账号或密码错误，请确认！");
    }
    @ExceptionHandler(AccessDeniedException.class)
    public R<?> handleAccessEx(AccessDeniedException e) {
        log.warn("用户鉴权失败：{}", e.getMessage(), e);
        return R.fail(BizExceptionCode.AUTH_DENIED.getCode(), "当前操作无权限，请联系管理员授权");
    }

}
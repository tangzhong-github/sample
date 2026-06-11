package com.tangzhong.sample.framework.handler;

import com.tangzhong.sample.common.api.response.R;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

/**
 * 全局异常处理器
 * @author tangzhong
 * @date   2026-06-05 13:53
 * @since  V1.0.0
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoResourceFoundException.class)
    public R<?> handleNoResourceFoundException(NoResourceFoundException e, HttpServletRequest request) {
        log.warn("未知API路径：`{}`, 异常信息:", request.getRequestURI(), e);
        return R.fail(HttpStatus.NOT_FOUND.value(), String.format("未知API路径：`%s`", request.getRequestURI()));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public R<?> handleBadCredentialsException(BadCredentialsException e, HttpServletRequest request) {
        log.warn(e.getMessage(), e);
        return R.fail(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        log.warn(e.getMessage(), e);
        return R.fail(e.getBindingResult().getAllErrors().getLast().getDefaultMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public R<?> handleIllegalArgumentException(IllegalArgumentException e, HttpServletRequest request) {
        log.warn(e.getMessage(), e);
        return R.fail(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public R<?> handleException(Exception e, HttpServletRequest request) {
        log.error("`{}`请求，`{}`，发生系统异常，信息如下：", request.getMethod(), request.getRequestURI(), e);
        return R.fail("服务异常，请联系管理员！");
    }

}
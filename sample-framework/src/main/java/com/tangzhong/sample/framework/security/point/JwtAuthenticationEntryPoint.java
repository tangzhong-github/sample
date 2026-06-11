package com.tangzhong.sample.framework.security.point;

import com.tangzhong.sample.common.api.response.R;
import com.tangzhong.sample.common.util.JsonUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 认证失败
 * @author tangzhong
 * @date   2026-06-04 16:53
 * @since  V1.0.0
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(@NonNull HttpServletRequest request, HttpServletResponse response, @NonNull AuthenticationException authException) throws IOException{
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JsonUtil.toJsonString(R.fail(401, "未登录或token已过期，请重新登录")));
    }

}
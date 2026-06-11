package com.tangzhong.sample.framework.security.point;

import com.tangzhong.sample.common.api.response.R;
import com.tangzhong.sample.common.util.JsonUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.NonNull;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 权限不足
 * @author tangzhong
 * @date   2026-06-04 16:57
 * @since  V1.0.0
 */
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(@NonNull HttpServletRequest request, HttpServletResponse response, @NonNull AccessDeniedException accessDeniedException) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JsonUtil.toJsonString(R.fail(403, "权限不足")));
    }

}
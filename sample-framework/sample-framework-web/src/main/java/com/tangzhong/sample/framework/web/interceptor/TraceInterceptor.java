package com.tangzhong.sample.framework.web.interceptor;

import com.tangzhong.sample.framework.common.context.RequestContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author tangzhong
 * @date   2026-07-03 10:08
 * @since  V1.0.0
 */
public class TraceInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws Exception {
        RequestContext.setTraceId();
        return true;
    }

    @Override
    public void afterCompletion(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler, @Nullable Exception ex) throws Exception {
        RequestContext.clearTraceId();
    }

}
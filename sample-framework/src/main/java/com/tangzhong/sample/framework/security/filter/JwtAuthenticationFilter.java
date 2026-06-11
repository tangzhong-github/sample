package com.tangzhong.sample.framework.security.filter;

import com.tangzhong.sample.common.constant.Constants;
import com.tangzhong.sample.framework.security.config.JwtConfig;
import com.tangzhong.sample.framework.security.service.TokenService;
import com.tangzhong.sample.framework.security.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author tangzhong
 * @date   2026-06-04 16:18
 * @since  V1.0.0
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtConfig jwtConfig;
    private final TokenService tokenService;
    private final UserDetailsService userDetailsService;

    @Override
    protected boolean shouldNotFilter(@NonNull HttpServletRequest request) throws ServletException {
        List<String> excludePaths = jwtConfig.getExcludePaths();
        if(!CollectionUtils.isEmpty(excludePaths)){
            String path = request.getRequestURI();
            String contextPath = request.getContextPath();
            if(StringUtils.hasText(contextPath) && !contextPath.equals(Constants.CHAR_SLASH)){
                // 获取不包含context-path的路径
                path = path.substring(contextPath.length());
            }
            // 判断当前路径是否在排除列表中
            return excludePaths.contains(path);
        }
        return false;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(jwtConfig.getHeaderName());
        if(StringUtils.hasText(token) && token.startsWith(jwtConfig.getTokenPrefix())) {
            token = token.replace(jwtConfig.getTokenPrefix(), "").trim();
            if(JwtUtil.validateToken(token)){
                Long userId = JwtUtil.getUserId(token);
                if (Objects.nonNull(userId) && tokenService.exists(userId)) {
                    // 构建认证信息
                    String username = JwtUtil.getUsernameFromToken(token);
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
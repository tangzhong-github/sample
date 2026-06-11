package com.tangzhong.sample.framework.security.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.List;

/**
 *
 * @author tangzhong
 * @date   2026-06-10 11:45
 * @since  V1.0.0
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "security.jwt")
public class JwtConfig {

    /**
     * Token头部名称
     */
    private String headerName = "Authorization";

    /**
     * Token前缀
     */
    private String tokenPrefix = "Bearer ";

    /**
     * 排除JWT验证的路径列表
     */
    private List<String> excludePaths = Collections.singletonList("/auth/login");

}
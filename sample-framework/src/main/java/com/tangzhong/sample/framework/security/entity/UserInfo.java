package com.tangzhong.sample.framework.security.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 *
 * @author tangzhong
 * @date   2026-06-04 17:05
 * @since  V1.0.0
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserInfo implements UserDetails {

    private Long userId;

    private String username;

    private String password;

    private Collection<? extends GrantedAuthority> authorities;

}
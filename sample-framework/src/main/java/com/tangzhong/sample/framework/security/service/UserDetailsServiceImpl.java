package com.tangzhong.sample.framework.security.service;

import com.tangzhong.sample.framework.mybatis.entity.BaseEntity;
import com.tangzhong.sample.framework.security.entity.UserInfo;
import com.tangzhong.sample.framework.security.UserInfoProvider;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.FactorGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * @author tangzhong
 * @date   2026-06-04 17:01
 * @since  V1.0.0
 */
@Service
@SuppressWarnings({"rawtypes", "unchecked"})
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserInfoProvider userInfoProvider;

    @Override
    @SuppressWarnings("NullableProblems")
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        BaseEntity user = userInfoProvider.getUser(username);
        userInfoProvider.checkUser(user);
        return UserInfo.builder()
                .userId(user.getId())
                .username(username)
                .password(userInfoProvider.getPassword(user))
                .authorities(getAuthorities(user.getId()))
                .build();
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Long userId) {
        List<String> permissions = userInfoProvider.getPermissions(userId);
        return permissions.stream().map(FactorGrantedAuthority::fromAuthority).toList();
    }

}
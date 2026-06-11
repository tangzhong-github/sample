package com.tangzhong.sample.auth.controller;

import com.tangzhong.sample.auth.pojo.dto.LoginDTO;
import com.tangzhong.sample.auth.pojo.vo.LoginVO;
import com.tangzhong.sample.common.api.response.R;
import com.tangzhong.sample.framework.core.base.controller.BaseController;
import com.tangzhong.sample.framework.security.entity.UserInfo;
import com.tangzhong.sample.framework.security.service.TokenService;
import com.tangzhong.sample.framework.security.util.JwtUtil;
import com.tangzhong.sample.framework.security.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 *
 * @author tangzhong
 * @date   2026-06-04 17:28
 * @since  V1.0.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController extends BaseController {

    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public R<LoginVO> login(@RequestBody LoginDTO dto){
        //1、认证
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
        //2、生成 token
        UserInfo user = (UserInfo) authentication.getPrincipal();
        Long userId = Objects.requireNonNull(user).getUserId();
        String token = JwtUtil.generateToken(userId, user.getUsername());
        tokenService.save(userId, token);
        return R.success(LoginVO.builder().token(token).build());
    }

    @PostMapping("/logout")
    public R<?> logout(){
        Long userId = SecurityUtil.getUserId();
        tokenService.remove(userId);
        return R.success();
    }

}
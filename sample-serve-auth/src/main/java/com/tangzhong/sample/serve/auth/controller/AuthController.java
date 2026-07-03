package com.tangzhong.sample.serve.auth.controller;

import com.tangzhong.sample.common.api.response.R;
import com.tangzhong.sample.framework.core.base.controller.BaseController;
import com.tangzhong.sample.framework.security.entity.UserInfo;
import com.tangzhong.sample.framework.security.service.TokenService;
import com.tangzhong.sample.framework.security.util.JwtUtil;
import com.tangzhong.sample.framework.security.util.SecurityUtil;
import com.tangzhong.sample.serve.auth.pojo.dto.LoginDTO;
import com.tangzhong.sample.serve.auth.pojo.vo.LoginVO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.util.StringUtils;
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
        //1 认证
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
        //2 生成 token
        UserInfo user = (UserInfo) authentication.getPrincipal();
        Long userId = Objects.requireNonNull(user).getUserId();
        String newToken;
        String stockToken = tokenService.get(userId);
        if(StringUtils.hasLength(stockToken)){
            //2.1 判断当前用户是否存在token，若存在，直接返回 todo 后续要增加token刷新机制，防止token过期
            newToken = stockToken;
        }else{
            newToken = JwtUtil.generateToken(userId, user.getUsername());
            tokenService.save(userId, newToken);
        }
        return R.success(LoginVO.builder().token(newToken).build());
    }

    @PostMapping("/logout")
    public R<?> logout(){
        Long userId = SecurityUtil.getUserId();
        tokenService.remove(userId);
        return R.success();
    }

}
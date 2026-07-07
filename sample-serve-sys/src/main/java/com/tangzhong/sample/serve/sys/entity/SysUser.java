package com.tangzhong.sample.serve.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.tangzhong.sample.framework.common.service.UserInfo;
import com.tangzhong.sample.framework.mybatis.entity.MybatisEntity;
import com.tangzhong.sample.serve.sys.constant.SysDict;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author tangzhong
 * @date   2026-06-04 11:11
 * @since  V1.0.0
 */
@Getter
@Setter
@NoArgsConstructor
@TableName(value ="sys_user")
public class SysUser extends MybatisEntity implements UserInfo {

    /** 用户类型 {@link SysDict#USER_TYPE} */
    private String type;

    /** 用户名 */
    private String username;

    /** 手机号 */
    private String mobilePhoneNumber;

    /** 密码 */
    private String password;

    /** 用户状态：{@link SysDict#USER_STATUS} */
    private String status;

    @Override
    public Long getUserId() {
        return super.getId();
    }

    @Override
    public String getUserPassword() {
        return this.getPassword();
    }
}
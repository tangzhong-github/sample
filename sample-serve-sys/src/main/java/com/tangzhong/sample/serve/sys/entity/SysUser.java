package com.tangzhong.sample.serve.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.tangzhong.sample.framework.mybatis.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 *
 * @author tangzhong
 * @date   2026-06-04 11:11
 * @since  V1.0.0
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value ="sys_user")
public class SysUser extends BaseEntity {

    /** 用户类型 {@link com.tangzhong.sample.serve.sys.constant.SysDictConstants#USER_TYPE} */
    private String type;

    /** 用户名 */
    private String username;

    /** 手机号 */
    private String mobilePhoneNumber;

    /** 密码 */
    private String password;

    /** 用户状态：{@link com.tangzhong.sample.serve.sys.constant.SysDictConstants#USER_STATUS} */
    private String status;

}
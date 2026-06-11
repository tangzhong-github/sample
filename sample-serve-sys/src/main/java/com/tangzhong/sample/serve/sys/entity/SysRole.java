package com.tangzhong.sample.serve.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.tangzhong.sample.framework.mybatis.entity.BaseEntity;
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
@TableName(value ="sys_role")
public class SysRole extends BaseEntity {

    /** 角色名 */
    private String roleName;

    /** 角色KEY */
    private String roleKey;

    /** 角色状态：sys_role_status */
    private String status;

}
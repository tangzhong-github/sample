package com.tangzhong.sample.serve.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.tangzhong.sample.framework.mybatis.entity.MybatisEntity;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode(callSuper = true)
@TableName(value ="sys_role")
public class SysRole extends MybatisEntity {

    /** 角色名 */
    private String roleName;

    /** 角色KEY */
    private String roleKey;

    /** 角色状态：1021 */
    private String status;

}
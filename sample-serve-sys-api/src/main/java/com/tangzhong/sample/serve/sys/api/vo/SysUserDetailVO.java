package com.tangzhong.sample.serve.sys.api.vo;

import com.tangzhong.sample.common.api.vo.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 *
 * @author tangzhong
 * @date   2026-06-04 11:41
 * @since  V1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysUserDetailVO extends BaseVO implements Serializable {

    /** 用户类型：1011 */
    private String type;

    /** 用户名 */
    private String username;

    /** 用户状态：1012 */
    private String status;

}
package com.tangzhong.sample.common.api.vo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 *
 * @author tangzhong
 * @date   2026-06-12 14:09
 * @since  V1.0.0
 */
@Data
public class BaseVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

}
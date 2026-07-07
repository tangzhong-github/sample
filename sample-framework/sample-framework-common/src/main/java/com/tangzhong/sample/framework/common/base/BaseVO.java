package com.tangzhong.sample.framework.common.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

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

    /** 创建人名称 */
    private String creatorName;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /** 修改人名称 */
    private String updaterName;

    /** 修改时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

}
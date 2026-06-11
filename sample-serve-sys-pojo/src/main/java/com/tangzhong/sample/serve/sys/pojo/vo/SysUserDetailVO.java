package com.tangzhong.sample.serve.sys.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 * @author tangzhong
 * @date   2026-06-04 11:41
 * @since  V1.0.0
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysUserDetailVO implements Serializable {

    /** 用户ID */
    private Long id;

    /** 用户类型：1011 */
    private String type;

    /** 用户名 */
    private String username;

    /** 用户状态：1012 */
    private String status;

    /** 创建人ID */
    private Long creatorId;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

}
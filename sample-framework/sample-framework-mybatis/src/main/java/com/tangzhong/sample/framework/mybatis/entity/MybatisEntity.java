package com.tangzhong.sample.framework.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.tangzhong.sample.framework.common.base.BaseEntity;
import com.tangzhong.sample.framework.mybatis.metadata.Metadata;
import com.tangzhong.sample.framework.mybatis.metadata.MetadataFieldConstants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.mapping.SqlCommandType;

import java.time.LocalDateTime;

/**
 *
 * @author tangzhong
 * @date   2026-06-04 11:11
 * @since  V1.0.0
 */
@Getter
@Setter
@NoArgsConstructor
public class MybatisEntity extends BaseEntity {

    /** ID */
    @TableId
    @Metadata
    private Long id;

    /** 创建人ID */
    @Metadata(type = MetadataFieldConstants.USER_ID)
    private Long creatorId;

    /** 创建时间 */
    @Metadata(type = MetadataFieldConstants.LOCAL_DATE_TIME)
    private LocalDateTime createTime;

    /** 更新人ID */
    @Metadata(type = MetadataFieldConstants.USER_ID, executionCommands = {SqlCommandType.INSERT, SqlCommandType.UPDATE})
    private Long updaterId;

    /** 更新时间 */
    @Metadata(type = MetadataFieldConstants.LOCAL_DATE_TIME, executionCommands = {SqlCommandType.INSERT, SqlCommandType.UPDATE})
    private LocalDateTime updateTime;

}
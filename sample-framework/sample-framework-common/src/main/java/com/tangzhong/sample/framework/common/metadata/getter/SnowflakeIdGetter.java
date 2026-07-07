package com.tangzhong.sample.framework.common.metadata.getter;

import com.tangzhong.sample.framework.common.metadata.MetadataTypeConstants;
import com.tangzhong.sample.framework.common.util.SnowflakeIdUtil;
import org.springframework.stereotype.Component;

/**
 *
 * @author tangzhong
 * @date   2026-07-06 15:00
 * @since  V1.0.0
 */
@Component
public class SnowflakeIdGetter extends AbstractMetadataGetter<Long>{

    public SnowflakeIdGetter(){
        super(MetadataTypeConstants.SNOWFLAKE_ID);
    }

    @Override
    public Long get() {
        return SnowflakeIdUtil.getInstance().nextId();
    }
}
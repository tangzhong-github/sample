package com.tangzhong.sample.framework.mybatis.metadata.generator;

import com.tangzhong.sample.framework.common.metadata.MetadataGetterFactory;
import com.tangzhong.sample.framework.common.metadata.MetadataTypeConstants;
import com.tangzhong.sample.framework.common.util.SnowflakeIdUtil;
import com.tangzhong.sample.framework.mybatis.metadata.MetadataGenerator;
import com.tangzhong.sample.framework.mybatis.metadata.MetadataFieldConstants;
import org.springframework.stereotype.Component;

/**
 *
 * @author tangzhong
 * @date   2026-06-05 16:29
 * @since  V1.0.0
 */
@Component
public class SnowflakeIdMetadataGenerator implements MetadataGenerator<Long> {

    @Override
    public String metaDataType() {
        return MetadataFieldConstants.SNOWFLAKE_ID;
    }

    @Override
    public Long generate() {
        return MetadataGetterFactory.getValue(MetadataTypeConstants.SNOWFLAKE_ID);
    }

}

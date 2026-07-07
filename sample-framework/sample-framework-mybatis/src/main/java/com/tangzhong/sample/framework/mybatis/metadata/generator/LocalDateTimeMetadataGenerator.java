package com.tangzhong.sample.framework.mybatis.metadata.generator;

import com.tangzhong.sample.framework.common.metadata.MetadataGetterFactory;
import com.tangzhong.sample.framework.common.metadata.MetadataTypeConstants;
import com.tangzhong.sample.framework.mybatis.metadata.MetadataGenerator;
import com.tangzhong.sample.framework.mybatis.metadata.MetadataFieldConstants;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 *
 * @author tangzhong
 * @date   2026-06-05 16:29
 * @since  V1.0.0
 */
@Component
public class LocalDateTimeMetadataGenerator implements MetadataGenerator<LocalDateTime> {

    @Override
    public String metaDataType() {
        return MetadataFieldConstants.LOCAL_DATE_TIME;
    }

    @Override
    public LocalDateTime generate() {
        return MetadataGetterFactory.getValue(MetadataTypeConstants.LOCAL_DATE_TIME);
    }
}

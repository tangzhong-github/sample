package com.tangzhong.sample.framework.mybatis.metadata.generator;

import com.tangzhong.sample.framework.mybatis.metadata.MetadataGenerator;
import com.tangzhong.sample.framework.mybatis.metadata.MetadataTypeConstants;
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
        return MetadataTypeConstants.LOCAL_DATE_TIME;
    }

    @Override
    public LocalDateTime generate() {
        return LocalDateTime.now();
    }
}

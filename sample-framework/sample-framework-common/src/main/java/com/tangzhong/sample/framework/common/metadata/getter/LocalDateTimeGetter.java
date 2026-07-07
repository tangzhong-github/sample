package com.tangzhong.sample.framework.common.metadata.getter;

import com.tangzhong.sample.framework.common.metadata.MetadataTypeConstants;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 *
 * @author tangzhong
 * @date   2026-07-06 15:00
 * @since  V1.0.0
 */
@Component
public class LocalDateTimeGetter extends AbstractMetadataGetter<LocalDateTime>{

    public LocalDateTimeGetter(){
        super(MetadataTypeConstants.LOCAL_DATE_TIME);
    }

    @Override
    public LocalDateTime get() {
        return LocalDateTime.now();
    }
}
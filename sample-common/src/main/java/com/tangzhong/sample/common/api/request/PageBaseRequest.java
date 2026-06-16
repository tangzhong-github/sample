package com.tangzhong.sample.common.api.request;

import lombok.Data;

/**
 *
 * @author tangzhong
 * @date   2026-06-12 14:16
 * @since  V1.0.0
 */
@Data
public class PageBaseRequest {

    private int pageNum;

    private int pageSize;

}
package com.tangzhong.sample.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author tangzhong
 * @date   2026-06-16 10:01
 * @since  V1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BizException extends RuntimeException{

    private int code;

    public BizException(String message) {
        throw new BizException(500, message);
    }

    public BizException(int code, String message) {
        super(message);
        this.code = code;
    }

}
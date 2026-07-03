package com.tangzhong.sample.framework.mvc;

import org.slf4j.MDC;

import java.util.UUID;

/**
 * @author tangzhong
 * @date   2026-07-03 10:10
 * @since  V1.0.0
 */
public final class RequestContext {

    private static final String TRACE_ID = "traceId";

    public static String getTraceId() {
        return MDC.get(TRACE_ID);
    }

    public static void setTraceId(){
        MDC.put(TRACE_ID, UUID.randomUUID().toString().replace("-", ""));
    }

    public static void clearTraceId(){
        MDC.clear();
    }

}
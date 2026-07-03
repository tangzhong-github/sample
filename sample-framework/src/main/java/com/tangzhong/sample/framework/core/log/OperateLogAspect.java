package com.tangzhong.sample.framework.core.log;

import com.tangzhong.sample.common.constant.CommonDictConstants;
import com.tangzhong.sample.common.util.JsonUtil;
import com.tangzhong.sample.framework.mvc.RequestContext;
import com.tangzhong.sample.framework.security.util.SecurityUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author tangzhong
 * @date   2026-07-02 17:40
 * @since  V1.0.0
 */
@RequiredArgsConstructor
@Aspect
@Component
public class OperateLogAspect {

    private final OperateLogHandler operateLogHandler;

    @Around("@annotation(operateLog)")
    public Object around(ProceedingJoinPoint pjp, OperateLog operateLog) throws Throwable {
        long operateStartTime = System.currentTimeMillis();
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        Object result = null;
        String isSuccess = CommonDictConstants.BOOLEAN_TRUE;
        Exception error = null;
        try {
            result = pjp.proceed();
            return result;
        } catch (Exception e) {
            isSuccess = CommonDictConstants.BOOLEAN_FALSE;
            error = e;
            throw e;
        } finally {
            OperateLogDTO dto = new OperateLogDTO();
            dto.setTraceId(RequestContext.getTraceId());
            dto.setIp(request.getRemoteAddr());
            dto.setOperationName(operateLog.value());
            dto.setRequestMethod(request.getMethod());
            dto.setRequestUrl(request.getRequestURL().toString());
            dto.setMethodSignature(pjp.getSignature().toShortString());
            dto.setRequestParams(JsonUtil.toJsonString(pjp.getArgs()));
            if(CommonDictConstants.BOOLEAN_TRUE.equals(isSuccess)){
                dto.setResponseData(substring(JsonUtil.toJsonString(result)));
            }else{
                dto.setResponseData(error.getMessage());
                String exceptionInfo = Arrays.stream(error.getStackTrace()).map(StackTraceElement::toString).collect(Collectors.joining("\n"));
                dto.setExceptionInfo(substring(exceptionInfo));
            }
            dto.setIsSuccess(isSuccess);
            dto.setOperatorName(SecurityUtil.getUsername());
            dto.setCostTime(System.currentTimeMillis() - operateStartTime);
            operateLogHandler.save(dto);
        }
    }

    private String substring(String str){
        if(!StringUtils.hasLength(str) || str.length()<=2000){
            return str;
        }
        return str.substring(0, 2000);
    }

}
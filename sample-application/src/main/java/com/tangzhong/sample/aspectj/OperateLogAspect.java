package com.tangzhong.sample.aspectj;

import com.tangzhong.sample.common.util.JsonUtil;
import com.tangzhong.sample.framework.annotation.OperateLog;
import com.tangzhong.sample.framework.mvc.RequestContext;
import com.tangzhong.sample.serve.sys.constant.SysDictConstants;
import com.tangzhong.sample.serve.sys.pojo.dto.SysOperateLogDTO;
import com.tangzhong.sample.serve.sys.service.ISysOperateLogService;
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

    private final ISysOperateLogService sysOperateLogService;

    @Around("@annotation(operateLog)")
    public Object around(ProceedingJoinPoint pjp, OperateLog operateLog) throws Throwable {
        long start = System.currentTimeMillis();
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        Object result = null;
        String isSuccess = SysDictConstants.BOOLEAN_TRUE;
        Exception error = null;
        try {
            result = pjp.proceed();
            return result;
        } catch (Exception e) {
            isSuccess = SysDictConstants.BOOLEAN_FALSE;
            error = e;
            throw e;
        } finally {
            SysOperateLogDTO dto = new SysOperateLogDTO();
            dto.setTraceId(RequestContext.getTraceId());
            dto.setIp(request.getRemoteAddr());
            dto.setOperationName(operateLog.value());
            dto.setRequestMethod(request.getMethod());
            dto.setRequestUrl(request.getRequestURL().toString());
            dto.setMethodSignature(pjp.getSignature().toShortString());
            dto.setRequestParams(JsonUtil.toJsonString(pjp.getArgs()));
            if(SysDictConstants.BOOLEAN_TRUE.equals(isSuccess)){
                dto.setResponseData(substring(JsonUtil.toJsonString(result)));
            }else{
                dto.setResponseData(error.getMessage());
                String exceptionInfo = Arrays.stream(error.getStackTrace()).map(StackTraceElement::toString).collect(Collectors.joining("\n"));
                dto.setExceptionInfo(substring(exceptionInfo));
            }
            dto.setCostTime(System.currentTimeMillis() - start);
            dto.setIsSuccess(isSuccess);
            sysOperateLogService.addAsync(dto);
        }
    }

    private String substring(String str){
        if(!StringUtils.hasLength(str) || str.length()<=2000){
            return str;
        }
        return str.substring(0, 2000);
    }

}
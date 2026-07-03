package com.tangzhong.sample.serve.sys.service.impl;

import com.tangzhong.sample.framework.core.log.OperateLogDTO;
import com.tangzhong.sample.framework.core.log.OperateLogHandler;
import com.tangzhong.sample.framework.mybatis.service.impl.BaseServiceImpl;
import com.tangzhong.sample.serve.sys.converter.SysOperateLogConverter;
import com.tangzhong.sample.serve.sys.entity.SysOperateLog;
import com.tangzhong.sample.serve.sys.mapper.SysOperateLogMapper;
import com.tangzhong.sample.serve.sys.service.ISysOperateLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author tangzhong
 * @date   2026-06-04 14:12
 * @since  V1.0.0
 */
@Service
@RequiredArgsConstructor
public class SysOperateLogServiceImpl extends BaseServiceImpl<SysOperateLog, SysOperateLogMapper> implements ISysOperateLogService, OperateLogHandler {

    private final SysOperateLogConverter sysOperateLogConverter;

    @Override
    @Async("logExecutor")
    public void save(OperateLogDTO dto) {
        SysOperateLog entity = sysOperateLogConverter.toEntity(dto);
        super.save(entity);
    }

}
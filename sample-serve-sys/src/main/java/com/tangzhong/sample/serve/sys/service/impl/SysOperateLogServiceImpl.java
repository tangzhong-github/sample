package com.tangzhong.sample.serve.sys.service.impl;

import com.tangzhong.sample.framework.mybatis.service.impl.BaseManageServiceImpl;
import com.tangzhong.sample.serve.sys.converter.SysOperateLogConverter;
import com.tangzhong.sample.serve.sys.entity.SysOperateLog;
import com.tangzhong.sample.serve.sys.mapper.SysOperateLogMapper;
import com.tangzhong.sample.serve.sys.pojo.dto.SysOperateLogDTO;
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
public class SysOperateLogServiceImpl extends BaseManageServiceImpl<SysOperateLog, SysOperateLogDTO, SysOperateLogConverter, SysOperateLogMapper> implements ISysOperateLogService {

    @Override
    @Async("logExecutor")
    public void addAsync(SysOperateLogDTO dto) {
        SysOperateLog entity = entityConverter.toEntity(dto);
        super.save(entity);
    }

}
package com.tangzhong.sample.serve.sys.converter;

import com.tangzhong.sample.framework.core.BaseConverter;
import com.tangzhong.sample.framework.core.log.OperateLogDTO;
import com.tangzhong.sample.serve.sys.entity.SysOperateLog;
import com.tangzhong.sample.serve.sys.api.vo.SysOperateLogDetailVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author tangzhong
 * @date   2026-06-12 11:40
 * @since  V1.0.0
 */
@Mapper(componentModel = "spring")
public interface SysOperateLogConverter extends BaseConverter<SysOperateLog, SysOperateLogDetailVO> {

    SysOperateLogConverter INSTANCE = Mappers.getMapper(SysOperateLogConverter.class);

    SysOperateLog toEntity(OperateLogDTO dto);

}

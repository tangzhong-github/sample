package com.tangzhong.sample.serve.sys.converter;

import com.tangzhong.sample.framework.core.BaseConverter;
import com.tangzhong.sample.serve.sys.entity.SysRole;
import com.tangzhong.sample.serve.sys.pojo.vo.SysRoleDetailVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author tangzhong
 * @date   2026-06-12 11:40
 * @since  V1.0.0
 */
@Mapper(componentModel = "spring")
public interface SysRoleConverter extends BaseConverter<SysRole, SysRoleDetailVO> {

    SysRoleConverter INSTANCE = Mappers.getMapper(SysRoleConverter.class);

}

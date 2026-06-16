package com.tangzhong.sample.serve.sys.converter;

import com.tangzhong.sample.framework.core.BaseConverter;
import com.tangzhong.sample.serve.sys.entity.SysUser;
import com.tangzhong.sample.serve.sys.pojo.vo.SysUserDetailVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author tangzhong
 * @date   2026-06-12 11:40
 * @since  V1.0.0
 */
@Mapper(componentModel = "spring")
public interface SysUserConverter extends BaseConverter<SysUser, SysUserDetailVO> {

    SysUserConverter INSTANCE = Mappers.getMapper(SysUserConverter.class);

}

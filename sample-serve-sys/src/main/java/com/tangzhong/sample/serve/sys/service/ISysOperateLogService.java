package com.tangzhong.sample.serve.sys.service;

import com.tangzhong.sample.framework.mybatis.service.IBaseManageService;
import com.tangzhong.sample.serve.sys.entity.SysOperateLog;
import com.tangzhong.sample.serve.sys.pojo.dto.SysOperateLogDTO;

/**
 *
 * @author tangzhong
 * @date   2026-06-04 14:12
 * @since  V1.0.0
 */
public interface ISysOperateLogService extends IBaseManageService<SysOperateLog, SysOperateLogDTO> {

    void addAsync(SysOperateLogDTO dto);

}

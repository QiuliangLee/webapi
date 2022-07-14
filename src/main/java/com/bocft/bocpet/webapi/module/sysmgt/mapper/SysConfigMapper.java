package com.bocft.bocpet.webapi.module.sysmgt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bocft.bocpet.webapi.module.sysmgt.entity.SysConfig;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author liuzhe
 * @since 2020-09-30
 */
@Repository
@Mapper
public interface SysConfigMapper extends BaseMapper<SysConfig> {

}

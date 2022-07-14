package com.bocft.bocpet.webapi.module.sms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bocft.bocpet.webapi.module.sms.entity.SysSms;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author liuzhe
 * @since 2020-10-27
 */
@Mapper
@Repository
public interface SysSmsMapper extends BaseMapper<SysSms> {

}

package com.bocft.bocpet.webapi.module.sysmgt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bocft.bocpet.webapi.module.sysmgt.entity.SysDict;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author 8902882
 * @since 2021-10-26
 */
@Repository
@Mapper
public interface SysDictMapper extends BaseMapper<SysDict> {

}

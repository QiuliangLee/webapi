package com.bocft.bocpet.webapi.module.sysmgt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bocft.bocpet.webapi.module.sysmgt.entity.SysFile;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 文件信息表 Mapper 接口
 * </p>
 *
 * @author liuzhe
 * @since 2020-10-14
 */
@Repository
@Mapper
public interface SysFileMapper extends BaseMapper<SysFile> {

}

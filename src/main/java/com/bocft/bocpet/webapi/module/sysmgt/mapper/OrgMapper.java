package com.bocft.bocpet.webapi.module.sysmgt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bocft.bocpet.webapi.module.sysmgt.entity.Org;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author liuzhe
 * @since 2020-09-24
 */
@Repository
@Mapper
public interface OrgMapper extends BaseMapper<Org> {

    Boolean ifConflictOrgName(@Param("orgName") String orgName);
}

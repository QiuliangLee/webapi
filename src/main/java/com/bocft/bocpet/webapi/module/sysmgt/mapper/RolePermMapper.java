package com.bocft.bocpet.webapi.module.sysmgt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bocft.bocpet.webapi.module.sysmgt.entity.RolePerm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
@Mapper
public interface RolePermMapper extends BaseMapper<RolePerm> {
    int deleteByPermSet(@Param("roleId") Integer roleId, @Param("permIds") Set<Integer> permIds);
}
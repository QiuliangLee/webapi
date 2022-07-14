package com.bocft.bocpet.webapi.module.sysmgt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bocft.bocpet.webapi.module.sysmgt.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {

    int deleteByRoleSet(@Param("userId") Integer userId, @Param("roleIds") Set<Integer> roleIds);

    int deleteAll(List<UserRole> records);
}
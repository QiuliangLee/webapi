package com.bocft.bocpet.webapi.module.sysmgt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bocft.bocpet.webapi.module.sysmgt.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    Page<Role> selectByUid(Page page, @Param("uid") Integer uid);

    List<Role> selectByUid(Integer uid);

}
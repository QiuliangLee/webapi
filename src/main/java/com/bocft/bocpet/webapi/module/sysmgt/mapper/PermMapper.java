package com.bocft.bocpet.webapi.module.sysmgt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bocft.bocpet.webapi.module.sysmgt.entity.Perm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PermMapper extends BaseMapper<Perm> {

    List<Perm> selectByUid(Integer uid);

    Page<Perm> selectByRid(Page page, @Param("rid") Integer rid);

    List<Perm> selectByRid(Integer rid);
}
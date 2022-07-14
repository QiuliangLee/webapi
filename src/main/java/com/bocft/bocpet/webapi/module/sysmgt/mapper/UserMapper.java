package com.bocft.bocpet.webapi.module.sysmgt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bocft.bocpet.webapi.module.sysmgt.entity.User;
import com.bocft.bocpet.webapi.module.sysmgt.entity.UserVO;
import com.bocft.bocpet.webapi.module.sysmgt.param.UserSelectParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper extends BaseMapper<User> {

    Page<User> selectByRole(Page page, @Param("rid") Integer roleId);

    Page<UserVO> selectUserVO(Page page, @Param("user") UserSelectParam user);
}
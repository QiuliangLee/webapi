package com.bocft.bocpet.webapi.module.petmgt.mapper;

import com.bocft.bocpet.webapi.module.petmgt.entity.ClientUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Lucas
 * @create 2022-07-25 0:01
 */
@Repository
@Mapper
public interface ClientUserMapper {
    int insertClientUser(ClientUser clientUser);
}

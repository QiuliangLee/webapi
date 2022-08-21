package com.bocft.bocpet.webapi.module.petmgt.mapper;

import com.bocft.bocpet.webapi.module.petmgt.entity.Insurance;
import com.bocft.bocpet.webapi.module.petmgt.entity.PetCommunity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Lucas
 * @create 2022-08-17 22:18
 */
@Repository
@Mapper
public interface PetCommunityMapper {
    public List<PetCommunity> selectAllPetCommunity();
}
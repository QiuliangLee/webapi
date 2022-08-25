package com.bocft.bocpet.webapi.module.petmgt.mapper;

import com.bocft.bocpet.webapi.module.petmgt.entity.Pet;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Lucas
 * @create 2022-07-24 22:03
 */
@Repository
@Mapper
public interface PetMapper {
    int insert(Pet pet);

    List<Pet> select();

    List<Pet> selectByTypeAndGender(@Param("type")String type, @Param("gender")String gender);

    int update(Pet pet);
}

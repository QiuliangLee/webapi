package com.bocft.bocpet.webapi.module.petmgt.mapper;

import com.bocft.bocpet.webapi.module.petmgt.entity.Pet;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Lucas
 * @create 2022-07-24 22:03
 */
@Repository
@Mapper
public interface PetMapper {
//    int deleteById(String id);
//
    int insertPet(Pet pet);
//
//    Pet selectById(String id);
//
//    int updatePet(Pet pet);

    /**
     * 查询所有的宠物
     *
     * @return
     */
    List<Pet> selectAllPets();
}

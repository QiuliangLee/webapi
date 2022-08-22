package com.bocft.bocpet.webapi.module.petmgt.service;

import com.bocft.bocpet.webapi.module.petmgt.entity.Pet;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Lucas
 * @create 2022-07-24 22:21
 */
public interface PetService {
    List<Pet> select();
    List<Pet> selectByTypeAndGender(String type, String gender);
    int insert(Pet pet);
    int update(Pet pet);
}

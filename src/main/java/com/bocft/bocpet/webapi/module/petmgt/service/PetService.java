package com.bocft.bocpet.webapi.module.petmgt.service;

import com.bocft.bocpet.webapi.module.petmgt.entity.Pet;

import java.util.List;

/**
 * @author Lucas
 * @create 2022-07-24 22:21
 */
public interface PetService {
    List<Pet> queryAllPets();
    int addPet(Pet pet);
}

package com.bocft.bocpet.webapi.module.petmgt.service.impl;

import com.bocft.bocpet.webapi.module.petmgt.entity.Pet;
import com.bocft.bocpet.webapi.module.petmgt.mapper.PetMapper;
import com.bocft.bocpet.webapi.module.petmgt.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Lucas
 * @create 2022-07-24 22:21
 */
@Service
public class PetServiceImpl implements PetService {
    @Autowired
    PetMapper petMapper;

    @Override
    public List<Pet> select() {
        return petMapper.select();
    }

    @Override
    public List<Pet> selectByTypeAndGender(String type, String gender) {
        return petMapper.selectByTypeAndGender(type,gender);
    }

    @Override
    public int insert(Pet pet) {
        return petMapper.insert(pet);
    }

    @Override
    public int update(Pet pet) {
        return petMapper.update(pet);
    }
}

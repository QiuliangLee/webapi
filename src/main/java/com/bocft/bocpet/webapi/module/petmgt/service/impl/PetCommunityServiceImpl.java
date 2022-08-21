package com.bocft.bocpet.webapi.module.petmgt.service.impl;

import com.bocft.bocpet.webapi.module.petmgt.entity.PetCommunity;
import com.bocft.bocpet.webapi.module.petmgt.mapper.PetCommunityMapper;
import com.bocft.bocpet.webapi.module.petmgt.service.PetCommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Lucas
 * @create 2022-08-17 22:27
 */
@Service
public class PetCommunityServiceImpl implements PetCommunityService {
    @Autowired
    PetCommunityMapper petCommunityMapper;
    @Override
    public List<PetCommunity> queryAllPetCommunity() {
        return petCommunityMapper.selectAllPetCommunity();
    }
}

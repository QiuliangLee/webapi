package com.bocft.bocpet.webapi.module.petmgt.service;

import com.bocft.bocpet.webapi.module.petmgt.entity.PetCommunity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Lucas
 * @create 2022-08-17 22:26
 */
public interface PetCommunityService {
    public List<PetCommunity> queryAllPetCommunity();
}

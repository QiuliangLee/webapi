package com.bocft.bocpet.webapi.module.petmgt.controller;

import com.bocft.bocpet.webapi.common.pojo.Result;
import com.bocft.bocpet.webapi.module.petmgt.entity.Pet;
import com.bocft.bocpet.webapi.module.petmgt.entity.PetCommunity;
import com.bocft.bocpet.webapi.module.petmgt.service.PetCommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Lucas
 * @create 2022-08-17 22:28
 */
@RestController
@RequestMapping("/petCommunity")
public class PetCommunityController {
    @Autowired
    PetCommunityService petCommunityService;
    @RequestMapping("/getPetCommunityList")
    Result getPetCommunityList() {
        List<PetCommunity> petCommunityList = petCommunityService.queryAllPetCommunity();
        return Result.suc().putData("list", petCommunityList)
                .putData("total", petCommunityList.size());
    }
}

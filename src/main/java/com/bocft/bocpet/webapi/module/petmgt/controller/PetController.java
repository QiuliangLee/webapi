package com.bocft.bocpet.webapi.module.petmgt.controller;

import com.bocft.bocpet.webapi.common.annotation.OperLog;
import com.bocft.bocpet.webapi.common.enums.ResultCodeEnum;
import com.bocft.bocpet.webapi.module.petmgt.entity.Pet;
import com.bocft.bocpet.webapi.module.petmgt.service.PetService;
import com.bocft.bocpet.webapi.common.pojo.Result;
import com.bocft.bocpet.webapi.module.sysmgt.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @author Lucas
 * @create 2022-07-24 21:00
 */

@RestController
@RequestMapping("/pet")
public class PetController {
    @Autowired
    PetService petService;

    /**
     * 增加宠物
     *
     * @param pet
     * @return
     */
    @RequestMapping("/addPet")
    Result addPet(Pet pet) {
        int success = petService.addPet(pet);
        if (success == 1) {
            return Result.suc().putData("添加成功", null);
        } else {
            return Result.err(ResultCodeEnum.CREATE_FAILED);
        }
    }

    /**
     * 获取所有宠物
     *
     * @return
     */
    @RequestMapping("/getList")
    Result getPetList() {
        List<Pet> pets = petService.queryAllPets();
        return Result.suc().putData("list", pets)
                .putData("total", pets.size());
    }

    /**
     * 根据类型和性别获取宠物
     *
     * @param type
     * @param gender
     * @return
     */
    @RequestMapping("/getPetListByTypeAndGender")
    Result getPetListByTypeAndGender(String type, String gender) {
        List<Pet> pets = petService.quearyPetsByTypeAndGender(type, gender);
        return Result.suc().putData("list", pets)
                .putData("total", pets.size());
    }

    @RequestMapping("updatePetByIs_adopt")
    Result updatePetByIs_adopt(String id) {
        petService.updateIs_adopt(id);
        return Result.suc();
    }

}

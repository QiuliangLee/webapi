package com.bocft.bocpet.webapi.module.petmgt.controller;

import com.bocft.bocpet.webapi.common.enums.ResultCodeEnum;
import com.bocft.bocpet.webapi.common.pojo.Result;
import com.bocft.bocpet.webapi.module.petmgt.entity.Pet;
import com.bocft.bocpet.webapi.module.petmgt.mapper.PetMapper;
import com.bocft.bocpet.webapi.module.petmgt.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Lucas
 * @create 2022-07-24 21:00
 */

@RestController
@RequestMapping("/pet")
public class PetController {
    @Autowired
    PetService petService;

    @RequestMapping("/insert")
    Result insert(Pet pet) {
        int success = petService.insert(pet);
        if (success == 1) {
            return Result.suc().putData("添加成功", null);
        } else {
            return Result.err(ResultCodeEnum.CREATE_FAILED);
        }
    }

    @RequestMapping("/select")
    Result select() {
        List<Pet> pets = petService.select();
        return Result.suc().putData("list", pets)
                .putData("total", pets.size());
    }

    @RequestMapping("/selectByTypeAndGender")
    Result selectByTypeAndGender(String type, String gender) {
        List<Pet> pets = petService.selectByTypeAndGender(type, gender);
        return Result.suc().putData("list", pets)
                .putData("total", pets.size());
    }
    @RequestMapping("update")
    Result update(Pet pet) {
        int success=petService.update( pet);
        if (success == 1) {
            return Result.suc().putData("修改成功", null);
        } else {
            return Result.err(ResultCodeEnum.CREATE_FAILED);
        }
    }
}

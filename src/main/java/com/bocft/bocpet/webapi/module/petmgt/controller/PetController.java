package com.bocft.bocpet.webapi.module.petmgt.controller;

import com.bocft.bocpet.webapi.module.petmgt.entity.Pet;
import com.bocft.bocpet.webapi.module.petmgt.service.PetService;
import com.bocft.bocpet.webapi.common.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/getList")
    Result getPetList() {
        List<Pet> pets = petService.queryAllPets();
        return Result.suc().putData("list", pets)
                .putData("total", pets.size());
    }
}

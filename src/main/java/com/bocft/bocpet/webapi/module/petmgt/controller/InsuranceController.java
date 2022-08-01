package com.bocft.bocpet.webapi.module.petmgt.controller;

import com.bocft.bocpet.webapi.common.pojo.Result;
import com.bocft.bocpet.webapi.module.petmgt.entity.Insurance;
import com.bocft.bocpet.webapi.module.petmgt.entity.Pet;
import com.bocft.bocpet.webapi.module.petmgt.service.InsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Lucas
 * @create 2022-07-28 22:15
 */
@RestController
@RequestMapping("insurance")
public class InsuranceController {
    @Autowired
    InsuranceService insuranceService;

    @RequestMapping("/getInsuranceList")
    Result getInsuranceList() {
        List<Insurance> insuranceList = insuranceService.getInsuranceList();
        return Result.suc().putData("list", insuranceList)
                .putData("total", insuranceList.size());
    }
}

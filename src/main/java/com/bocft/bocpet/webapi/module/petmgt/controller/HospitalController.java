package com.bocft.bocpet.webapi.module.petmgt.controller;

import com.bocft.bocpet.webapi.common.enums.ResultCodeEnum;
import com.bocft.bocpet.webapi.common.pojo.Result;
import com.bocft.bocpet.webapi.module.petmgt.entity.Hospital;
import com.bocft.bocpet.webapi.module.petmgt.entity.Project;
import com.bocft.bocpet.webapi.module.petmgt.service.HospitalService;
import org.apache.catalina.Host;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Lucas
 * @create 2022-08-22 23:17
 */
@RestController
@RequestMapping("/hospital")
public class HospitalController {
    @Autowired
    HospitalService hospitalService;

    @RequestMapping("/insert")
    public Result insert(Hospital hospital) {
        int success = hospitalService.insert(hospital);
        if (success == 1) {
            return Result.suc().putData("添加成功", null);
        } else {
            return Result.err(ResultCodeEnum.CREATE_FAILED);
        }
    }
    @RequestMapping("/delete")
    public Result delete(Integer id) {
        int success = hospitalService.delete(id);
        if (success == 1) {
            return Result.suc().putData("删除成功", null);
        } else {
            return Result.err(ResultCodeEnum.CREATE_FAILED);
        }
    }

    @RequestMapping("/select")
    public Result select() {
        List<Hospital> hospitals = hospitalService.select();
        return Result.suc().putData("list", hospitals)
                .putData("total", hospitals.size());
    }

    @RequestMapping("update")
    public Result update(Integer id, String name) {
        int success = hospitalService.update(id, name);
        if (success == 1) {
            return Result.suc().putData("修改成功", null);
        } else {
            return Result.err(ResultCodeEnum.CREATE_FAILED);
        }
    }
}

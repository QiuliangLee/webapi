package com.bocft.bocpet.webapi.module.petmgt.controller;

import com.bocft.bocpet.webapi.common.enums.ResultCodeEnum;
import com.bocft.bocpet.webapi.common.pojo.Result;
import com.bocft.bocpet.webapi.module.petmgt.entity.Hospital;
import com.bocft.bocpet.webapi.module.petmgt.entity.HospitalProduct;
import com.bocft.bocpet.webapi.module.petmgt.entity.Project;
import com.bocft.bocpet.webapi.module.petmgt.service.HospitalProductService;
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
@RequestMapping("/hospitalProduct")
public class HospitalProductController {
    @Autowired
    HospitalProductService hospitalProductService;

    @RequestMapping("/insert")
    public Result insert(HospitalProduct hospitalProduct) {
        int success = hospitalProductService.insert(hospitalProduct);
        if (success == 1) {
            return Result.suc().putData("添加成功", null);
        } else {
            return Result.err(ResultCodeEnum.CREATE_FAILED);
        }
    }
    @RequestMapping("/delete")
    public Result delete(Integer id) {
        int success = hospitalProductService.delete(id);
        if (success == 1) {
            return Result.suc().putData("删除成功", null);
        } else {
            return Result.err(ResultCodeEnum.CREATE_FAILED);
        }
    }

    @RequestMapping("/select")
    public Result select() {
        List<HospitalProduct> hospitalProducts = hospitalProductService.select();
        return Result.suc().putData("list", hospitalProducts)
                .putData("total", hospitalProducts.size());
    }

    @RequestMapping("update")
    public Result update(Integer id, String name) {
        int success = hospitalProductService.update(id, name);
        if (success == 1) {
            return Result.suc().putData("修改成功", null);
        } else {
            return Result.err(ResultCodeEnum.CREATE_FAILED);
        }
    }
}

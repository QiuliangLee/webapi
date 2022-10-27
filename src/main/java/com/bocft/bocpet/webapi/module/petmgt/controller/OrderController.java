package com.bocft.bocpet.webapi.module.petmgt.controller;

import com.bocft.bocpet.webapi.common.enums.ResultCodeEnum;
import com.bocft.bocpet.webapi.common.pojo.Result;
import com.bocft.bocpet.webapi.module.petmgt.entity.Order;
import com.bocft.bocpet.webapi.module.petmgt.entity.Pet;
import com.bocft.bocpet.webapi.module.petmgt.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Lucas
 * @create 2022-07-24 21:00
 */

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderServiceImpl orderService;

    @RequestMapping("/insert")
    Result insert(Order order) {
        int success = orderService.insert(order);
        if (success == 1) {
            return Result.suc().putData("添加成功", null);
        } else {
            return Result.err(ResultCodeEnum.CREATE_FAILED);
        }
    }

    @RequestMapping("/select")
    Result select() {
        List<Order> orders = orderService.select();
        return Result.suc().putData("list", orders)
                .putData("total", orders.size());
    }

    @RequestMapping("update")
    Result update(Order order) {
        int success = orderService.update(order);
        if (success == 1) {
            return Result.suc().putData("修改成功", null);
        } else {
            return Result.err(ResultCodeEnum.CREATE_FAILED);
        }
    }
}

package com.bocft.bocpet.webapi.module.petmgt.service.impl;

import com.bocft.bocpet.webapi.module.petmgt.entity.Order;
import com.bocft.bocpet.webapi.module.petmgt.entity.Pet;
import com.bocft.bocpet.webapi.module.petmgt.mapper.OrderMapper;
import com.bocft.bocpet.webapi.module.petmgt.mapper.PetMapper;
import com.bocft.bocpet.webapi.module.petmgt.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Lucas
 * @create 2022-09-22 14:17
 */
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;

    @Override
    public List<Order> select() {
        return orderMapper.select();
    }

    @Override
    public int insert(Order order) {
        return orderMapper.insert(order);
    }

    @Override
    public int update(Order order) {
        return orderMapper.update(order);
    }
}

package com.bocft.bocpet.webapi.module.petmgt.service;

import com.bocft.bocpet.webapi.module.petmgt.entity.Order;
import com.bocft.bocpet.webapi.module.petmgt.entity.Pet;

import java.util.List;

/**
 * @author Lucas
 * @create 2022-09-22 14:18
 */
public interface OrderService {
    public List<Order> select();

    public int insert(Order order);

    public int update(Order order);
}

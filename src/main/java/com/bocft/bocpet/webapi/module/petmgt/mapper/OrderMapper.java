package com.bocft.bocpet.webapi.module.petmgt.mapper;

import com.bocft.bocpet.webapi.module.petmgt.entity.Order;
import com.bocft.bocpet.webapi.module.petmgt.entity.Pet;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Lucas
 * @create 2022-07-24 22:03
 */
@Repository
@Mapper
public interface OrderMapper {
    public int insert(Order order);

    public List<Order> select();

    public int update(Order order);
}

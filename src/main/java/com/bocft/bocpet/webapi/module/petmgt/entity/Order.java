package com.bocft.bocpet.webapi.module.petmgt.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Lucas
 * @create 2022-09-22 14:14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {
    @TableId
    protected Integer id;
    protected String user_id;
    protected String name;
    protected double price;
    protected int num;
}
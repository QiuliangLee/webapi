package com.bocft.bocpet.webapi.module.petmgt.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Lucas
 * @create 2022-07-25 0:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientUser implements Serializable {
    @TableId
    protected String id;
    protected String name;
    protected String phone;
    protected String address;
    protected Pet pet;
}

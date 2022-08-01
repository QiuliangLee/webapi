package com.bocft.bocpet.webapi.module.petmgt.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Lucas
 * @create 2022-07-28 22:07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Insurance {
    private String id;
    private String name;
    private String desc;
    private String price;
    private String info;
}

package com.bocft.bocpet.webapi.module.petmgt.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Lucas
 * @create 2022-08-22 22:47
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HospitalProduct {
    private Integer id;
    private String name;
    private String hospitalName;
    private String pet;
    private String user;
    private String originalPrice;
    private String discountPrice;
    private String image;
}

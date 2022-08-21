package com.bocft.bocpet.webapi.module.petmgt.entity;

import lombok.Data;

/**
 * @author Lucas
 * @create 2022-08-17 22:16
 */
@Data
public class PetCommunity {
    private Integer id;
    private String user;
    private String name;
    private String tag;
    private String desc;
    private String num;
}

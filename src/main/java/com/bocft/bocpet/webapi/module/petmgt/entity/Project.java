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
public class Project {
    private Integer id;
    private String user;
    private String title;
    private String location;
    private String desc;
    private String intro;
    private String tag;
    private String collection_agency;
    private String executive_agency;
    private String num;
    private String image;
}

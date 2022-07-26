package com.bocft.bocpet.webapi.module.petmgt.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Lucas
 * @create 2022-07-24 21:34
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pet implements Serializable {
    @TableId
    protected String id;
    protected String name;
    protected String desc;
    protected String gender;
    protected String age;
    protected String weight;
    protected String birth_day;
    protected String create_time;
    protected String health_info;
    protected String type;
    protected String image;
    protected String isadopt;
}

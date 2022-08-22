package com.bocft.bocpet.webapi.module.petmgt.service;

import com.bocft.bocpet.webapi.module.petmgt.entity.Hospital;
import com.bocft.bocpet.webapi.module.petmgt.entity.HospitalProduct;
import com.bocft.bocpet.webapi.module.petmgt.entity.Project;
import org.apache.catalina.Host;

import java.util.List;

/**
 * @author Lucas
 * @create 2022-08-22 23:10
 */
public interface HospitalService {
    public List<Hospital> select();
    public int insert(Hospital hospital);
    public int update(Integer id, String name);
    public int delete(Integer id);
}

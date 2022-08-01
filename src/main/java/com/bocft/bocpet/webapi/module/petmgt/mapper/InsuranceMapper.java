package com.bocft.bocpet.webapi.module.petmgt.mapper;

import com.bocft.bocpet.webapi.module.petmgt.entity.Insurance;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Lucas
 * @create 2022-07-28 22:10
 */
@Repository
@Mapper
public interface InsuranceMapper {
    public List<Insurance> selectInsuranceList();
}

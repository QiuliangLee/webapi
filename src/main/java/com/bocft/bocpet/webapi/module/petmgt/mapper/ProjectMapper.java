package com.bocft.bocpet.webapi.module.petmgt.mapper;

import com.bocft.bocpet.webapi.module.petmgt.entity.Pet;
import com.bocft.bocpet.webapi.module.petmgt.entity.Project;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Lucas
 * @create 2022-08-22 22:54
 */
@Repository
@Mapper
public interface ProjectMapper {
    public int insertProject(Project project);

    public int deleteProjectById(int id);

    public int updateProject(Integer id, String name);

    public List<Project> selectAllProject();
}

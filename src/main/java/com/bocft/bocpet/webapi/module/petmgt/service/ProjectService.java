package com.bocft.bocpet.webapi.module.petmgt.service;

import com.bocft.bocpet.webapi.module.petmgt.entity.Project;

import java.util.List;

/**
 * @author Lucas
 * @create 2022-08-22 23:10
 */
public interface ProjectService {
    public List<Project> selectAllProject();
    public int insertProject(Project project);
    public int updateProject(Integer id, String name);
    public int deleteProjectById(Integer id);
}

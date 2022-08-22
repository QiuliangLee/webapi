package com.bocft.bocpet.webapi.module.petmgt.controller;

import com.bocft.bocpet.webapi.common.enums.ResultCodeEnum;
import com.bocft.bocpet.webapi.common.pojo.Result;
import com.bocft.bocpet.webapi.module.petmgt.entity.Project;
import com.bocft.bocpet.webapi.module.petmgt.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Lucas
 * @create 2022-08-22 23:17
 */
@RestController
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    ProjectService projectService;

    @RequestMapping("/insertProject")
    public Result insertProject(Project project) {
        int success = projectService.insertProject(project);
        return Result.suc();
    }
    @RequestMapping("/deleteProjectById")
    public Result deleteProjectById(Integer id) {
        int success = projectService.deleteProjectById(id);
        return Result.suc();
    }

    @RequestMapping("/selectAllProject")
    public Result selectAllProject() {
        List<Project> projects = projectService.selectAllProject();
        return Result.suc().putData("list", projects)
                .putData("total", projects.size());
    }

    @RequestMapping("updateProject")
    public Result updateProject(Integer id, String name) {
        projectService.updateProject(id, name);
        return Result.suc();
    }
}

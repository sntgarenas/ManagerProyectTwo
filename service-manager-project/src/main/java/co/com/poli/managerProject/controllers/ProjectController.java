package co.com.poli.managerProject.controllers;

import co.com.poli.managerProject.entities.Project;
import co.com.poli.managerProject.helpers.ResponseBuilder;
import co.com.poli.managerProject.services.project.IProjectService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import co.com.poli.managerProject.helpers.Response;

import java.util.List;

@RestController
@RequestMapping("/project")
@AllArgsConstructor
public class ProjectController {

    private IProjectService iProjectService;
    private ResponseBuilder responseBuilder;

    @GetMapping()
    public Response findAll() {
        List<Project> projects = iProjectService.findAll();

        if (projects.isEmpty()) {
            return responseBuilder.failed("Not are projects");
        }

        return responseBuilder.success(projects);
    }
}

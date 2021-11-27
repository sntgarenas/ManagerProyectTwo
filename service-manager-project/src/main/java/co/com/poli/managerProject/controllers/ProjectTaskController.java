package co.com.poli.managerProject.controllers;

import co.com.poli.managerProject.entities.ProjectTask;
import co.com.poli.managerProject.helpers.Response;
import co.com.poli.managerProject.helpers.ResponseBuilder;
import co.com.poli.managerProject.services.projectTask.IProjectTaskService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/task")
@AllArgsConstructor
public class ProjectTaskController {

    private final IProjectTaskService projectTaskService;
    private final ResponseBuilder responseBuilder;
    private final ModelMapper modelMapper;

    @GetMapping()
    public ResponseEntity<Response> findAll() {
        List<ProjectTask> projectTasks = projectTaskService.findAll();

        if (projectTasks.isEmpty()) {
            return new ResponseEntity<>(responseBuilder.failed("Not are project tasks"),
                    HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(responseBuilder.success(projectTasks),
                HttpStatus.OK);
    }
}

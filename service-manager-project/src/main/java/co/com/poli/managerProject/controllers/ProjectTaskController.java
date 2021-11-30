package co.com.poli.managerProject.controllers;

import co.com.poli.managerProject.dto.ProjectTaskDto;
import co.com.poli.managerProject.entities.Backlog;
import co.com.poli.managerProject.entities.ProjectTask;
import co.com.poli.managerProject.helpers.FormatMessage;
import co.com.poli.managerProject.helpers.Response;
import co.com.poli.managerProject.helpers.ResponseBuilder;
import co.com.poli.managerProject.services.projectTask.IProjectTaskService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/task")
@AllArgsConstructor
public class ProjectTaskController {

    private final IProjectTaskService projectTaskService;
    private final ResponseBuilder responseBuilder;
    private final ModelMapper modelMapper;

    @GetMapping()
    public ResponseEntity<Response> findAll(HttpServletRequest request) {
        List<ProjectTask> projectTasks = projectTaskService.findAll();

        if (projectTasks.isEmpty()) {
            return new ResponseEntity<>(responseBuilder.failed("Not are project tasks", request.getRequestURI()),
                    HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(responseBuilder.success(projectTasks, request.getRequestURI()),
                HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Response> save(@Valid @RequestBody ProjectTaskDto projectTaskDto, BindingResult result, HttpServletRequest request) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(responseBuilder.failed(FormatMessage.formatMessage(result), request.getRequestURI()),
                    HttpStatus.BAD_REQUEST);
        } else {
            ProjectTask projectTask = projectTaskService.findProjectTaskByProjectIdentifierOrBacklog(projectTaskDto.getProjectIdentifier(),
                    projectTaskDto.getBacklog());

            if (projectTask != null) {
                return new ResponseEntity<>(responseBuilder.failed("El identificador ya existe o el backlog no existe", request.getRequestURI()),
                        HttpStatus.BAD_REQUEST);
            }
        }

        ProjectTask projectTask = modelMapper.map(projectTaskDto, ProjectTask.class);
        projectTaskService.save(projectTask);

        return new ResponseEntity<>(responseBuilder.successCreated(projectTask, request.getRequestURI()),
                HttpStatus.CREATED);
    }
}

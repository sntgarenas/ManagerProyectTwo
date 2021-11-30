package co.com.poli.managerProject.controllers;

import co.com.poli.managerProject.dto.ProjectDto;
import co.com.poli.managerProject.entities.Project;
import co.com.poli.managerProject.helpers.FormatMessage;
import co.com.poli.managerProject.helpers.ResponseBuilder;
import co.com.poli.managerProject.services.project.IProjectService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import co.com.poli.managerProject.helpers.Response;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/project")
@AllArgsConstructor
public class ProjectController {

    private IProjectService iProjectService;
    private ResponseBuilder responseBuilder;
    private final ModelMapper modelMapper;

    @GetMapping()
    public Response findAll(HttpServletRequest request) {
        List<Project> projects = iProjectService.findAll();

        if (projects.isEmpty()) {
            return responseBuilder.failed("Not are projects", request.getRequestURI());
        }

        return responseBuilder.success(projects, request.getRequestURI());
    }

    @PostMapping()
    public ResponseEntity<Response> save(@Valid @RequestBody ProjectDto projectDto, BindingResult result, HttpServletRequest request) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(responseBuilder.failed(FormatMessage.formatMessage(result), request.getRequestURI()),
                    HttpStatus.BAD_REQUEST);
        }

        Project project = modelMapper.map(projectDto, Project.class);

        if (iProjectService.findProjectByProjectNameOrProjectIdentifier(project.getProjectName(), project.getProjectIdentifier()) != null) {
            return new ResponseEntity<>(responseBuilder.failed("Nombre del proyecto o identificador del proyecto ya existen", request.getRequestURI()),
                    HttpStatus.BAD_REQUEST);
        }

        iProjectService.save(project);

        return new ResponseEntity<>(responseBuilder.success(project, request.getRequestURI()),
                HttpStatus.CREATED);
    }
}

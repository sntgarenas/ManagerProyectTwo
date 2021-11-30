package co.com.poli.managerProject.controllers;

import co.com.poli.managerProject.dto.BacklogDto;
import co.com.poli.managerProject.entities.Backlog;
import co.com.poli.managerProject.entities.Project;
import co.com.poli.managerProject.helpers.FormatMessage;
import co.com.poli.managerProject.helpers.Response;
import co.com.poli.managerProject.helpers.ResponseBuilder;
import co.com.poli.managerProject.services.backlog.IBacklogService;
import co.com.poli.managerProject.services.project.IProjectService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/backlog")
@AllArgsConstructor
public class BacklogController {

    private final IProjectService iProjectService;
    private final IBacklogService iBacklogService;
    private final ResponseBuilder responseBuilder;
    private final ModelMapper modelMapper;

    @GetMapping()
    public ResponseEntity<Response> findAll(HttpServletRequest request) {
        List<Backlog> backlog = iBacklogService.findAll();

        if (backlog.isEmpty()) {
            return new ResponseEntity<>(responseBuilder.failed("Not are backlogs", request.getRequestURI()),
                    HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity<>(responseBuilder.success(backlog, request.getRequestURI()),
                HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Response> save(@Valid @RequestBody BacklogDto backlogDto, BindingResult result, HttpServletRequest request) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(responseBuilder.failed(FormatMessage.formatMessage(result), request.getRequestURI()),
                    HttpStatus.BAD_REQUEST);
        } else {
            Backlog backlog = iBacklogService.findBacklogByProjectIdentifier(backlogDto.getProjectIdentifier());

            if (backlog != null) {
                return new ResponseEntity<>(responseBuilder.failed("El identificador ya existe", request.getRequestURI()),
                        HttpStatus.BAD_REQUEST);
            }
        }

        Backlog backlog = modelMapper.map(backlogDto, Backlog.class);
        iBacklogService.save(backlog);

        return new ResponseEntity<>(responseBuilder.successCreated(backlog, request.getRequestURI()),
                HttpStatus.CREATED);
    }
}

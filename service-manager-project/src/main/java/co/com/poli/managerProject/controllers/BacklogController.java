package co.com.poli.managerProject.controllers;

import co.com.poli.managerProject.dto.BacklogDto;
import co.com.poli.managerProject.entities.Backlog;
import co.com.poli.managerProject.entities.ProjectTask;
import co.com.poli.managerProject.helpers.FormatMessage;
import co.com.poli.managerProject.helpers.Response;
import co.com.poli.managerProject.helpers.ResponseBuilder;
import co.com.poli.managerProject.services.backlog.IBacklogService;
import co.com.poli.managerProject.services.projectTask.IProjectTaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/backlog")
@AllArgsConstructor
public class BacklogController {

    private final IProjectTaskService projectTaskService;
    private final IBacklogService iBacklogService;
    private final ResponseBuilder responseBuilder;
    private final ModelMapper modelMapper;

    @GetMapping()
    public ResponseEntity<Response> findAll() {
        List<Backlog> backlog = iBacklogService.findAll();

        if (backlog.isEmpty()) {
            return new ResponseEntity<>(responseBuilder.failed("Not are backlogs"),
                    HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity<>(responseBuilder.success(backlog),
                HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Response> save(@Valid @RequestBody BacklogDto backlogDto, BindingResult result) {
        //ProjectTask projectTask = projectTaskService.findById(backlogDto);

        if (result.hasErrors()) {
            return new ResponseEntity<>(responseBuilder.failed(FormatMessage.formatMessage(result)),
                    HttpStatus.BAD_REQUEST);
        }

        Backlog backlog = modelMapper.map(backlogDto, Backlog.class);
        iBacklogService.save(backlog);

        return new ResponseEntity<>(responseBuilder.success(backlog),
                HttpStatus.CREATED);
    }
}

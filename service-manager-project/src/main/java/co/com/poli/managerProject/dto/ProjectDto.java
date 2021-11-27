package co.com.poli.managerProject.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class ProjectDto {

    @NotBlank(message = "Nombre del proyecto no puede estar vacio")
    private String projectName;

    @NotBlank(message = "Identificador del proyecto no puede estar vacio")
    private String projectIdentifier;

    @NotBlank(message = "Descripcion no puede estar vacia")
    private String description;

    private Date startDate;
    private Date endDate;
}

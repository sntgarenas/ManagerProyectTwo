package co.com.poli.managerProject.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class ProjectTaskDto {

    @NotBlank(message = "Nombre del proyecto no puede estar vacio")
    private String name;

    @NotBlank(message = "Resumen del proyecto no puede estar vacio")
    private String summary;

    private String acceptanceCriteria;
    private String status;

    @Range(min = 1, max = 8, message = "La prioridad no puede ser menor a 1 o mayor a 8")
    private int priority;

    @Range(min = 1, max = 8, message = "El numero de horas no puede ser menor a 1 o mayor a 8")
    private Double hours;

    private Date startDate;
    private Date endDate;

    @NotBlank(message = "Identificador del proyecto no puede estar vacio")
    private String projectIdentifier;
}

package co.com.poli.managerProject.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class BacklogDto {

    @NotBlank(message = "Identificador del proyecto no puede estar vacio")
    private String projectIdentifier;
}

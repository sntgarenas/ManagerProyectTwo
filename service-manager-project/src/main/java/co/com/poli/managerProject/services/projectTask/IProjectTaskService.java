package co.com.poli.managerProject.services.projectTask;

import co.com.poli.managerProject.entities.ProjectTask;

import java.util.List;

public interface IProjectTaskService {
    List<ProjectTask> findAll();
}

package co.com.poli.managerProject.services.projectTask;

import co.com.poli.managerProject.entities.Backlog;
import co.com.poli.managerProject.entities.ProjectTask;

import java.util.List;

public interface IProjectTaskService {
    List<ProjectTask> findAll();
    ProjectTask findById(Long id);
    void save(ProjectTask projectTask);
    ProjectTask findProjectTaskByProjectIdentifierOrBacklog(String projectIdentifier, Backlog backlog);
}

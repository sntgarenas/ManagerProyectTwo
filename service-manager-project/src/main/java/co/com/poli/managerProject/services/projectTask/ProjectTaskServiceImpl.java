package co.com.poli.managerProject.services.projectTask;

import co.com.poli.managerProject.entities.Backlog;
import co.com.poli.managerProject.entities.ProjectTask;
import co.com.poli.managerProject.repositories.IProjectTaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProjectTaskServiceImpl implements IProjectTaskService {

    private IProjectTaskRepository iProjectTaskRepository;

    @Override
    public List<ProjectTask> findAll() {
        return iProjectTaskRepository.findAll();
    }

    @Override
    public ProjectTask findById(Long id) {
        return iProjectTaskRepository.findById(id).orElse(null);
    }

    @Override
    public void save(ProjectTask projectTask) {
        iProjectTaskRepository.save(projectTask);
    }

    @Override
    public ProjectTask findProjectTaskByProjectIdentifierOrBacklog(String projectIdentifier, Backlog backlog) {
        return iProjectTaskRepository.findProjectTaskByProjectIdentifierOrBacklog(projectIdentifier, backlog);
    }

}

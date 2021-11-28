package co.com.poli.managerProject.services.project;

import co.com.poli.managerProject.entities.Project;
import co.com.poli.managerProject.repositories.IProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;
import java.util.List;

@Service
@AllArgsConstructor
public class ProjectServiceImpl implements IProjectService{

    private final IProjectRepository projectRepository;

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    public void save(Project project) {
        projectRepository.save(project);
    }

    @Override
    public Project findProjectByProjectNameOrProjectIdentifier(String projectName, String projectIdentifier) {
        return projectRepository.findProjectByProjectNameOrProjectIdentifier(projectName, projectIdentifier);
    }
}

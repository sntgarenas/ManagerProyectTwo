package co.com.poli.managerProject.repositories;

import co.com.poli.managerProject.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProjectRepository extends JpaRepository<Project, Long> {
    Project findProjectByProjectNameOrProjectIdentifier(String projectName, String projectIdentifier);
}

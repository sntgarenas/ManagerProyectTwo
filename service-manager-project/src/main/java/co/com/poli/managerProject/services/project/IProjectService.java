package co.com.poli.managerProject.services.project;

import co.com.poli.managerProject.entities.Project;

import java.util.List;

public interface IProjectService {
    List<Project> findAll();
}
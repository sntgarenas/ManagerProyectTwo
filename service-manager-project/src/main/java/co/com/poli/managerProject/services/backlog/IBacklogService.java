package co.com.poli.managerProject.services.backlog;

import co.com.poli.managerProject.entities.Backlog;

import java.util.List;

public interface IBacklogService {
    List<Backlog> findAll();
    void save(Backlog backlog);
}
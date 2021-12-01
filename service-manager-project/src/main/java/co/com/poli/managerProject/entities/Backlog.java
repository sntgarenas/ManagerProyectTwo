package co.com.poli.managerProject.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "backlog")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Backlog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "project_identifier", nullable = false)
    private String projectIdentifier;

    @JsonBackReference
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "project_id")
    @OneToOne(fetch = FetchType.LAZY)
    private Project project;

    @JsonManagedReference
    @OneToMany(mappedBy = "backlog", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<ProjectTask> projectTasks;
}

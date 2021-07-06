package com.telework.demo.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "project")
public class Project implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    /********** RELATIONS ************/
    @ManyToMany
    @JoinTable(name = "projects_developers_association",
            joinColumns = @JoinColumn(name = "idProject"),
            inverseJoinColumns = @JoinColumn(name = "idDeveloper"))
    private List<Developer> developers;

    @ManyToOne
    @JoinColumn(name = "projectManagerId")
    private ProjectManager projectManager;

}

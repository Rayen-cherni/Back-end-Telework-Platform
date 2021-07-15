package com.telework.demo.domain.entity;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
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
    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.JOIN)
    @JoinTable(name = "projects_developers_association",
            joinColumns = @JoinColumn(name = "idProject"),
            inverseJoinColumns = @JoinColumn(name = "idDeveloper"))
    private List<Developer> developers;

    @ManyToOne
    @JoinColumn(name = "projectManagerId")
    private ProjectManager projectManager;

}

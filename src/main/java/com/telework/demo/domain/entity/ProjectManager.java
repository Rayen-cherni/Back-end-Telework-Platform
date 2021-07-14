package com.telework.demo.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "projectManager")
public class ProjectManager extends User implements Serializable {

    /********** RELATIONS ************/
    @OneToMany(mappedBy = "projectManager",fetch = FetchType.EAGER)
    private List<Project> projects;

    /** IS NOT USED **/
    /*
    @OneToMany(fetch = FetchType.EAGER,
        mappedBy = "projectManager")
    private List<Demand> demands;
    */

}


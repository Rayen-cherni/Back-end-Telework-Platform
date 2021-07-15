package com.telework.demo.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "developer")
public class Developer extends User implements Serializable {

    /********** RELATIONS ************/

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "projects_developers_association",
            joinColumns = @JoinColumn(name = "idDeveloper"),
            inverseJoinColumns = @JoinColumn(name = "idProject"))
    @Fetch(value = FetchMode.JOIN)
    private List<Project> projects;

    @OneToMany(mappedBy = "developer",
            fetch = FetchType.EAGER,
            cascade = CascadeType.REMOVE)
    @Fetch(FetchMode.SUBSELECT)
    private List<Historique> historiques;

    /** IS NOT USED **/
      /*  @OneToMany(fetch = FetchType.EAGER,
            mappedBy = "developer")
    private List<Demand> demands;
    */
}

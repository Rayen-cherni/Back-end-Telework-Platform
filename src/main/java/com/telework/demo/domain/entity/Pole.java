package com.telework.demo.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "pole")
public class Pole implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    //THe number of places in the pole
    @Column(name = "capacity")
    private Integer capacity;

    //THe number of places reserved in the pole
    @Column(name = "reserved")
    private Integer reserved;


    /********** RELATIONS ************/
    @OneToOne
    private PoleManager poleManager;

}

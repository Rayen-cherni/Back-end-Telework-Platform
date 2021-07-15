package com.telework.demo.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.telework.demo.domain.entity.enumeration.Decision;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "historique")
public class Historique implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "startingDate")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startingDate;


    @Column(name = "deadline")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date deadline;

    @Column(name = "projectManagerDecision")
    private Decision projectManagerDecision;

    @Column(name = "poleManagerDecision")
    private Decision poleManagerDecision;


    /********** RELATIONS ************/
    @ManyToOne
    @JoinColumn(name = "DeveloperId")
    private Developer developer;


}

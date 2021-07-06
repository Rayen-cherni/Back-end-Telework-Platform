package com.telework.demo.domain.entity;

import com.telework.demo.domain.entity.enumeration.Decision;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@Table(name = "historique")
public class Historique implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "startingDate")
    private Instant startingDate;

    @Column(name = "deadline")
    private Instant deadline;

    @Column(name = "projectManagerDecision")
    private Decision projectManagerDecision;

    @Column(name = "poleManagerDecision")
    private Decision poleManagerDecision;


    /********** RELATIONS ************/
    @ManyToOne
    @JoinColumn(name = "DeveloperId")
    private Developer developer;


}

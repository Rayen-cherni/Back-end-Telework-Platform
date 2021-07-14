package com.telework.demo.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "poleManager")
public class PoleManager extends User implements Serializable {

    /********** RELATIONS ************/

    @OneToOne
    private Pole pole;


}

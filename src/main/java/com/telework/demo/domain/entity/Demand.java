

/** IS NOT USED **/

/*
package com.telework.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
@Table(name = "demand")
public class Demand implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "startingDate")
    private Instant startingDate;

    @Column(name = "deadline")
    private Instant deadline;

    @Column(name = "type")
    private Type type;

    @Column(name = "status")
    private DemandStatus status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "developerId")
    private Developer developer;

    @ManyToOne
    @JoinColumn(name = "projectManagerId")
    private ProjectManager projectManager;

}
*/


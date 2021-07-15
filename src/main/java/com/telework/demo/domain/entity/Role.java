package com.telework.demo.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "role")
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "roleName")
    private String roleName;

    @OneToMany(mappedBy = "role",
            fetch = FetchType.EAGER)
    private List<User> users;
}

package com.telework.demo.domain.entity;

import com.telework.demo.domain.entity.enumeration.UserStatus;
import com.telework.demo.domain.entity.enumeration.WithHoldingType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "telNum")
    private String telNum;

    @Column(name = "adress")
    private String adress;

    @Column(name = "status")
    private UserStatus userStatus;

    //Number of presential sessions
    @Column(name = "presential")
    private Integer presential;

    //Number of telework sessions
    @Column(name = "remote")
    private Integer remote;

    //Mean is the collab out of services
    @Column(name = "withHoldingType")
    private WithHoldingType withHoldingType;

    @ManyToOne
    @JoinColumn(name = "roleId")
    private Role role;
}

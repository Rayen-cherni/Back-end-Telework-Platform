package com.telework.demo.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
public class UpdateUserForm {

    private String firstname;

    private String lastname;

    private String telNum;

    private String adress;

}

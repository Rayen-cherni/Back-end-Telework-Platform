package com.telework.demo.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.telework.demo.domain.entity.enumeration.Decision;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class HistoriqueDto {

    private Integer id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startingDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date deadline;

    private Decision projectManagerDecision;

    private Decision poleManagerDecision;

    @JsonIgnore
    private DeveloperDto developer;

}

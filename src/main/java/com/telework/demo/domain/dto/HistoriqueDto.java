package com.telework.demo.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.telework.demo.domain.entity.enumeration.Decision;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class HistoriqueDto {

    private Integer id;

    private Instant startingDate;

    private Instant deadline;

    private Decision projectManagerDecision;

    private Decision poleManagerDecision;

    @JsonIgnore
    private DeveloperDto developer;

}

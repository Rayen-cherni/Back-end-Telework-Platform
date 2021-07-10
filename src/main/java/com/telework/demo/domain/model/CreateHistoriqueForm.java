package com.telework.demo.domain.model;

import com.telework.demo.domain.dto.DeveloperDto;
import com.telework.demo.domain.dto.HistoriqueDto;
import com.telework.demo.domain.entity.enumeration.Decision;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CreateHistoriqueForm {

    private Instant startingDate;

    private Instant deadline;

    private Decision projectManagerDecision;

    private Decision poleManagerDecision;

    private Integer developerId;

    public static HistoriqueDto convertToHistoriqueDto(CreateHistoriqueForm historiqueForm,
                                                       DeveloperDto developerDto) {

        HistoriqueDto historiqueDto = HistoriqueDto.builder()
                .startingDate(historiqueForm.getStartingDate())
                .deadline(historiqueForm.getDeadline())
                .projectManagerDecision(historiqueForm.getProjectManagerDecision())
                .poleManagerDecision(historiqueForm.getPoleManagerDecision())
                .developer(developerDto)
                .build();
        ;
        return historiqueDto;
    }
}

package com.telework.demo.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.telework.demo.domain.dto.DeveloperDto;
import com.telework.demo.domain.dto.HistoriqueDto;
import com.telework.demo.domain.entity.enumeration.Decision;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CreateHistoriqueForm {

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startingDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date deadline;

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

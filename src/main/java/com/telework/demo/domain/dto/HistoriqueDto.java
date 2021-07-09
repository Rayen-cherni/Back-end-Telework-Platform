package com.telework.demo.domain.dto;

import com.telework.demo.domain.entity.enumeration.Decision;
import com.telework.demo.domain.entity.Historique;
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

    private DeveloperDto developer;

    //README
    //We replace the implementation of toEntity() && fromEntity() functions by ModelMapper
    /*
    public static HistoriqueDto fromEntity(Historique historique) {
        if (historique == null) {
            return null;
        }
        return HistoriqueDto.builder()
                .id(historique.getId())
                .startingDate(historique.getStartingDate())
                .deadline(historique.getDeadline())
                .projectManagerDecision(historique.getProjectManagerDecision())
                .poleManagerDecision(historique.getPoleManagerDecision())
                .developer(DeveloperDto.fromEntity(historique.getDeveloper()))
                .build();
    }

    public static Historique toEntity(HistoriqueDto dto) {
        if (dto == null) {
            return null;
        }
        Historique historique = new Historique();

        historique.setId(dto.getId());
        historique.setStartingDate(dto.getStartingDate());
        historique.setDeadline(dto.getDeadline());
        historique.setProjectManagerDecision(dto.getProjectManagerDecision());
        historique.setPoleManagerDecision(dto.getPoleManagerDecision());
        historique.setDeveloper(DeveloperDto.toEntity(dto.getDeveloper()));
        return historique;
    }

     */
}

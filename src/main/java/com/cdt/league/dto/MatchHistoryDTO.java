package com.cdt.league.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Data
public class MatchHistoryDTO {
    private Long id;
    @NotNull
    private Long player1Id;
    @NotNull
    private Long player2Id;
    @NotNull
    private Integer playerOneWins;
    @NotNull
    private Integer playerTwoWins;
    private Integer draws;
    private Integer totalMatches;
    private LocalDate updatedAt;
    private LocalDate createdAt;
}

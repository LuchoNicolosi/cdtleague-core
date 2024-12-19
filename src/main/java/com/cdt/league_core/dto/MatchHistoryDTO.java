package com.cdt.league_core.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Data
public class MatchHistoryDTO {
    private Long id;
    private Long player1Id;
    private Long player2Id;
    private Integer playerOneWins;
    private Integer playerTwoWins;
    private Integer draws;
    private Integer totalMatches;
    private LocalDate updatedAt;
    private LocalDate createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPlayer1Id() {
        return player1Id;
    }

    public void setPlayer1Id(Long player1Id) {
        this.player1Id = player1Id;
    }

    public Long getPlayer2Id() {
        return player2Id;
    }

    public void setPlayer2Id(Long player2Id) {
        this.player2Id = player2Id;
    }

    public Integer getPlayerOneWins() {
        return playerOneWins;
    }

    public void setPlayerOneWins(Integer playerOneWins) {
        this.playerOneWins = playerOneWins;
    }

    public Integer getPlayerTwoWins() {
        return playerTwoWins;
    }

    public void setPlayerTwoWins(Integer playerTwoWins) {
        this.playerTwoWins = playerTwoWins;
    }

    public Integer getDraws() {
        return draws;
    }

    public void setDraws(Integer draws) {
        this.draws = draws;
    }

    public Integer getTotalMatches() {
        return totalMatches;
    }

    public void setTotalMatches(Integer totalMatches) {
        this.totalMatches = totalMatches;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
}

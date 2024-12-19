package com.cdt.league_core.dto;

import com.cdt.league_core.entity.enums.MatchType;
import jakarta.validation.constraints.NotNull;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
public class MatchDTO {
    private Long id;
    @NotNull
    private Long player1Id;
    @NotNull
    private Long player2Id;
    private String playerOneName;
    private String playerTwoName;
    @NotNull
    private Integer playerOneScore;
    @NotNull
    private Integer playerTwoScore;
    @NotNull
    private MatchType type;
    private LocalDate createdAt;

    public Long getId() {
        return id;
    }

    public MatchType getType() {
        return type;
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

    public Integer getPlayerOneScore() {
        return playerOneScore;
    }

    public void setPlayerOneScore(Integer playerOneScore) {
        this.playerOneScore = playerOneScore;
    }

    public Integer getPlayerTwoScore() {
        return playerTwoScore;
    }

    public String getPlayerOneName() {
        return playerOneName;
    }

    public void setPlayerOneName(String playerOneName) {
        this.playerOneName = playerOneName;
    }

    public String getPlayerTwoName() {
        return playerTwoName;
    }

    public void setPlayerTwoName(String playerTwoName) {
        this.playerTwoName = playerTwoName;
    }

    public void setType(MatchType type) {
        this.type = type;
    }

    public void setPlayerTwoScore(Integer playerTwoScore) {
        this.playerTwoScore = playerTwoScore;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
}

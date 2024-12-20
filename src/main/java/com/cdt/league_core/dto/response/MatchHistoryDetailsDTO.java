package com.cdt.league_core.dto.response;

import java.time.LocalDate;
import java.util.List;

public class MatchHistoryDetailsDTO {
    private Long id;
    private Long playerId;
    private Long rivalId;
    private Integer playerWins;
    private Integer rivalWins;
    private String playerName;
    private String rivalName;
    private Integer draws;
    private Integer totalMatches;
    private LocalDate updatedAt;
    private LocalDate createdAt;
    private List<MatchDetailsDTO> matches;

    public MatchHistoryDetailsDTO() {
    }

    public MatchHistoryDetailsDTO(Long id, Long playerId, Long rivalId, Integer playerWins, Integer rivalWins, String playerName, String rivalName, Integer draws, Integer totalMatches, LocalDate updatedAt, LocalDate createdAt, List<MatchDetailsDTO> matches) {
        this.id = id;
        this.playerId = playerId;
        this.rivalId = rivalId;
        this.playerWins = playerWins;
        this.rivalWins = rivalWins;
        this.playerName = playerName;
        this.rivalName = rivalName;
        this.draws = draws;
        this.totalMatches = totalMatches;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.matches = matches;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<MatchDetailsDTO> getMatches() {
        return matches;
    }

    public void setMatches(List<MatchDetailsDTO> matches) {
        this.matches = matches;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public Long getRivalId() {
        return rivalId;
    }

    public void setRivalId(Long rivalId) {
        this.rivalId = rivalId;
    }

    public Integer getPlayerWins() {
        return playerWins;
    }

    public void setPlayerWins(Integer playerWins) {
        this.playerWins = playerWins;
    }

    public Integer getRivalWins() {
        return rivalWins;
    }

    public void setRivalWins(Integer rivalWins) {
        this.rivalWins = rivalWins;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getRivalName() {
        return rivalName;
    }

    public void setRivalName(String rivalName) {
        this.rivalName = rivalName;
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



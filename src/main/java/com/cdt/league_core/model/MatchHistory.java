package com.cdt.league_core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "match_history")
public class MatchHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "player_one_id", nullable = false)
    private Long player1Id;
    @Column(name = "player_two_id", nullable = false)
    private Long player2Id;
    @Column(name = "player_one_wins", nullable = false)
    private Integer playerOneWins;
    @Column(name = "player_two_wins", nullable = false)
    private Integer playerTwoWins;
    @Column(nullable = false)
    private Integer draws;
    @Column(name = "total_matches", nullable = false)
    private Integer totalMatches;
    @Column(name = "updated_at")
    private LocalDate updatedAt;
    @Column(name = "created_at")
    private LocalDate createdAt;
    @OneToMany(mappedBy = "matchHistory")
    @JsonIgnore
    private List<Match> matches;

    public MatchHistory() {
    }

    public MatchHistory(Long player1Id, Long player2Id, Integer playerOneWins, Integer playerTwoWins, Integer draws, LocalDate updatedAt) {
        this.player1Id = player1Id;
        this.player2Id = player2Id;
        this.playerOneWins = playerOneWins;
        this.playerTwoWins = playerTwoWins;
        this.draws = draws;
        this.updatedAt = updatedAt;
        this.createdAt = LocalDate.now();
        this.matches = new ArrayList<>();
    }

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

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
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

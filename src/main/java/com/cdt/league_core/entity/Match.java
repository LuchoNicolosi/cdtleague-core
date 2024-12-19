package com.cdt.league_core.entity;

import com.cdt.league_core.entity.enums.MatchType;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "matches")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "match_type", nullable = false)
    private MatchType type;
    @Column(name = "player_one_id", nullable = false)
    private Long player1Id;
    @Column(name = "player_two_id", nullable = false)
    private Long player2Id;
    @Column(name = "player_one_score", nullable = false)
    private Integer playerOneScore;

    public MatchHistory getMatchHistory() {
        return matchHistory;
    }

    @Column(name = "player_two_score", nullable = false)
    private Integer playerTwoScore;
    @Column(name = "created_at")
    private LocalDate createdAt;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "match_history_id")
    private MatchHistory matchHistory;

    public Match() {
    }

    public Match(Long id, MatchType type, Long player1Id, Long player2Id, Integer playerOneScore, Integer playerTwoScore, LocalDate createdAt, MatchHistory matchHistory) {
        this.id = id;
        this.type = type;
        this.player1Id = player1Id;
        this.player2Id = player2Id;
        this.playerOneScore = playerOneScore;
        this.playerTwoScore = playerTwoScore;
        this.createdAt = createdAt;
        this.matchHistory = matchHistory;
    }

    public Long getId() {
        return id;
    }

    public void setType(MatchType type) {
        this.type = type;
    }

    public MatchType getType() {
        return type;
    }

    public void setMatchHistory(MatchHistory matchHistory) {
        this.matchHistory = matchHistory;
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

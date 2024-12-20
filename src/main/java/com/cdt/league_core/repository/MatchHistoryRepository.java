package com.cdt.league_core.repository;

import com.cdt.league_core.model.MatchHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchHistoryRepository extends JpaRepository<MatchHistory, Long> {
    @Query("SELECT m FROM MatchHistory m WHERE m.player1Id = :playerId OR m.player2Id = :playerId")
    List<MatchHistory> findMatchesHistoryByPlayerId(Long playerId);

    @Query("SELECT m FROM MatchHistory m WHERE m.player1Id = :player1 OR m.player2Id = :player2 AND m.player1Id = :player2 OR m.player2Id = :player1")
    MatchHistory findByPlayer1IdAndPlayer2Id(Long player1, Long player2);
}

package com.cdt.league_core.repository;


import com.cdt.league_core.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    @Query("SELECT m FROM Match m WHERE m.player1Id = :playerId OR m.player2Id = :playerId")
    List<Match> findMatchesByPlayerId(@Param("playerId") Long playerId);
}

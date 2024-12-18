package com.cdt.league_core.repository;

import com.cdt.league_core.entity.MatchHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchHistoryRepository extends JpaRepository<MatchHistory, Long> {
    MatchHistory findByPlayer1IdAndPlayer2Id(Long player1, Long player2);
}

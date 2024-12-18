package com.cdt.league.service;

import com.cdt.league.entity.Match;
import com.cdt.league.entity.MatchHistory;
import com.cdt.league.repository.MatchHistoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class MatchHistoryService {
    private final MatchHistoryRepository mhRepository;

    public MatchHistoryService(MatchHistoryRepository mhRepository) {
        this.mhRepository = mhRepository;
    }

    public void createMatchHistory(Match match) {
        MatchHistory newMhHistory = new MatchHistory();
        newMhHistory.setPlayer1Id(match.getPlayer1Id());
        newMhHistory.setPlayer2Id(match.getPlayer2Id());
        newMhHistory.setTotalMatches(1);
        boolean isDraw = match.getPlayerOneScore().equals(match.getPlayerTwoScore());

        if (isDraw) {
            newMhHistory.setDraws(1);
        } else {
            newMhHistory.setDraws(0);
        }
        ;

        if (match.getPlayerOneScore() > match.getPlayerTwoScore() && !isDraw) {
            newMhHistory.setPlayerOneWins(1);
            newMhHistory.setPlayerTwoWins(0);
        } else {
            newMhHistory.setPlayerOneWins(0);
            newMhHistory.setPlayerTwoWins(1);
        }
        newMhHistory.setCreatedAt(LocalDate.now());

        mhRepository.save(newMhHistory);
    }

    public MatchHistory saveMatchHistory(Match match) {
        validateMatch(match);

        MatchHistory history = mhRepository.findByPlayer1IdAndPlayer2Id(match.getPlayer1Id(), match.getPlayer2Id());
        if (history == null) {
            history = new MatchHistory();
            history.setPlayer1Id(match.getPlayer1Id());
            history.setPlayer2Id(match.getPlayer2Id());
            history.setTotalMatches(0);
            history.setDraws(0);
            history.setPlayerOneWins(0);
            history.setPlayerTwoWins(0);
            history.setCreatedAt(LocalDate.now());
        }else{
        history.setUpdatedAt(LocalDate.now());
        }

        history.setTotalMatches(history.getTotalMatches() + 1);
        updateWins(match, history);

       return mhRepository.save(history);
    }

    private boolean isDraw(Match match) {
        return match.getPlayerOneScore().equals(match.getPlayerTwoScore());
    }

    private void updateWins(Match match, MatchHistory history) {
        if (isDraw(match)) {
            history.setDraws(history.getDraws() + 1);
        } else if (match.getPlayerOneScore() > match.getPlayerTwoScore()) {
            history.setPlayerOneWins(history.getPlayerOneWins() + 1);
        } else {
            history.setPlayerTwoWins(history.getPlayerTwoWins() + 1);
        }
    }

    private void validateMatch(Match match) {
        if (match.getPlayer1Id() == null || match.getPlayer2Id() == null) {
            throw new IllegalArgumentException("Player IDs cannot be null");
        }
        if (match.getPlayerOneScore() < 0 || match.getPlayerTwoScore() < 0) {
            throw new IllegalArgumentException("Scores cannot be negative");
        }
    }
}

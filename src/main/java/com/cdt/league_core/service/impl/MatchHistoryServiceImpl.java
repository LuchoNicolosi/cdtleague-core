package com.cdt.league_core.service.impl;

import com.cdt.league_core.dto.MatchDTO;
import com.cdt.league_core.dto.MatchHistoryDTO;
import com.cdt.league_core.exception.BadRequestException;
import com.cdt.league_core.exception.ConflictException;
import com.cdt.league_core.model.Match;
import com.cdt.league_core.model.MatchHistory;
import com.cdt.league_core.repository.MatchHistoryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class MatchHistoryServiceImpl {
    private final MatchHistoryRepository mhRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public MatchHistoryServiceImpl(MatchHistoryRepository mhRepository, ObjectMapper objectMapper) {
        this.mhRepository = mhRepository;
        this.objectMapper = objectMapper;
    }

    public MatchHistory saveMatchHistory(Match match) {
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
        } else {
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
}

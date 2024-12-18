package com.cdt.league_core.service;

import com.cdt.league_core.dto.MatchDTO;
import com.cdt.league_core.entity.Match;
import com.cdt.league_core.entity.MatchHistory;
import com.cdt.league_core.repository.MatchRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class MatchService {
    private MatchRepository mRepository;
    private final MatchHistoryService matchHistoryService;

    public MatchService(MatchRepository mRepository, MatchHistoryService matchHistoryService) {
        this.mRepository = mRepository;
        this.matchHistoryService = matchHistoryService;
    }

    public Match createMatch(MatchDTO data) {

        Match match = new Match();
        match.setPlayer1Id(data.getPlayer1Id());
        match.setPlayer2Id(data.getPlayer2Id());
        match.setPlayerOneScore(data.getPlayerOneScore());
        match.setPlayerTwoScore(data.getPlayerTwoScore());
        match.setType(data.getType());
        MatchHistory mHistory = matchHistoryService.saveMatchHistory(match);

        match.setMatchHistory(mHistory);
        match.setCreatedAt(LocalDate.now());

        return mRepository.save(match);
    }
}
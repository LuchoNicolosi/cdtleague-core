package com.cdt.league_core.service;

import com.cdt.league_core.dto.MatchDTO;
import com.cdt.league_core.dto.PlayerDTO;
import com.cdt.league_core.entity.Match;
import com.cdt.league_core.entity.MatchHistory;
import com.cdt.league_core.entity.Player;
import com.cdt.league_core.repository.MatchRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class MatchService {
    private MatchRepository mRepository;
    private final ModelMapper modelMapper;
    private final MatchHistoryService matchHistoryService;
    private final PlayerService playerService;

    public MatchService(MatchRepository mRepository, ModelMapper modelMapper, MatchHistoryService matchHistoryService, PlayerService playerService) {
        this.mRepository = mRepository;
        this.modelMapper = modelMapper;
        this.matchHistoryService = matchHistoryService;
        this.playerService = playerService;
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

    public List<MatchDTO> getAllMatches() {
        List<MatchDTO> matches = new ArrayList<>(mRepository.findAll().stream().map(this::toDTO).toList());
        for (MatchDTO match : matches) {
            match.setPlayerOneName(playerService.getPLayer(match.getPlayer1Id()).getName());
            match.setPlayerTwoName(playerService.getPLayer(match.getPlayer2Id()).getName());
        }
        return matches;
    }

    public MatchDTO toDTO(Match data) {
        return modelMapper.map(data, MatchDTO.class);
    }
}

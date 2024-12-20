package com.cdt.league_core.service.impl;

import com.cdt.league_core.dto.MatchDTO;
import com.cdt.league_core.dto.MatchHistoryDTO;
import com.cdt.league_core.dto.response.MatchDetailsDTO;
import com.cdt.league_core.exception.BadRequestException;
import com.cdt.league_core.exception.ConflictException;
import com.cdt.league_core.exception.NotFoundException;
import com.cdt.league_core.model.Match;
import com.cdt.league_core.model.MatchHistory;
import com.cdt.league_core.model.Player;
import com.cdt.league_core.repository.MatchRepository;
import com.cdt.league_core.repository.PlayerRepository;
import com.cdt.league_core.service.IMatchService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MatchServiceImpl implements IMatchService {
    private MatchRepository mRepository;
    private final PlayerRepository playerRepository;
    private final ObjectMapper objectMapper;
    private final MatchHistoryServiceImpl matchHistoryServiceImpl;

    @Autowired
    public MatchServiceImpl(MatchRepository mRepository, PlayerRepository playerRepository, MatchHistoryServiceImpl matchHistoryServiceImpl, ObjectMapper objectMapper) {
        this.mRepository = mRepository;
        this.playerRepository = playerRepository;
        this.matchHistoryServiceImpl = matchHistoryServiceImpl;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<MatchDetailsDTO> findAll() {
        List<MatchDetailsDTO> matches = mRepository.findAll().stream().map(match -> objectMapper.convertValue(match, MatchDetailsDTO.class)).toList();
        for (MatchDetailsDTO match : matches) {
            match.setPlayerOneName(playerRepository.findById(match.getPlayer1Id()).orElseThrow().getName());
            match.setPlayerTwoName(playerRepository.findById(match.getPlayer2Id()).orElseThrow().getName());
        }
        return matches;
    }

    @Override
    public List<MatchDetailsDTO> getByPlayerId(Long playerId) {
        List<MatchDetailsDTO> matches = mRepository.findMatchesByPlayerId(playerId).stream().map(match -> objectMapper.convertValue(match, MatchDetailsDTO.class)).toList();
        for (MatchDetailsDTO match : matches) {
            Player player1 = playerRepository.findById(match.getPlayer1Id())
                    .orElseThrow(() -> new NotFoundException("User not found"));
            Player player2 = playerRepository.findById(match.getPlayer2Id())
                    .orElseThrow(() -> new NotFoundException("User not found"));
            match.setPlayerOneName(player1.getName());
            match.setPlayerTwoName(player2.getName());
        }
        return matches;
    }

    @Override
    public MatchDTO createMatch(MatchDTO matchDto) {
        validateMatch(matchDto);
        Match match = objectMapper.convertValue(matchDto, Match.class);
        match.setMatchHistory(matchHistoryServiceImpl.saveMatchHistory(match));
        match.setCreatedAt(LocalDate.now());
        return objectMapper.convertValue(mRepository.save(match), MatchDTO.class);
    }

    private void validateMatch(MatchDTO data) {
        if (data.getPlayer1Id() == null || data.getPlayer2Id() == null) {
            throw new ConflictException("Player IDs cannot be null");
        }
        playerRepository.findById(data.getPlayer1Id())
                .orElseThrow(() -> new NotFoundException("Player with id [" + data.getPlayer1Id() + "] not found"));

        playerRepository.findById(data.getPlayer2Id())
                .orElseThrow(() -> new NotFoundException("Player with id [" + data.getPlayer2Id() + "] not found"));
        if (data.getPlayerOneScore() < 0 || data.getPlayerTwoScore() < 0 || data.getPlayerOneScore() == null || data.getPlayerTwoScore() == null) {
            throw new BadRequestException("Scores cannot be negative or null");
        }
        if (data.getType().name().isEmpty()) {
            throw new BadRequestException("Match type is required.");
        }
    }
}

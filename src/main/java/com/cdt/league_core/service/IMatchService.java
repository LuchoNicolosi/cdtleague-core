package com.cdt.league_core.service;

import com.cdt.league_core.dto.MatchDTO;
import com.cdt.league_core.dto.response.MatchDetailsDTO;

import java.util.List;

public interface IMatchService {
    List<MatchDetailsDTO> findAll();

    List<MatchDetailsDTO> getByPlayerId(Long playerId);

    MatchDTO createMatch(MatchDTO match);
}

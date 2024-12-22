package com.cdt.league_core.service;

import com.cdt.league_core.dto.MatchHistoryDTO;
import com.cdt.league_core.dto.PlayerDTO;
import com.cdt.league_core.dto.response.MatchHistoryDetailsDTO;
import com.cdt.league_core.model.enums.MatchType;

import java.util.List;

public interface IPlayerService {
    List<PlayerDTO> findAll();

    PlayerDTO findById(Long id);

    MatchHistoryDetailsDTO findMatchHistoryById(Long playerId, Long id, MatchType typeMatch);

    List<MatchHistoryDetailsDTO> findAllMatchHistoryByPlayer(Long playerId);

    PlayerDTO createPlayer(PlayerDTO player);
}

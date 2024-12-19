package com.cdt.league_core.service;

import com.cdt.league_core.dto.PlayerDTO;

import java.util.List;

public interface IPlayerService {
    List<PlayerDTO> findAll();

    PlayerDTO createPlayer(PlayerDTO player);
}

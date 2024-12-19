package com.cdt.league_core.service.impl;

import com.cdt.league_core.dto.PlayerDTO;
import com.cdt.league_core.model.Player;
import com.cdt.league_core.repository.PlayerRepository;
import com.cdt.league_core.service.IPlayerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class PlayerServiceImpl implements IPlayerService {
    private final PlayerRepository pRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public PlayerServiceImpl(PlayerRepository pRepository, ObjectMapper objectMapper) {
        this.pRepository = pRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<PlayerDTO> findAll() {
        return pRepository.findAll().stream().map(player -> objectMapper.convertValue(player, PlayerDTO.class)).toList();
    }

    @Override
    public PlayerDTO createPlayer(PlayerDTO playerDto) {
        playerDto.setCups(Objects.requireNonNullElse(playerDto.getCups(), 0));
        playerDto.setCreatedAt(LocalDate.now());
        Player player = pRepository.save(objectMapper.convertValue(playerDto, Player.class));
        return objectMapper.convertValue(player, PlayerDTO.class);
    }
}

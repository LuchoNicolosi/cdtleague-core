package com.cdt.league_core.service;

import com.cdt.league_core.dto.PlayerDTO;
import com.cdt.league_core.entity.Player;
import com.cdt.league_core.repository.PlayerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class PlayerService {
    private final PlayerRepository pRepository;
    private final ModelMapper modelMapper;

    public PlayerService(PlayerRepository pRepository, ModelMapper modelMapper) {
        this.pRepository = pRepository;
        this.modelMapper = modelMapper;
    }

    public void createPlayer(PlayerDTO data) {
        Player player = new Player();
        player.setName(data.getName());
        player.setAvatar(data.getAvatar());
        player.setDescription(data.getDescription());
        player.setCups(Objects.requireNonNullElse(data.getCups(), 0));
        player.setCreatedAt(LocalDate.now());
        pRepository.save(player);
    }

    public List<PlayerDTO> getAllPlayers() {
        return new ArrayList<>(pRepository.findAll().stream().map(this::toDTO).toList());
    }

    public PlayerDTO getPLayer(Long id) {
        return toDTO(pRepository.findById(id).orElseThrow());
    }

    public PlayerDTO toDTO(Player data) {
        return modelMapper.map(data, PlayerDTO.class);
    }
}

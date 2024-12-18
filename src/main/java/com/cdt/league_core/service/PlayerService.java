package com.cdt.league_core.service;

import com.cdt.league_core.dto.PlayerDTO;
import com.cdt.league_core.entity.Player;
import com.cdt.league_core.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PlayerService {
    private PlayerRepository pRepository;

    public PlayerService(PlayerRepository pRepository) {
        this.pRepository = pRepository;
    }

    public void createPlayer(PlayerDTO data) {
        Player player = new Player();
        player.setName(data.getName());
        player.setAvatar(data.getAvatar());
        player.setDescription(data.getDescription());
        player.setCups(data.getCups());
        player.setCreatedAt(LocalDate.now());

        pRepository.save(player);
    }
}
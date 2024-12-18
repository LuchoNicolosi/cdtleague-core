package com.cdt.league.service;

import com.cdt.league.dto.PlayerDTO;
import com.cdt.league.entity.Player;
import com.cdt.league.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

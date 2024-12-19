package com.cdt.league_core.controller;

import com.cdt.league_core.dto.PlayerDTO;
import com.cdt.league_core.service.PlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/player")
public class PlayerController {
    private final Logger log = LoggerFactory.getLogger(PlayerController.class);
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }


    @GetMapping
    public  ResponseEntity getPlayers(){
        try {
            List<PlayerDTO> players = playerService.getAllPlayers();
            return ResponseEntity.status(HttpStatus.OK).body(players);
        } catch (Exception e) {
            log.error("Error get players: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error get players: " + e.getMessage());
        }
    }
    @PostMapping
    public ResponseEntity<String> createPlayer(@RequestBody PlayerDTO data) {
        try {
            playerService.createPlayer(data);
            return ResponseEntity.status(HttpStatus.CREATED).body("Player created successfully");
        } catch (Exception e) {
            log.error("Error creating player: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating player: " + e.getMessage());
        }
    }
}

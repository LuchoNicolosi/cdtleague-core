package com.cdt.league_core.controller;

import com.cdt.league_core.dto.ApiResponse;
import com.cdt.league_core.dto.MatchHistoryDTO;
import com.cdt.league_core.dto.PlayerDTO;
import com.cdt.league_core.dto.response.MatchHistoryDetailsDTO;
import com.cdt.league_core.service.IPlayerService;
import com.cdt.league_core.service.impl.PlayerServiceImpl;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/player")
public class PlayerController {
    private final Logger log = LoggerFactory.getLogger(PlayerController.class);
    private final IPlayerService playerService;

    @Autowired
    public PlayerController(IPlayerService playerService) {
        this.playerService = playerService;
    }


    @GetMapping
    public ResponseEntity<ApiResponse<List<PlayerDTO>>> getPlayers() {
        try {
            return ResponseEntity.ok(new ApiResponse<>(true, playerService.findAll(), "Players fetched successfully"));
        } catch (Exception e) {
            log.error("Error get players: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, null, "Error fetching players: " + e.getMessage()));
        }
    }

    @GetMapping("/{playerId}")
    public ResponseEntity<ApiResponse<PlayerDTO>> getPlayer(@PathVariable Long playerId) {
        try {
            return ResponseEntity.ok(new ApiResponse<>(true, playerService.findById(playerId), "Players fetched successfully"));
        } catch (Exception e) {
            log.error("Error get players: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, null, "Error fetching players: " + e.getMessage()));
        }
    }

    @GetMapping("/{playerId}/match-history")
    public ResponseEntity<ApiResponse<List<MatchHistoryDetailsDTO>>> getMatchHistoryByPlayer(@PathVariable Long playerId) {
        try {
            return ResponseEntity.ok(new ApiResponse<>(true, playerService.findAllMatchHistoryByPlayer(playerId), "Matches history fetched successfully"));
        } catch (Exception e) {
            log.error("Error get players: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, null, "Error fetching players: " + e.getMessage()));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createPlayer(@Valid @RequestBody PlayerDTO data) {
        try {
            playerService.createPlayer(data);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(true, null, "Successfully created"));
        } catch (Exception e) {
            log.error("Error creating player: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, null, "Error create player: " + e.getMessage()));
        }
    }
}

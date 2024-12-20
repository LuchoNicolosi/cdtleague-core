package com.cdt.league_core.controller;

import com.cdt.league_core.dto.ApiResponse;
import com.cdt.league_core.dto.MatchDTO;
import com.cdt.league_core.dto.response.MatchDetailsDTO;
import com.cdt.league_core.service.IMatchService;
import com.cdt.league_core.service.impl.MatchServiceImpl;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/match")
public class MatchController {
    private final Logger log = LoggerFactory.getLogger(MatchController.class);
    private final IMatchService matchService;

    public MatchController(IMatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<MatchDetailsDTO>>> getMatches() {
        try {
            return ResponseEntity.ok(new ApiResponse<>(true, matchService.findAll(), "Matches fetched successfully"));
        } catch (Exception e) {
            log.error("Error getting matches: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, null, "Error fetching matches: " + e.getMessage()));
        }
    }

    @GetMapping("/player/{playerId}")
    public ResponseEntity<ApiResponse<List<MatchDetailsDTO>>> getMatchesByPlayer(@PathVariable Long playerId) {
        try {
            return ResponseEntity.ok(new ApiResponse<>(true, matchService.getByPlayerId(playerId), "Match fetch successfully"));
        } catch (Exception e) {
            log.error("Error getting matches: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, null, "Error fetching matches: " + e.getMessage()));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createMatch(@Valid @RequestBody MatchDTO data) {
        try {
            matchService.createMatch(data);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(true, null, "Match created successfully"));
        } catch (Exception e) {
            log.error("Error creating match: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, null, "Error creating match: " + e.getMessage()));
        }
    }
}

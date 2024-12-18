package com.cdt.league_core.controller;

import com.cdt.league_core.dto.MatchDTO;
import com.cdt.league_core.service.MatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/match")
public class MatchController {
    private final Logger log = LoggerFactory.getLogger(MatchController.class);
    private final MatchService matchService;
    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }
    @PostMapping
    public ResponseEntity<String> createMatch(@RequestBody MatchDTO data) {
        try {
            matchService.createMatch(data);
            return ResponseEntity.status(HttpStatus.CREATED).body("Match created successfully");
        } catch (Exception e) {
            log.error("Error creating match: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating player: " + e.getMessage());
        }
    }
}

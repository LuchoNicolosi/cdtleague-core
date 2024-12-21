package com.cdt.league_core.service.impl;

import com.cdt.league_core.dto.PlayerDTO;
import com.cdt.league_core.dto.response.MatchDetailsDTO;
import com.cdt.league_core.dto.response.MatchHistoryDetailsDTO;
import com.cdt.league_core.exception.NotFoundException;
import com.cdt.league_core.model.MatchHistory;
import com.cdt.league_core.model.Player;
import com.cdt.league_core.repository.MatchHistoryRepository;
import com.cdt.league_core.repository.PlayerRepository;
import com.cdt.league_core.service.IPlayerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl implements IPlayerService {
    private final PlayerRepository pRepository;
    private final MatchHistoryRepository matchHistoryRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public PlayerServiceImpl(PlayerRepository pRepository, MatchHistoryRepository matchHistoryRepository, ObjectMapper objectMapper) {
        this.pRepository = pRepository;
        this.objectMapper = objectMapper;
        this.matchHistoryRepository = matchHistoryRepository;
    }

    @Override
    public List<PlayerDTO> findAll() {
        return pRepository.findAll().stream().map(player -> objectMapper.convertValue(player, PlayerDTO.class)).toList();
    }

    @Override
    public PlayerDTO findById(Long id) {
        Player player = pRepository.findById(id).orElseThrow(() -> new NotFoundException("Player with id [" + id + "] not found"));
        return objectMapper.convertValue(player, PlayerDTO.class);
    }

    @Override
    public MatchHistoryDetailsDTO findMatchHistoryById(Long playerId, Long id) {
        pRepository.findById(id).orElseThrow(() -> new NotFoundException("Player with id [" + id + "] not found"));
        MatchHistory matchHistory = matchHistoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Match history [" + id + "] not found"));
        // Convertir MatchHistory a DTO
        MatchHistoryDetailsDTO detailsDto = objectMapper.convertValue(matchHistory, MatchHistoryDetailsDTO.class);

        // Obtener jugadores de forma segura
        Player player1 = pRepository.findById(matchHistory.getPlayer1Id())
                .orElseThrow(() -> new NotFoundException("Player1 no encontrado"));
        Player player2 = pRepository.findById(matchHistory.getPlayer2Id())
                .orElseThrow(() -> new NotFoundException("Player2 no encontrado"));

        // Calcular victorias, derrotas y empates
        AtomicInteger playerWins = new AtomicInteger();
        AtomicInteger rivalWins = new AtomicInteger();
        AtomicInteger draws = new AtomicInteger();

        List<MatchDetailsDTO> matchesDetails = matchHistory.getMatches().stream()
                .map(match -> {
                    MatchDetailsDTO matchDto = objectMapper.convertValue(match, MatchDetailsDTO.class);

                    // Asignar nombres de jugadores según los IDs de los partidos
                    if (match.getPlayer1Id().equals(player1.getId())) {
                        matchDto.setPlayerOneName(player1.getName());
                        matchDto.setPlayerOneScore(match.getPlayerOneScore());

                        matchDto.setPlayerTwoName(player2.getName());
                        matchDto.setPlayerTwoScore(match.getPlayerTwoScore());
                    } else if (match.getPlayer1Id().equals(player2.getId())) {
                        matchDto.setPlayerOneName(player2.getName());
                        matchDto.setPlayerOneScore(match.getPlayerOneScore());

                        matchDto.setPlayerTwoName(player1.getName());
                        matchDto.setPlayerTwoScore(match.getPlayerTwoScore());
                    }

                    // Determinar resultados del partido
                    if (match.getPlayer1Id().equals(playerId)) {
                        if (match.getPlayerOneScore() > match.getPlayerTwoScore()) {
                            playerWins.getAndIncrement();
                        } else if (match.getPlayerOneScore() < match.getPlayerTwoScore()) {
                            rivalWins.getAndIncrement();
                        } else {
                            draws.getAndIncrement();
                        }
                    } else if (match.getPlayer2Id().equals(playerId)) {
                        if (match.getPlayerTwoScore() > match.getPlayerOneScore()) {
                            playerWins.getAndIncrement();
                        } else if (match.getPlayerTwoScore() < match.getPlayerOneScore()) {
                            rivalWins.getAndIncrement();
                        } else {
                            draws.getAndIncrement();
                        }
                    }

                    return matchDto;
                })
                .collect(Collectors.toList());

        detailsDto.setMatches(matchesDetails);

        // Establecer resultados calculados dinámicamente
        detailsDto.setPlayerWins(playerWins.get());
        detailsDto.setRivalWins(rivalWins.get());
        detailsDto.setDraws(draws.get());

        // Determinar si el jugador actual es Player1 o Player2 y asignar datos
        if (matchHistory.getPlayer1Id().equals(playerId)) {
            // Datos del jugador actual
            detailsDto.setPlayerId(player1.getId());
            detailsDto.setPlayerName(player1.getName());

            // Datos del rival
            detailsDto.setRivalId(player2.getId());
            detailsDto.setRivalName(player2.getName());
        } else if (matchHistory.getPlayer2Id().equals(playerId)) {
            // Datos del jugador actual
            detailsDto.setPlayerId(player2.getId());
            detailsDto.setPlayerName(player2.getName());

            // Datos del rival
            detailsDto.setRivalId(player1.getId());
            detailsDto.setRivalName(player1.getName());


        }
        return detailsDto;
    }

    @Override
    public List<MatchHistoryDetailsDTO> findAllMatchHistoryByPlayer(Long playerId) {
        List<MatchHistory> matchesHistory = matchHistoryRepository.findMatchesHistoryByPlayerId(playerId);
        List<MatchHistoryDetailsDTO> matchesHistoryDto = new ArrayList<>();

        for (MatchHistory matchHistory : matchesHistory) {
            // Convertir MatchHistory a DTO
            MatchHistoryDetailsDTO detailsDto = objectMapper.convertValue(matchHistory, MatchHistoryDetailsDTO.class);

            // Obtener jugadores de forma segura
            Player player1 = pRepository.findById(matchHistory.getPlayer1Id())
                    .orElseThrow(() -> new NotFoundException("Player1 no encontrado"));
            Player player2 = pRepository.findById(matchHistory.getPlayer2Id())
                    .orElseThrow(() -> new NotFoundException("Player2 no encontrado"));

            // Calcular victorias, derrotas y empates
            AtomicInteger playerWins = new AtomicInteger();
            AtomicInteger rivalWins = new AtomicInteger();
            AtomicInteger draws = new AtomicInteger();

            List<MatchDetailsDTO> matchesDetails = matchHistory.getMatches().stream()
                    .map(match -> {
                        MatchDetailsDTO matchDto = objectMapper.convertValue(match, MatchDetailsDTO.class);

                        // Asignar nombres de jugadores según los IDs de los partidos
                        if (match.getPlayer1Id().equals(player1.getId())) {
                            matchDto.setPlayerOneName(player1.getName());
                            matchDto.setPlayerOneScore(match.getPlayerOneScore());

                            matchDto.setPlayerTwoName(player2.getName());
                            matchDto.setPlayerTwoScore(match.getPlayerTwoScore());
                        } else if (match.getPlayer1Id().equals(player2.getId())) {
                            matchDto.setPlayerOneName(player2.getName());
                            matchDto.setPlayerOneScore(match.getPlayerOneScore());

                            matchDto.setPlayerTwoName(player1.getName());
                            matchDto.setPlayerTwoScore(match.getPlayerTwoScore());
                        }

                        // Determinar resultados del partido
                        if (match.getPlayer1Id().equals(playerId)) {
                            if (match.getPlayerOneScore() > match.getPlayerTwoScore()) {
                                playerWins.getAndIncrement();
                            } else if (match.getPlayerOneScore() < match.getPlayerTwoScore()) {
                                rivalWins.getAndIncrement();
                            } else {
                                draws.getAndIncrement();
                            }
                        } else if (match.getPlayer2Id().equals(playerId)) {
                            if (match.getPlayerTwoScore() > match.getPlayerOneScore()) {
                                playerWins.getAndIncrement();
                            } else if (match.getPlayerTwoScore() < match.getPlayerOneScore()) {
                                rivalWins.getAndIncrement();
                            } else {
                                draws.getAndIncrement();
                            }
                        }

                        return matchDto;
                    })
                    .collect(Collectors.toList());

            detailsDto.setMatches(matchesDetails);

            // Establecer resultados calculados dinámicamente
            detailsDto.setPlayerWins(playerWins.get());
            detailsDto.setRivalWins(rivalWins.get());
            detailsDto.setDraws(draws.get());

            // Determinar si el jugador actual es Player1 o Player2 y asignar datos
            if (matchHistory.getPlayer1Id().equals(playerId)) {
                // Datos del jugador actual
                detailsDto.setPlayerId(player1.getId());
                detailsDto.setPlayerName(player1.getName());

                // Datos del rival
                detailsDto.setRivalId(player2.getId());
                detailsDto.setRivalName(player2.getName());
            } else if (matchHistory.getPlayer2Id().equals(playerId)) {
                // Datos del jugador actual
                detailsDto.setPlayerId(player2.getId());
                detailsDto.setPlayerName(player2.getName());

                // Datos del rival
                detailsDto.setRivalId(player1.getId());
                detailsDto.setRivalName(player1.getName());
            }

            matchesHistoryDto.add(detailsDto);
        }

        return matchesHistoryDto;
    }

    @Override
    public PlayerDTO createPlayer(PlayerDTO playerDto) {
        playerDto.setCups(Objects.requireNonNullElse(playerDto.getCups(), 0));
        playerDto.setCreatedAt(LocalDate.now());
        Player player = pRepository.save(objectMapper.convertValue(playerDto, Player.class));
        return objectMapper.convertValue(player, PlayerDTO.class);
    }
}

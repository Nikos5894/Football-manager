package com.mycompany.footballmanager.Controller;


import com.mycompany.footballmanager.DTO.PlayerDTO;
import com.mycompany.footballmanager.Entity.Player;
import com.mycompany.footballmanager.Entity.PlayerTransferRequest;
import com.mycompany.footballmanager.Service.PlayerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/players")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transferPlayer(@RequestBody @Valid PlayerTransferRequest request) {
        playerService.transferPlayer(request);
        return ResponseEntity.ok("Player transferred successfully");
    }
    @PostMapping
    public ResponseEntity<String> createPlayer(@RequestBody @Valid PlayerDTO playerDTO) {
        try {
            playerService.createPlayer(playerDTO);
            return ResponseEntity.ok("Player created successfully!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public PlayerDTO getPlayerById(@PathVariable Long id) {
        Player player = playerService.getPlayerById(id);
        PlayerDTO dto = new PlayerDTO();
        dto.setName(player.getName());
        dto.setAge(player.getAge());
        dto.setMonthsOfExperience(player.getMonthsOfExperience());
        dto.setTeamId(player.getTeam().getId());
        return dto;
    }
    @GetMapping
    public List<PlayerDTO> getAllPlayers() {
        return playerService.getAllPlayers().stream()
                .map(player -> {
                    PlayerDTO dto = new PlayerDTO();
                    dto.setName(player.getName());
                    dto.setAge(player.getAge());
                    dto.setMonthsOfExperience(player.getMonthsOfExperience());
                    dto.setTeamId(player.getTeam().getId());
                    return dto;
                })
                .toList();
    }

    @GetMapping("/team/{teamName}")
    public List<PlayerDTO> getPlayersByTeam(@PathVariable String teamName) {
        return playerService.getPlayersByTeam(teamName).stream()
                .map(player -> {
                    PlayerDTO dto = new PlayerDTO();
                    dto.setName(player.getName());
                    dto.setAge(player.getAge());
                    dto.setMonthsOfExperience(player.getMonthsOfExperience());
                    dto.setTeamId(player.getTeam().getId());
                    return dto;
                })
                .toList();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePlayer(@PathVariable Long id) {
        boolean isDeleted = playerService.deletePlayer(id);
        if (isDeleted) {
            return ResponseEntity.ok("Player deleted successfully!");
        } else {
            return ResponseEntity.status(404).body("Player not found!");
        }
    }
}
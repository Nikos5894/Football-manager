package com.mycompany.footballmanager.Service;


import com.mycompany.footballmanager.DTO.PlayerDTO;
import com.mycompany.footballmanager.Entity.Player;
import com.mycompany.footballmanager.Entity.PlayerTransferRequest;
import com.mycompany.footballmanager.Entity.Team;
import com.mycompany.footballmanager.Exception.InsufficientBalanceException;
import com.mycompany.footballmanager.Exception.PlayerNotFoundException;
import com.mycompany.footballmanager.Exception.TeamNotFoundException;
import com.mycompany.footballmanager.Repository.PlayerRepository;
import com.mycompany.footballmanager.Repository.TeamRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    public PlayerService(PlayerRepository playerRepository, TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
    }

    @Transactional
    public void transferPlayer(PlayerTransferRequest request) {
        // Пошук гравця або помилка, якщо не знайдено
        Player player = playerRepository.findById(request.getPlayerId())
                .orElseThrow(() -> new PlayerNotFoundException("Player with ID " + request.getPlayerId() + " not found"));

        // Пошук команди, яка купує, або помилка, якщо не знайдено
        Team targetTeam = teamRepository.findById(request.getTargetTeamId())
                .orElseThrow(() -> new TeamNotFoundException("Target team with ID " + request.getTargetTeamId() + " not found"));

        // Пошук команди, яка продає
        Team sourceTeam = player.getTeam();
        if (sourceTeam == null) {
            throw new TeamNotFoundException("Source team not found for player ID: " + request.getPlayerId());
        }

        // Обчислення вартості трансферу та комісії
        double transferValue = (player.getMonthsOfExperience() * 100000.0) / player.getAge();
        double commission = (transferValue * targetTeam.getCommissionPercentage()) / 100;
        double totalCost = transferValue + commission;

        if (targetTeam.getAccountBalance() < totalCost) {
            throw new InsufficientBalanceException("Insufficient balance for transfer. Required: " + totalCost + ", Available: " + targetTeam.getAccountBalance());
        }

        targetTeam.setAccountBalance(targetTeam.getAccountBalance() - totalCost);
        sourceTeam.setAccountBalance(sourceTeam.getAccountBalance() + totalCost);

        player.setTeam(targetTeam);

        teamRepository.save(targetTeam);
        teamRepository.save(sourceTeam);
        playerRepository.save(player);
    }

    public Player createPlayer(@Valid PlayerDTO playerDTO) {
        Team team = teamRepository.findById(playerDTO.getTeamId())
                .orElseThrow(() -> new RuntimeException("Team not found"));

        Player player = new Player();
        player.setName(playerDTO.getName());
        player.setAge(playerDTO.getAge());
        player.setMonthsOfExperience(playerDTO.getMonthsOfExperience());
        player.setTeam(team);

        return playerRepository.save(player);
    }

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    public Player getPlayerById(Long id) {
        return playerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Player not found"));
    }

    public List<Player> getPlayersByTeam(String teamName) {
        return playerRepository.findByTeamName(teamName);
    }

    public boolean deletePlayer(Long id) {
        playerRepository.deleteById(id);
        return false;
    }
}

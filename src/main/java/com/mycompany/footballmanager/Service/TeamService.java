package com.mycompany.footballmanager.Service;


import com.mycompany.footballmanager.DTO.TeamDTO;
import com.mycompany.footballmanager.Entity.Team;
import com.mycompany.footballmanager.Exception.TeamNotFoundException;
import com.mycompany.footballmanager.Repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    // Створення нової команди
    public Team createTeam(TeamDTO teamDTO) {
        Team team = new Team();
        team.setName(teamDTO.getName());
        team.setAccountBalance(teamDTO.getAccountBalance());
        team.setCommissionPercentage(teamDTO.getCommissionPercentage());
        return teamRepository.save(team);
    }

    // Отримання всіх команд
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    // Отримання команди за ID
    public Team getTeamById(Long id) {
        return teamRepository.findById(id)
                .orElseThrow(() -> new TeamNotFoundException("Team not found with id: " + id));
    }

    // Оновлення команди
    public Team updateTeam(Long id, TeamDTO teamDTO) {
        Team existingTeam = getTeamById(id);
        existingTeam.setName(teamDTO.getName());
        existingTeam.setAccountBalance(teamDTO.getAccountBalance());
        existingTeam.setCommissionPercentage(teamDTO.getCommissionPercentage());
        return teamRepository.save(existingTeam);
    }

    // Видалення команди
    public void deleteTeam(Long id) {
        Team team = getTeamById(id);
        teamRepository.delete(team);
    }
}
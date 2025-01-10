package com.mycompany.footballmanager.Controller;




import com.mycompany.footballmanager.DTO.TeamDTO;
import com.mycompany.footballmanager.Entity.Team;
import com.mycompany.footballmanager.Exception.TeamNotFoundException;
import com.mycompany.footballmanager.Repository.TeamRepository;
import com.mycompany.footballmanager.Service.TeamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    // Створення нової команди (POST)
    @PostMapping
    public ResponseEntity<?> createTeam(@RequestBody TeamDTO teamDTO) {
        Team createdTeam = teamService.createTeam(teamDTO);
        return ResponseEntity.ok(" Team created successfully!");
    }

    // Отримання всіх команд (GET)
    @GetMapping
    public ResponseEntity<List<Team>> getAllTeams() {
        return ResponseEntity.ok(teamService.getAllTeams());
    }

    // Отримання команди за ID (GET)
    @GetMapping("/{name}")
    public ResponseEntity<Team> getTeamById(@PathVariable Long id) {
        return ResponseEntity.ok(teamService.getTeamById(id));

    }

    // Оновлення команди (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<Team> updateTeam(@PathVariable Long id, @RequestBody TeamDTO teamDTO) {
        return ResponseEntity.ok(teamService.updateTeam(id, teamDTO));
    }

    // Видалення команди (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTeam(@PathVariable Long id) {
        teamService.deleteTeam(id);
        return ResponseEntity.ok("Team deleted successfully!");
    }
}
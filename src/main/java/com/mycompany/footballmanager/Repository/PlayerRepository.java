package com.mycompany.footballmanager.Repository;



import com.mycompany.footballmanager.Entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {


    List<Player> findByTeamName(String teamName);


    List<Player> findByAgeGreaterThan(int age);
}

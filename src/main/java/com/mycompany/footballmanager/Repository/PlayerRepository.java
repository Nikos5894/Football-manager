package com.mycompany.footballmanager.Repository;



import com.mycompany.footballmanager.Entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    // Пошук гравців за ім'ям команди через пов'язану сутність
    List<Player> findByTeamName(String teamName);

    // Пошук гравця за віком (кастомний метод)
    List<Player> findByAgeGreaterThan(int age);
}

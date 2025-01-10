package com.mycompany.footballmanager.Repository;


import com.mycompany.footballmanager.Entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    // Пошук команди за назвою (кастомний метод)
    Team findByName(String name);

    @Query("SELECT t FROM Team t WHERE t.id = :id")
    Team findByIdExact(@Param("id") Long id);
}
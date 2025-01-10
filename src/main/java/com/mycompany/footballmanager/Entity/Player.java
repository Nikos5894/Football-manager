package com.mycompany.footballmanager.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Автоматична генерація ID
    private Long id;

    @NotBlank(message = "Player name is required")  // Валідація: ім'я не може бути пустим
    private String name;

    @Min(value = 18, message = "Player must be at least 18 years old")  // Валідація: мінімальний вік
    private int age;

    @Min(value = 6, message = "Experience must be at least 6 months")  // Валідація: досвід у місяцях
    private int monthsOfExperience;

    @ManyToOne  // Багато гравців можуть належати одній команді
    @JoinColumn(name = "team_id")  // Вказівка на зовнішній ключ
    @JsonIgnore
    private Team team;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Player name is required") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Player name is required") String name) {
        this.name = name;
    }

    @Min(value = 18, message = "Player must be at least 18 years old")
    public int getAge() {
        return age;
    }

    public void setAge(@Min(value = 18, message = "Player must be at least 18 years old") int age) {
        this.age = age;
    }

    @Min(value = 6, message = "Experience must be greater than zero")
    public int getMonthsOfExperience() {
        return monthsOfExperience;
    }

    public void setMonthsOfExperience(@Min(value = 6, message = "Experience must be greater than zero") int monthsOfExperience) {
        this.monthsOfExperience = monthsOfExperience;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
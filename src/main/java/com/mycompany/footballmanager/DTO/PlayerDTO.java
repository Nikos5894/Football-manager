package com.mycompany.footballmanager.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerDTO {

    @NotBlank(message = "Player name is required")
    private String name;

    @Min(value = 18, message = "Player must be at least 18 years old")
    private int age;

    @Min(value = 6, message = "Experience must be at least 6 months")
    private int monthsOfExperience;

    private Long teamId;  // ID команди, до якої належить гравець

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

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }
}
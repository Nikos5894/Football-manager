package com.mycompany.footballmanager.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Автоматична генерація ID
    private Long id;

    @NotBlank(message = "Team name is required")  // Валідація: назва команди обов'язкова
    private String name;

    @NotNull(message = "Balance must be positive")
    @Min(value = 0, message = "Balance must be positive")  // Валідація: позитивний баланс
    private Double accountBalance;

    @Min(value = 0, message = "Commission percentage must be positive")  // Валідація: комісія
    @Max(value = 10, message = "Commission percentage must be less than 10")  // Валідація: комісія
    private Double commissionPercentage;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)  // Один до багатьох
    private List<Player> players;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Team name is required") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Team name is required") String name) {
        this.name = name;
    }

    public @Min(value = 0, message = "Balance must be positive") Double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(@Min(value = 0, message = "Balance must be positive") Double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public @Min(value = 0, message = "Commission percentage must be positive") @Max(value = 10, message = "Commission percentage must be less than 10") Double getCommissionPercentage() {
        return commissionPercentage;
    }

    public void setCommissionPercentage(@Min(value = 0, message = "Commission percentage must be positive") @Max(value = 10, message = "Commission percentage must be less than 10") Double commissionPercentage) {
        this.commissionPercentage = commissionPercentage;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }


    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", accountBalance=" + accountBalance +
                ", commissionPercentage=" + commissionPercentage +
                ", players=" + players +
                '}';
    }
}
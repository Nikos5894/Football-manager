package com.mycompany.footballmanager.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamDTO {

    @NotBlank(message = "Team name is required")
    private String name;

    @Min(value = 0, message = "Account balance must be positive")
    private Double accountBalance;

    @Min(value = 0, message = "Commission percentage must be between 0 and 10")
    private Double commissionPercentage;

    public @NotBlank(message = "Team name is required") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Team name is required") String name) {
        this.name = name;
    }

    public @Min(value = 0, message = "Account balance must be positive") Double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(@Min(value = 0, message = "Account balance must be positive") Double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public @Min(value = 0, message = "Commission percentage must be between 0 and 10") Double getCommissionPercentage() {
        return commissionPercentage;
    }

    public void setCommissionPercentage(@Min(value = 0, message = "Commission percentage must be between 0 and 10") Double commissionPercentage) {
        this.commissionPercentage = commissionPercentage;
    }
}
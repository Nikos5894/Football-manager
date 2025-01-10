package com.mycompany.footballmanager.Entity;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerTransferRequest {
    @NotNull(message = "Player ID is required")
    private Long playerId;

    @NotNull(message = "Target team ID is required")
    private Long targetTeamId;

    public @NotNull(message = "Player ID is required") Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(@NotNull(message = "Player ID is required") Long playerId) {
        this.playerId = playerId;
    }

    public @NotNull(message = "Target team ID is required") Long getTargetTeamId() {
        return targetTeamId;
    }

    public void setTargetTeamId(@NotNull(message = "Target team ID is required") Long targetTeamId) {
        this.targetTeamId = targetTeamId;
    }
}
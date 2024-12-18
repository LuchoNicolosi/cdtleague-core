package com.cdt.league_core.dto;


import jakarta.validation.constraints.NotNull;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor

public class PlayerDTO {
    private Long id;
    @NotNull
    private String name;
    private String avatar;
    @NotNull
    private String description;
    private Integer cups;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    public void setId(Long id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getCups() {
        return cups;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }
}

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

    public void setName(String name) {
        this.name = name;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCups(Integer cups) {
        this.cups = cups;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
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

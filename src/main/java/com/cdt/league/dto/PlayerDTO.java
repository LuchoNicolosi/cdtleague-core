package com.cdt.league.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Data;
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
    private String clubId;
    private Integer cups = 0;
    private LocalDate createdAt;

    public void setId(Long id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getClubId() {
        return clubId;
    }

    public void setClubId(String clubId) {
        this.clubId = clubId;
    }

    public void setCups(Integer cups) {
        this.cups = cups;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
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
}

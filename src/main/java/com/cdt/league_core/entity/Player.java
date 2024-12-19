package com.cdt.league_core.entity;

import com.cdt.league_core.dto.PlayerDTO;
import jakarta.persistence.*;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;

@Entity
@Table(name = "players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column
    private String avatar;
    @Column
    private String description;
    @Column
    private Integer cups;
    @Column(name = "created_at")
    private LocalDate createdAt;
    @Column(name = "updated_at")
    private LocalDate updatedAt;

    public Player() {
    }

    public Player(Long id, String name, String avatar, String description, Integer cups, LocalDate createdAt, LocalDate updatedAt) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.description = description;
        this.cups = cups;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCups(Integer cups) {
        this.cups = cups;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAvatar() {
        return avatar;
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

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }
}

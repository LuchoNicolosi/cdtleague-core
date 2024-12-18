package com.cdt.league_core.entity;

import jakarta.persistence.*;

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
    @Column(columnDefinition = "INTEGER DEFAULT 0")
    private Integer cups = 0;
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

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }
}

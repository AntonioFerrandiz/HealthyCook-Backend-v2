package com.afb.HealthyCook.domain.model;

import javax.persistence.*;

@Entity
@Table(name = "difficulties")
public class RecipeDifficulty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "difficulty_id")
    public Integer id;

    @Column(name = "difficulty")
    private String difficulty;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public RecipeDifficulty() {
    }

    public RecipeDifficulty(Integer id, String difficulty) {
        this.id = id;
        this.difficulty = difficulty;
    }
}

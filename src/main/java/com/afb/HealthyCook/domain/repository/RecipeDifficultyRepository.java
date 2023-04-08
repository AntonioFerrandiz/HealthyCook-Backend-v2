package com.afb.HealthyCook.domain.repository;

import com.afb.HealthyCook.domain.model.RecipeDifficulty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeDifficultyRepository extends JpaRepository<RecipeDifficulty, Integer> {
}

package com.afb.HealthyCook.service;

import com.afb.HealthyCook.domain.model.RecipeDifficulty;

import java.util.List;

public interface RecipeDifficultyService {
    RecipeDifficulty save(RecipeDifficulty recipeDifficulty) throws Exception;
    List<RecipeDifficulty> findAll() throws Exception;
    RecipeDifficulty findById(Integer id) throws Exception;
}

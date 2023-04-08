package com.afb.HealthyCook.service.impl;

import com.afb.HealthyCook.domain.model.RecipeDifficulty;
import com.afb.HealthyCook.domain.repository.RecipeDifficultyRepository;
import com.afb.HealthyCook.service.RecipeDifficultyService;
import com.afb.HealthyCook.shared.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeDifficultyServiceImpl implements RecipeDifficultyService {
    @Autowired
    private RecipeDifficultyRepository recipeDifficultyRepository;


    @Override
    public RecipeDifficulty save(RecipeDifficulty recipeDifficulty) throws Exception {
        return this.recipeDifficultyRepository.save(recipeDifficulty);
    }

    @Override
    public List<RecipeDifficulty> findAll() throws Exception {
        return this.recipeDifficultyRepository.findAll();
    }

    @Override
    public RecipeDifficulty findById(Integer id) throws Exception {
        Optional<RecipeDifficulty> optionalRecipeDifficulty = this.recipeDifficultyRepository.findById(id);
        if(optionalRecipeDifficulty.isEmpty()){
            throw new ResourceNotFoundException("RecipeDifficulty", id);
        }
        RecipeDifficulty recipeDifficulty = optionalRecipeDifficulty.get();
        return recipeDifficulty;
    }
}

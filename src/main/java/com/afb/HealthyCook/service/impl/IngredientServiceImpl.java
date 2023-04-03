package com.afb.HealthyCook.service.impl;

import com.afb.HealthyCook.domain.repository.IngredientsRepository;
import com.afb.HealthyCook.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService {
    @Autowired
    private IngredientsRepository ingredientsRepository;


    @Override
    public List<Integer> getRecipesIdByIngredient(String ingredient) throws Exception {
        return this.ingredientsRepository.getRecipesIdByIngredient(ingredient) ;
    }
}

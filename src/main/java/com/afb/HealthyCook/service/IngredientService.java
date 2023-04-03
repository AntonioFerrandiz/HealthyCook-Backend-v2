package com.afb.HealthyCook.service;

import java.util.List;

public interface IngredientService {
    List<Integer> getRecipesIdByIngredient(String ingredient) throws Exception;
}

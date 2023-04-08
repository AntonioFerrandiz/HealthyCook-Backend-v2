package com.afb.HealthyCook.service;

import com.afb.HealthyCook.domain.dto.Recipe.CreateRecipeResource;
import com.afb.HealthyCook.domain.dto.Recipe.GetRecipeResource;
import com.afb.HealthyCook.domain.model.Recipe;

import java.util.List;
import java.util.Optional;

public interface RecipeService {
    CreateRecipeResource saveRecipe(CreateRecipeResource resource) throws Exception;

    GetRecipeResource findById(Integer id) throws Exception;

    List<GetRecipeResource> findRecipesByIngredients(List<String> ingredient) throws Exception;

    List<GetRecipeResource> findRecipesByDifficulty(String difficulty) throws Exception;
}

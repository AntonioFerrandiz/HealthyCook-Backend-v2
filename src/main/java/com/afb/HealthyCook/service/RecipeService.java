package com.afb.HealthyCook.service;

import com.afb.HealthyCook.domain.dto.Recipe.CreateRecipeResource;
import com.afb.HealthyCook.domain.dto.Recipe.GetRecipeResource;
import org.json.JSONObject;

import java.util.List;

public interface RecipeService {
    CreateRecipeResource saveRecipe(CreateRecipeResource resource) throws Exception;

    GetRecipeResource findById(Integer id) throws Exception;

    List<GetRecipeResource> findRecipesByIngredients(List<String> ingredient) throws Exception;

    List<GetRecipeResource> findRecipesByDifficulty(String difficulty) throws Exception;
}

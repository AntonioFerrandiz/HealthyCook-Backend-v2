package com.afb.HealthyCook.service;

import com.afb.HealthyCook.domain.dto.RecipeSteps.GetRecipeStepsResource;
import java.util.List;

public interface RecipeStepsService {
    GetRecipeStepsResource findById(Integer id) throws Exception;
    List<GetRecipeStepsResource> findByRecipeId(Integer recipeId) throws Exception;

}

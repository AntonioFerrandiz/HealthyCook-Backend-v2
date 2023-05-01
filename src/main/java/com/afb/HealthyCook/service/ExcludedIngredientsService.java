package com.afb.HealthyCook.service;

import com.afb.HealthyCook.domain.dto.ExcludedIngredients.CreateExcludedIngredientResource;
import com.afb.HealthyCook.domain.dto.ExcludedIngredients.GetExcludedIngredientResource;

import java.util.List;

public interface ExcludedIngredientsService {
    CreateExcludedIngredientResource saveExcludedIngredient(CreateExcludedIngredientResource resource) throws Exception;

    List<GetExcludedIngredientResource> findByUserId(Integer userId) throws Exception;
}

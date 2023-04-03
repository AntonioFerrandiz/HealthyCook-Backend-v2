package com.afb.HealthyCook.domain.repository;

import com.afb.HealthyCook.domain.dto.RecipeSteps.GetRecipeStepsResource;
import com.afb.HealthyCook.domain.model.RecipeSteps;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecipeStepsRepository extends JpaRepository<RecipeSteps, Integer> {

    @Query("SELECT rs FROM RecipeSteps rs WHERE rs.recipe.id = ?1")
    List<RecipeSteps> findByRecipeId(Integer recipeId);
}

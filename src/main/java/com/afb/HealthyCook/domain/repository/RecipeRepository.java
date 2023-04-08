package com.afb.HealthyCook.domain.repository;

import com.afb.HealthyCook.domain.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
public interface RecipeRepository  extends JpaRepository<Recipe, Integer> {


    @Query(value = "SELECT r FROM Recipe r WHERE r.id IN (?1)")
    List<Recipe> findRecipesByIngredients(List<Integer> recipesId) throws Exception;

    @Query(value = "SELECT r FROM Recipe r WHERE r.recipeDifficulty.difficulty = ?1")
    List<Recipe> findRecipesByDifficulty(String difficulty);

}

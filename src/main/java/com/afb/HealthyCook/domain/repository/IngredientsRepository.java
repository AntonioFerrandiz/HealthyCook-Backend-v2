package com.afb.HealthyCook.domain.repository;

import com.afb.HealthyCook.domain.model.Ingredients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IngredientsRepository extends JpaRepository<Ingredients, Integer> {

    @Query(value = "SELECT i.recipe.id FROM Ingredients i WHERE i.ingredient LIKE %?1% GROUP BY i.recipe.id")
    List<Integer> getRecipesIdByIngredient(String ingredient) throws Exception;


    @Query(value = "SELECT i.recipe.id FROM Ingredients i WHERE i.ingredient LIKE (?1) GROUP BY i.recipe.id")
    List<Integer> getRecipesIdByIngredients(List<String> ingredient) throws Exception;

    @Query(value = "SELECT i FROM Ingredients i WHERE i.recipe.id = ?1")
    List<Ingredients> findByRecipeId(Integer recipeId);

}

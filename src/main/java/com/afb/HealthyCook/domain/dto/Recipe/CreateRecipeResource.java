package com.afb.HealthyCook.domain.dto.Recipe;

import com.afb.HealthyCook.domain.dto.Ingredients.CreateIngredientResource;
import com.afb.HealthyCook.domain.dto.RecipeSteps.CreateRecipeStepResource;
import com.afb.HealthyCook.domain.model.Ingredients;
import com.afb.HealthyCook.domain.model.RecipeSteps;

import java.util.List;

public class CreateRecipeResource {

    private String recipeName;
    private String recipeDescription;
    private Integer preparationTime;
    private Integer diners;

    private List<CreateRecipeStepResource> recipeStepsList;
    private List<CreateIngredientResource> ingredientsList;

    private Integer userId;
    private Integer difficultyId;

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getRecipeDescription() {
        return recipeDescription;
    }

    public void setRecipeDescription(String recipeDescription) {
        this.recipeDescription = recipeDescription;
    }

    public Integer getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(Integer preparationTime) {
        this.preparationTime = preparationTime;
    }

    public Integer getDiners() {
        return diners;
    }

    public void setDiners(Integer diners) {
        this.diners = diners;
    }

    public Integer getDifficultyId() {
        return difficultyId;
    }

    public void setDifficultyId(Integer difficultyId) {
        this.difficultyId = difficultyId;
    }

    public List<CreateRecipeStepResource> getRecipeStepsList() {
        return recipeStepsList;
    }

    public void setRecipeStepsList(List<CreateRecipeStepResource> recipeStepsList) {
        this.recipeStepsList = recipeStepsList;
    }

    public List<CreateIngredientResource> getIngredientsList() {
        return ingredientsList;
    }

    public void setIngredientsList(List<CreateIngredientResource> ingredientsList) {
        this.ingredientsList = ingredientsList;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}

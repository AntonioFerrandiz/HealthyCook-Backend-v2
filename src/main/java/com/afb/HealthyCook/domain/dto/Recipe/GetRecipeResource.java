package com.afb.HealthyCook.domain.dto.Recipe;

import com.afb.HealthyCook.domain.dto.Ingredients.GetIngredientsResource;
import com.afb.HealthyCook.domain.dto.RecipeSteps.GetRecipeStepsResource;
import com.afb.HealthyCook.domain.model.Ingredients;
import com.afb.HealthyCook.domain.model.Recipe;
import com.afb.HealthyCook.domain.model.RecipeSteps;

import java.util.List;
import java.util.stream.Collectors;

public class GetRecipeResource {
    private Integer recipeId;
    private String recipeName;
    private String recipeDescription;
    private Integer preparationTime;
    private Integer diners;
    private String difficulty;
    private Integer userId;
    private String username;

    private List<Ingredients> ingredients;
    private List<RecipeSteps> recipeSteps;

    public Integer getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Integer recipeId) {
        this.recipeId = recipeId;
    }

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

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Ingredients> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredients> ingredients) {
        this.ingredients = ingredients;
    }

    public List<RecipeSteps> getRecipeSteps() {
        return recipeSteps;
    }

    public void setRecipeSteps(List<RecipeSteps> recipeSteps) {
        this.recipeSteps = recipeSteps;
    }

    public GetRecipeResource(Recipe recipe) {
        this.recipeId = recipe.getId();
        this.recipeName = recipe.getRecipeName();
        this.recipeDescription = recipe.getRecipeDescription();
        this.preparationTime = recipe.getPreparationTime();
        this.diners = recipe.getDiners();
        this.difficulty = recipe.getRecipeDifficulty().getDifficulty();
        this.userId = recipe.getUser().getId();
        this.ingredients = recipe.getIngredientsList();
        this.recipeSteps = recipe.getRecipeStepsList();
        this.username = recipe.getUser().getUsername();
    }

    public static List<GetRecipeResource> convert(List<Recipe> recipes){
        return recipes.stream().map(GetRecipeResource::new).collect(Collectors.toList());
    }
}

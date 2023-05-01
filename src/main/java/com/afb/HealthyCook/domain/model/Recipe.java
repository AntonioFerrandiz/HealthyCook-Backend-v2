package com.afb.HealthyCook.domain.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "recipes")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_id")
    private Integer id;
    @Column(name = "recipe_name")
    private String recipeName;
    @Column(name = "recipe_description")
    private String recipeDescription;
    @Column(name = "preparation_time")
    private Integer preparationTime;
    @Column(name = "diners")
    private Integer diners;



    // Ingredients
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Ingredients> ingredientsList;
    // Steps
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<RecipeSteps> recipeStepsList;
    // User
    @OneToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;

    // Difficulty
    @OneToOne(targetEntity = RecipeDifficulty.class)
    @JoinColumn(name = "difficulty_id")
    private RecipeDifficulty recipeDifficulty;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public List<Ingredients> getIngredientsList() {
        return ingredientsList;
    }

    public void setIngredientsList(List<Ingredients> ingredientsList) {
        this.ingredientsList = ingredientsList;
    }

    public List<RecipeSteps> getRecipeStepsList() {
        return recipeStepsList;
    }

    public void setRecipeStepsList(List<RecipeSteps> recipeStepsList) {
        this.recipeStepsList = recipeStepsList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public RecipeDifficulty getRecipeDifficulty() {
        return recipeDifficulty;
    }

    public void setRecipeDifficulty(RecipeDifficulty recipeDifficulty) {
        this.recipeDifficulty = recipeDifficulty;
    }

    public Recipe() {
    }

    public Recipe(Integer id, String recipeName, String recipeDescription, Integer preparationTime, Integer diners, List<Ingredients> ingredientsList, List<RecipeSteps> recipeStepsList, User user, RecipeDifficulty recipeDifficulty) {
        this.id = id;
        this.recipeName = recipeName;
        this.recipeDescription = recipeDescription;
        this.preparationTime = preparationTime;
        this.diners = diners;
        this.ingredientsList = ingredientsList;
        this.recipeStepsList = recipeStepsList;
        this.user = user;
        this.recipeDifficulty = recipeDifficulty;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", recipeName='" + recipeName + '\'' +
                ", recipeDescription='" + recipeDescription + '\'' +
                ", preparationTime=" + preparationTime +
                ", diners=" + diners +
                ", ingredientsList=" + ingredientsList +
                ", recipeStepsList=" + recipeStepsList +
                ", user=" + user +
                ", recipeDifficulty=" + recipeDifficulty +
                '}';
    }
}

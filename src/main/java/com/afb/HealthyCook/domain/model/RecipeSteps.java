package com.afb.HealthyCook.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "recipe_steps")
public class RecipeSteps implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_steps_id")
    private Integer id;
    @Column(name = "step_number")
    private Integer stepNumber;
    @Column(name = "step_description")
    private String stepDescription;

    // RECIPE
    @ManyToOne(targetEntity = Recipe.class)
    @JoinColumn(name = "recipe_id")
    @JsonBackReference
    private Recipe recipe;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(Integer stepNumber) {
        this.stepNumber = stepNumber;
    }

    public String getStepDescription() {
        return stepDescription;
    }

    public void setStepDescription(String stepDescription) {
        this.stepDescription = stepDescription;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public RecipeSteps() {
    }

    public RecipeSteps(Integer id, Integer stepNumber, String stepDescription, Recipe recipe) {
        this.id = id;
        this.stepNumber = stepNumber;
        this.stepDescription = stepDescription;
        this.recipe = recipe;
    }
}

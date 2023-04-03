package com.afb.HealthyCook.domain.dto.RecipeSteps;

import com.afb.HealthyCook.domain.model.RecipeSteps;

import java.util.List;
import java.util.stream.Collectors;

public class GetRecipeStepsResource {
    private Integer recipeStepId;
    private Integer stepNumber;
    private String stepDescription;

    public Integer getRecipeStepId() {
        return recipeStepId;
    }

    public void setRecipeStepId(Integer recipeStepId) {
        this.recipeStepId = recipeStepId;
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

    public GetRecipeStepsResource(RecipeSteps recipeSteps) {
        this.recipeStepId = recipeSteps.getId();
        this.stepNumber = recipeSteps.getStepNumber();
        this.stepDescription = recipeSteps.getStepDescription();
    }

    public static List<GetRecipeStepsResource> convert(List<RecipeSteps> recipeStepsList){
        return recipeStepsList.stream().map(GetRecipeStepsResource::new).collect(Collectors.toList());
    }
}

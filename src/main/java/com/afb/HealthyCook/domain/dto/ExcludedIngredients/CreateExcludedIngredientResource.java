package com.afb.HealthyCook.domain.dto.ExcludedIngredients;

public class CreateExcludedIngredientResource {
    private String excludedIngredient;

    private Integer userId;

    public String getExcludedIngredient() {
        return excludedIngredient;
    }

    public void setExcludedIngredient(String excludedIngredient) {
        this.excludedIngredient = excludedIngredient;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}

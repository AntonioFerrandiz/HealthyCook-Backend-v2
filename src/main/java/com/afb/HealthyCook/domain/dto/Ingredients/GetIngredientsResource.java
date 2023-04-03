package com.afb.HealthyCook.domain.dto.Ingredients;

import com.afb.HealthyCook.domain.model.Ingredients;

import java.util.List;
import java.util.stream.Collectors;

public class GetIngredientsResource {
    private Integer ingredientId;
    private String ingredientName;

    public Integer getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Integer ingredientId) {
        this.ingredientId = ingredientId;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public GetIngredientsResource(Ingredients ingredients) {
        this.ingredientId = ingredients.getId();
        this.ingredientName = ingredients.getIngredient();
    }
    public static List<GetIngredientsResource> convert(List<Ingredients> ingredients){
        return ingredients.stream().map(GetIngredientsResource::new).collect(Collectors.toList());
    }
}

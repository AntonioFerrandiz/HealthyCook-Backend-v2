package com.afb.HealthyCook.domain.dto.ExcludedIngredients;

import com.afb.HealthyCook.domain.model.ExcludedIngredients;

import java.util.List;
import java.util.stream.Collectors;

public class GetExcludedIngredientResource {

    private Integer excludedIngredientId;
    private String excludedIngredient;

    private Integer userId;
    private String username;

    public Integer getExcludedIngredientId() {
        return excludedIngredientId;
    }

    public void setExcludedIngredientId(Integer excludedIngredientId) {
        this.excludedIngredientId = excludedIngredientId;
    }

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public GetExcludedIngredientResource(ExcludedIngredients excludedIngredients) {
        this.excludedIngredientId = excludedIngredients.getId();
        this.excludedIngredient = excludedIngredients.getExcludedIngredient();
        this.userId = excludedIngredients.getUser().getId();
        this.username = excludedIngredients.getUser().getUsername();
    }
    public static List<GetExcludedIngredientResource> convert(List<ExcludedIngredients> excludedIngredients){
        return excludedIngredients.stream().map(GetExcludedIngredientResource::new).collect(Collectors.toList());
    }
}

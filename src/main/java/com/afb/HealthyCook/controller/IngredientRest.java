package com.afb.HealthyCook.controller;

import com.afb.HealthyCook.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/ingredient")
public class IngredientRest {
    @Autowired
    private IngredientService ingredientService;

    @RequestMapping(value = "getRecipeIdByIngredient/{ingredient}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getRecipeIdByIngredient(@PathVariable String ingredient) throws Exception{
        return new ResponseEntity<>(this.ingredientService.getRecipesIdByIngredient(ingredient), HttpStatus.OK);
    }
}

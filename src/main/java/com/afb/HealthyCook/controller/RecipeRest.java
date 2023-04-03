package com.afb.HealthyCook.controller;

import com.afb.HealthyCook.domain.dto.Recipe.CreateRecipeResource;
import com.afb.HealthyCook.domain.dto.Recipe.GetRecipeResource;
import com.afb.HealthyCook.domain.model.Recipe;
import com.afb.HealthyCook.service.RecipeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/recipe")
public class RecipeRest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RecipeService recipeService;

    @RequestMapping(value = "createRecipe", method = RequestMethod.POST, produces =
            {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> createRecipe(@RequestBody CreateRecipeResource createRecipeResource) throws Exception {
        Recipe recipe = this.recipeService.saveRecipe(createRecipeResource);
        return new ResponseEntity<>(recipe, HttpStatus.OK);
    }

    @RequestMapping(value = "findRecipeById/{recipeId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetRecipeResource> findRecipeById(@PathVariable Integer recipeId) throws Exception{
        return ResponseEntity.ok(this.recipeService.findById(recipeId));

    }

    @RequestMapping(value = "findRecipesByIngredient/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<GetRecipeResource>> findRecipesByIngredient(@RequestBody List<String> ingredient) throws Exception{
        return ResponseEntity.ok(this.recipeService.findRecipesByIngredients(ingredient));
    }
}

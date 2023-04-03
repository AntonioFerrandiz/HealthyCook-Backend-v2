package com.afb.HealthyCook.controller;

import com.afb.HealthyCook.domain.dto.RecipeSteps.GetRecipeStepsResource;
import com.afb.HealthyCook.service.RecipeStepsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/recipe-steps")
public class RecipeStepsRest {
    @Autowired
    private RecipeStepsService recipeStepsService;

    @RequestMapping(value = "getRecipeStepById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetRecipeStepsResource> getRecipeStepById(@PathVariable Integer id) throws Exception {
        GetRecipeStepsResource recipeStep = this.recipeStepsService.findById(id);
        return new ResponseEntity<>(recipeStep, HttpStatus.OK);
    }
    @RequestMapping(value = "getRecipeStepByRecipeId/{recipeId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<GetRecipeStepsResource>> getRecipeStepByRecipeId(@PathVariable Integer recipeId) throws Exception {
        List<GetRecipeStepsResource> recipeSteps = this.recipeStepsService.findByRecipeId(recipeId);
        return new ResponseEntity<>(recipeSteps, HttpStatus.OK);
    }

}

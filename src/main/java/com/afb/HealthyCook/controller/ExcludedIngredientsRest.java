package com.afb.HealthyCook.controller;

import com.afb.HealthyCook.domain.dto.ExcludedIngredients.CreateExcludedIngredientResource;
import com.afb.HealthyCook.domain.dto.ExcludedIngredients.GetExcludedIngredientResource;
import com.afb.HealthyCook.domain.dto.Recipe.GetRecipeResource;
import com.afb.HealthyCook.service.ExcludedIngredientsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("api/excluded-ingredients")
public class ExcludedIngredientsRest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ExcludedIngredientsService excludedIngredientsService;

    @RequestMapping(value = "createExcludedIngredient" ,method = RequestMethod.POST, produces =
            {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> addExcludedIngredient(@RequestBody CreateExcludedIngredientResource response) throws Exception{
        CreateExcludedIngredientResource resource = this.excludedIngredientsService.saveExcludedIngredient(response);
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @RequestMapping(value = "findExcludedIngredientsByUserId/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<GetExcludedIngredientResource>> findExcludedIngredientsByUserId(@PathVariable Integer userId) throws Exception{
        return ResponseEntity.ok(this.excludedIngredientsService.findByUserId(userId));
    }
}

package com.afb.HealthyCook.controller;

import com.afb.HealthyCook.domain.dto.Recipe.CreateRecipeResource;
import com.afb.HealthyCook.domain.dto.Recipe.GetRecipeResource;
import com.afb.HealthyCook.domain.model.Recipe;
import com.afb.HealthyCook.domain.model.RecipeDifficulty;
import com.afb.HealthyCook.service.RecipeDifficultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/recipe-difficulty")
public class RecipeDifficultyRest {
    @Autowired
    private RecipeDifficultyService recipeDifficultyService;
    @RequestMapping(value = "createRecipeDifficulty", method = RequestMethod.POST, produces =
            {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<RecipeDifficulty> createRecipeDifficulty(@RequestBody RecipeDifficulty recipeDifficulty) throws Exception {
        RecipeDifficulty difficulty = this.recipeDifficultyService.save(recipeDifficulty);
        return new ResponseEntity<>(difficulty, HttpStatus.OK);
    }

    @RequestMapping(value = "findAllRecipeDifficulties", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RecipeDifficulty>> findRecipeDifficulById() throws Exception{
        return ResponseEntity.ok(this.recipeDifficultyService.findAll());
    }

    @RequestMapping(value = "findRecipeDifficultyById/{recipeDifficultyId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RecipeDifficulty> findRecipeDifficultyById(@PathVariable Integer recipeDifficultyId) throws Exception{
        return ResponseEntity.ok(this.recipeDifficultyService.findById(recipeDifficultyId));
    }

}

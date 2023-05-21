package com.afb.HealthyCook.controller;

import com.afb.HealthyCook.domain.dto.Recipe.CreateRecipeResource;
import com.afb.HealthyCook.domain.dto.Recipe.GetRecipeResource;
import com.afb.HealthyCook.service.RecipeService;
import io.swagger.annotations.ApiOperation;
import net.sf.jasperreports.engine.JRException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.Base64;
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
        CreateRecipeResource recipe = this.recipeService.saveRecipe(createRecipeResource);
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

    @RequestMapping(value = "getRecipesByDifficulty/{difficulty}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<GetRecipeResource>> getRecipesByDifficulty(@PathVariable String difficulty) throws Exception{
        return ResponseEntity.ok(this.recipeService.findRecipesByDifficulty(difficulty));
    }

    @ApiOperation(value = "Generate recipe PDF", produces = "application/pdf")
    @RequestMapping(value = "generateRecipePDF/{recipeId}", method = RequestMethod.POST)
    public ResponseEntity<String> generateRecipePDF(@PathVariable("recipeId") Integer id){
        try{
            byte[] bytesReporte = this.recipeService.getRecipePDF(id);
            String report = Base64.getEncoder().encodeToString(bytesReporte);
            return new ResponseEntity<>(report, HttpStatus.OK);
        } catch (FileNotFoundException e){
            throw new RuntimeException(e);
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }

}

package com.afb.HealthyCook.service.impl;

import com.afb.HealthyCook.domain.dto.Ingredients.CreateIngredientResource;
import com.afb.HealthyCook.domain.dto.Recipe.CreateRecipeResource;
import com.afb.HealthyCook.domain.dto.Recipe.GetRecipeResource;
import com.afb.HealthyCook.domain.dto.RecipeSteps.CreateRecipeStepResource;
import com.afb.HealthyCook.domain.model.*;
import com.afb.HealthyCook.domain.repository.*;
import com.afb.HealthyCook.service.RecipeService;
import com.afb.HealthyCook.shared.exception.ResourceNotFoundException;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private RecipeDifficultyRepository recipeDifficultyRepository;

    @Autowired
    private RecipeStepsRepository recipeStepsRepository;

    @Autowired
    private IngredientsRepository ingredientsRepository;

    @Autowired
    private ExcludedIngredientRepository excludedIngredientRepository;


    @Autowired
    private UsersRepository usersRepository;

    @Override
    public CreateRecipeResource saveRecipe(CreateRecipeResource resource) throws Exception {
        Optional<User> optionalUser = this.usersRepository.findById(resource.getUserId());
        if(optionalUser.isEmpty()){
            logger.error("No existe usuario con id: " + resource.getUserId());
        }
        User user = optionalUser.get();
        Optional<RecipeDifficulty> optionalRecipeDifficulty = this.recipeDifficultyRepository.findById(resource.getDifficultyId());
        if(optionalRecipeDifficulty.isEmpty()){
            logger.error("No existe receta con id: " + resource.getUserId());
        }
        RecipeDifficulty recipeDifficulty = optionalRecipeDifficulty.get();
        logger.error("<AFB log='user'/> " + user.toString());
        Recipe recipe = new Recipe();
        recipe.setRecipeName(resource.getRecipeName());
        recipe.setRecipeDescription(resource.getRecipeDescription());
        recipe.setRecipeDifficulty(recipeDifficulty);
        recipe.setDiners(resource.getDiners());
        recipe.setPreparationTime(resource.getPreparationTime());
        recipe.setUser(user);
        recipe = this.recipeRepository.save(recipe);
        logger.error("<AFB log='recipe'/> : " + recipe);

        Integer recipeId = recipe.getId();
        logger.error("<AFB log='recipeId'/> " + recipeId);

        List<Ingredients> ingredientsList = new ArrayList<>();
        List<CreateIngredientResource> createIngredientResources = resource.getIngredientsList();
        logger.error("ingredientsList without id: " + createIngredientResources);
        for (CreateIngredientResource item : createIngredientResources){
            Ingredients ingredient = new Ingredients();
            ingredient.setIngredient(item.getIngredient());
            ingredient.setRecipe(recipe);
            ingredientsList.add(ingredient);
        }
        logger.error("<AFB log='ingredientsList with recipe id'/> " + ingredientsList);
        this.ingredientsRepository.saveAll(ingredientsList);

        List<RecipeSteps> recipeStepsList =  new ArrayList<>();
        List<CreateRecipeStepResource> createRecipeStepResources = resource.getRecipeStepsList();
        logger.error("<AFB log='recipeStepsList without recipe id'/> " + createRecipeStepResources);
        Integer stepNumber = 1;
        for (CreateRecipeStepResource item : createRecipeStepResources){
            RecipeSteps recipeSteps = new RecipeSteps();
            recipeSteps.setStepNumber(stepNumber);
            recipeSteps.setStepDescription(item.getStepDescription());
            recipeSteps.setRecipe(recipe);
            recipeStepsList.add(recipeSteps);
            stepNumber++;
        }
        logger.error("<AFB log='recipeStepsList with recipe id'/> " + recipeStepsList);
        this.recipeStepsRepository.saveAll(recipeStepsList);
        recipe.setIngredientsList(ingredientsList);
        recipe.setRecipeStepsList(recipeStepsList);
        return resource;
    }

    @Override
    public GetRecipeResource findById(Integer id) throws Exception {
        Optional<Recipe> recipeOptional = this.recipeRepository.findById(id);
        if(!recipeOptional.isPresent()){
            throw new ResourceNotFoundException("Receta", id);
        }
        return new GetRecipeResource(recipeOptional.get());
    }

    @Override
    public List<GetRecipeResource> findRecipesByIngredients(List<String> ingredient) throws Exception {

        List<List<Integer>> recipesIdAux = new ArrayList<>();
        for (String i : ingredient){
            List<Integer> ids =  this.ingredientsRepository.getRecipesIdByIngredient(i);
            recipesIdAux.add(ids);
        }

        List<Integer> recipesId = recipesIdAux.stream().flatMap(List::stream).distinct().collect(Collectors.toList());
        if(recipesId.isEmpty()){
            throw new ResourceNotFoundException("No existen recetas con los ingredientes indicados");
        }
        List<Recipe> recipeList = this.recipeRepository.findRecipesByIngredients(recipesId);
        List<ExcludedIngredients> excludedIngredientsList = this.excludedIngredientRepository.findByUserId(1);
        if (!excludedIngredientsList.isEmpty()) {
            List<Recipe> recipesWithoutExcludes = recipeList.stream()
                    .filter(recipe -> excludedIngredientsList.stream()
                            .noneMatch(excludedIngredient -> containExcludedIngredient(excludedIngredient, recipe)))
                    .collect(Collectors.toList());
            return GetRecipeResource.convert(recipesWithoutExcludes);
        }
        return GetRecipeResource.convert(recipeList);
    }
    public boolean containExcludedIngredient(ExcludedIngredients excludedIngredient, Recipe recipe) {
        String excludedIngredientName = excludedIngredient.getExcludedIngredient();
        Pattern pattern = Pattern.compile("\\b" + excludedIngredientName + "\\b", Pattern.CASE_INSENSITIVE);
        return recipe.getIngredientsList().stream()
                .map(Ingredients::getIngredient)
                .anyMatch(ingredientName -> pattern.matcher(ingredientName).find());
    }
    @Override
    public List<GetRecipeResource> findRecipesByDifficulty(String difficulty) throws Exception {
        List<Recipe> recipeList = this.recipeRepository.findRecipesByDifficulty(difficulty);
        return GetRecipeResource.convert(recipeList);
    }

    @Override
    public byte[] getRecipePDF(Integer id) throws FileNotFoundException, JRException {
        try{
            Optional<Recipe> optionalRecipe = this.recipeRepository.findById(id);
            if(!optionalRecipe.isPresent()) {
                throw new Exception("No existe receta con ID: " + id);
            }

            Recipe recipe = optionalRecipe.get();
            List<RecipeSteps> recipeStepsList = this.recipeStepsRepository.findByRecipeId(id);
            List<Ingredients> ingredientsList = this.ingredientsRepository.findByRecipeId(id);

            List<Map<String, Object>> reportBody = new ArrayList<>();
            Map<String, Object> recipeMap = new HashMap<>();

            recipeMap.put("recipe_creator", recipe.getUser().getUsername());
            recipeMap.put("recipe_name", recipe.getRecipeName());
            recipeMap.put("recipe_description", recipe.getRecipeDescription());
            recipeMap.put("recipe_preparation_time", recipe.getPreparationTime().toString());
            recipeMap.put("recipe_dinners", recipe.getDiners().toString());
            recipeMap.put("recipe_difficulty", recipe.getRecipeDifficulty().getDifficulty());

            reportBody.add(recipeMap);

            File file = ResourceUtils.getFile("classpath:reports/jrxml/recipe.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

            JRBeanCollectionDataSource detail = new JRBeanCollectionDataSource(reportBody);

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("CreatedBy", "AntonioFerrandiz");

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters,detail);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

            return outputStream.toByteArray();
        } catch (Exception e){
            logger.error("Error al generar el PDF: " + e.getMessage(), e.getCause());
        }
        return  new byte[0];
    }

}

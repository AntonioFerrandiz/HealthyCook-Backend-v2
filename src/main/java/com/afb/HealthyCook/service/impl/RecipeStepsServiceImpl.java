package com.afb.HealthyCook.service.impl;

import com.afb.HealthyCook.domain.dto.RecipeSteps.GetRecipeStepsResource;
import com.afb.HealthyCook.domain.model.RecipeSteps;
import com.afb.HealthyCook.domain.repository.RecipeStepsRepository;
import com.afb.HealthyCook.service.RecipeStepsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeStepsServiceImpl implements RecipeStepsService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RecipeStepsRepository recipeStepsRepository;

    @Override
    public GetRecipeStepsResource findById(Integer id) throws Exception {
        Optional<RecipeSteps> optionalRecipeSteps = this.recipeStepsRepository.findById(id);
        return new GetRecipeStepsResource(optionalRecipeSteps.get());
    }

    @Override
    public List<GetRecipeStepsResource> findByRecipeId(Integer recipeId) throws Exception {
        List<RecipeSteps> optionals = this.recipeStepsRepository.findByRecipeId(recipeId);
        logger.error("optionals: " + optionals);
        return GetRecipeStepsResource.convert(optionals);
    }
}

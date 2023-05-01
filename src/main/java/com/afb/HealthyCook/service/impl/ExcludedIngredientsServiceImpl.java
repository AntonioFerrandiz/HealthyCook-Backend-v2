package com.afb.HealthyCook.service.impl;

import com.afb.HealthyCook.domain.dto.ExcludedIngredients.CreateExcludedIngredientResource;
import com.afb.HealthyCook.domain.dto.ExcludedIngredients.GetExcludedIngredientResource;
import com.afb.HealthyCook.domain.model.ExcludedIngredients;
import com.afb.HealthyCook.domain.model.User;
import com.afb.HealthyCook.domain.repository.ExcludedIngredientRepository;
import com.afb.HealthyCook.domain.repository.UsersRepository;
import com.afb.HealthyCook.service.ExcludedIngredientsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExcludedIngredientsServiceImpl implements ExcludedIngredientsService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ExcludedIngredientRepository excludedIngredientRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public CreateExcludedIngredientResource saveExcludedIngredient(CreateExcludedIngredientResource resource) throws Exception {
        Optional<User> optionalUser = this.usersRepository.findById(resource.getUserId());
        if(optionalUser.isEmpty()){
            logger.error("No existe usuario con id: " + resource.getUserId());
        }
        User user = optionalUser.get();

        ExcludedIngredients excludedIngredients = new ExcludedIngredients();
        excludedIngredients.setExcludedIngredient(resource.getExcludedIngredient());
        excludedIngredients.setUser(user);

        this.excludedIngredientRepository.save(excludedIngredients);
        return resource;
    }

    @Override
    public List<GetExcludedIngredientResource> findByUserId(Integer userId) throws Exception {
        Optional<User> optionalUser = this.usersRepository.findById(userId);
        if(optionalUser.isEmpty()){
            logger.error("No existe usuario con id: " + userId);
        }
        List<ExcludedIngredients> excludedIngredientsList = this.excludedIngredientRepository.findByUserId(userId);
        return GetExcludedIngredientResource.convert(excludedIngredientsList);
    }
}

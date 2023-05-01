package com.afb.HealthyCook.domain.repository;

import com.afb.HealthyCook.domain.model.ExcludedIngredients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExcludedIngredientRepository extends JpaRepository<ExcludedIngredients, Integer> {

    @Query("SELECT ei FROM ExcludedIngredients ei WHERE ei.user.id = ?1")
    List<ExcludedIngredients> findByUserId(Integer userId);
}

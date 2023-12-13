package com.foodrama.foodrama.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodrama.foodrama.model.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Long>{

}

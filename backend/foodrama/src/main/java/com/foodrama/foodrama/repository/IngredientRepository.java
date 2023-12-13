package com.foodrama.foodrama.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodrama.foodrama.model.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Long>{

}
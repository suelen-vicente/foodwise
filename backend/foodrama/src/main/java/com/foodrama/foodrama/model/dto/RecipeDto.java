package com.foodrama.foodrama.model.dto;

import java.util.Set;
import java.util.stream.Collectors;

import com.foodrama.foodrama.model.Recipe;

public record RecipeDto(Long id, String name, Double price, String steps, Long portion, Set<RecipeIngredientDto> ingredients) {

	public RecipeDto(Recipe recipe) {
		this(recipe.getId(), 
				recipe.getName(), 
				recipe.getPrice(), 
				recipe.getSteps(), 
				recipe.getPortion(), 
				recipe.getIngredients()
					.stream()
					.map(RecipeIngredientDto::new)
					.collect(Collectors.toSet()));
	}
	
	public Recipe toEntity() {
		Recipe recipe = new Recipe();
		recipe.setName(this.name());
		recipe.setPrice(this.price());
		recipe.setSteps(this.steps);
		recipe.setPortion(this.portion);
		recipe.setIngredients(this.ingredients()
				.stream()
				.map(ing -> ing.toEntity())
				.collect(Collectors.toSet()));
        
        return recipe;
	}

}

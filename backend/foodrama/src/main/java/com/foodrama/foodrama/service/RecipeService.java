package com.foodrama.foodrama.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodrama.foodrama.model.Recipe;
import com.foodrama.foodrama.model.dto.RecipeDto;
import com.foodrama.foodrama.model.dto.IngredientDto;
import com.foodrama.foodrama.repository.RecipeRepository;

@Service
public class RecipeService {
	
	@Autowired
	private RecipeRepository recipeRepository;
	
	/**
	 * Returns a list of all available recipes in the database ordered alphabetically.
	 *
	 * @return list of recipes sorted by name ascending
	 */
	public List<RecipeDto> getAllRecipes() {
		return recipeRepository.findAll()
				.stream()
				.sorted()
				.map(RecipeDto::new)
				.toList();
	}
	
	/**
	 * Returns the ingredient with passed id.
	 *
	 * @param id id of the ingredient
	 * @return the ingredient with the matching id requested
	 */
	public RecipeDto getRecipeById(Long id) {
		return recipeRepository.findById(id)
				.map(RecipeDto::new)
				.orElse(null);
	}
	
	/**
     * Save a new recipe to the database.
     *
     * @param recipeDto the DTO containing information about the recipe and its ingredients
     * @return the saved ingredient as a DTO
     */
    public RecipeDto saveRecipe(RecipeDto recipeDto) {
    	Recipe savedRecipe = recipeRepository.save(recipeDto.toEntity());
        return new RecipeDto(savedRecipe);
    }
    
    /**
     * Edit a recipe in the database.
     *
     * @param id id of the recipe
     * @param recipeDto the DTO containing information about the recipe
     * @return the saved recipe as a DTO
     */
    public RecipeDto editRecipe(Long id, RecipeDto recipeDto) {
    	Recipe recipe = recipeDto.toEntity();
    	recipe.setId(id);
    	recipe.setPrice(calculateRecipePrice(recipeDto.ingredients()));
    	
    	Recipe savedRecipe = recipeRepository.save(recipeDto.toEntity());
        return new RecipeDto(savedRecipe);
    }

    /**
     * Delete the ingredient with the specified ID from the database.
     *
     * @param id the ID of the ingredient to delete
     */
    public void deleteRecipeById(Long id) {
    	recipeRepository.deleteById(id);
    }
    
    private Double calculateRecipePrice(Set<IngredientDto> ingredients) {
        return ingredients.stream()
        		.mapToDouble(IngredientDto::price)
                .sum();
    }
}

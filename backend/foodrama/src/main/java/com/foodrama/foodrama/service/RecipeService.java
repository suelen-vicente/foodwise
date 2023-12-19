package com.foodrama.foodrama.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodrama.foodrama.model.Recipe;
import com.foodrama.foodrama.model.dto.IngredientDto;
import com.foodrama.foodrama.model.dto.RecipeDto;
import com.foodrama.foodrama.repository.IngredientRepository;
import com.foodrama.foodrama.repository.RecipeRepository;

@Service
public class RecipeService {
	
	@Autowired
	private RecipeRepository recipeRepository;
	
	@Autowired
	private IngredientRepository ingredientRepository;
	
	/**
	 * Returns a list of all available recipes in the database ordered alphabetically.
	 *
	 * @return list of recipes sorted by name ascending
	 */
	public List<RecipeDto> getAll() {
		return recipeRepository.findAll()
				.stream()
				.sorted()
				.map(RecipeDto::new)
				.collect(Collectors.toList());
	}
	
	/**
	 * Returns the ingredient with passed id.
	 *
	 * @param id id of the ingredient
	 * @return the ingredient with the matching id requested
	 */
	public RecipeDto getById(Long id) {
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
    public RecipeDto save(RecipeDto recipeDto) {
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
    public RecipeDto edit(Long id, RecipeDto recipeDto) {
    	List<Long> ingredientIds = recipeDto.ingredients()
    			.stream()
                .map(IngredientDto::id)
                .collect(Collectors.toList());
    	
    	if (!ingredientRepository.existsAllByIdIn(ingredientIds)) { 
			throw new IllegalArgumentException("Ingredient not found");
		}
    	
    	Recipe recipe = recipeDto.toEntity();
    	recipe.setId(id);
    	
    	return new RecipeDto(recipeRepository.save(recipeDto.toEntity()));
    }

    /**
     * Delete the ingredient with the specified ID from the database.
     *
     * @param id the ID of the ingredient to delete
     */
    public void deleteById(Long id) {
    	recipeRepository.deleteById(id);
    }
}

package com.foodrama.foodrama.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.foodrama.foodrama.model.Ingredient;
import com.foodrama.foodrama.model.Recipe;
import com.foodrama.foodrama.model.RecipeIngredient;
import com.foodrama.foodrama.model.RecipeIngredientId;
import com.foodrama.foodrama.model.dto.RecipeDto;
import com.foodrama.foodrama.model.dto.RecipeIngredientDto;
import com.foodrama.foodrama.repository.IngredientRepository;
import com.foodrama.foodrama.repository.RecipeRepository;

import jakarta.transaction.Transactional;

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
	@Transactional
	public List<RecipeDto> getAll() {
		try {
		return recipeRepository.findAll()
				.stream()
				.sorted()
				.map(RecipeDto::new)
				.collect(Collectors.toList());
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR , "Error retrieving recipe: " + e.getMessage());
		}
	}
	
	/**
	 * Returns the ingredient with passed id.
	 *
	 * @param id id of the ingredient
	 * @return the ingredient with the matching id requested
	 */
	@Transactional
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
	@Transactional
    public RecipeDto save(RecipeDto recipeDto) {
    	try {
    		Recipe recipe = recipeDto.toEntity();
    		
    		Set<RecipeIngredient> recipeIngs = new HashSet<RecipeIngredient>();
    		
    		for(RecipeIngredientDto ing: recipeDto.ingredients()) {
    			RecipeIngredient recipeIng = ing.toEntity();
    			Ingredient ingredient = ingredientRepository.findById(ing.ingredient().id()).orElse(null);
    			
    			if(ingredient == null) {
    				throw new ResponseStatusException(HttpStatus.NOT_FOUND , "Error saving recipe. Ingredient not found"); 
    			}
    			
    			recipeIng.setIngredient(ingredient);
    			recipeIng.setRecipe(recipe);
    			recipeIng.setRecipeIngredientId(new RecipeIngredientId(ingredient.getId(), recipe.getId()));
    			recipeIngs.add(recipeIng);
    		}
    		
    		recipe.setIngredients(recipeIngs);
    				
	    	Recipe savedRecipe = recipeRepository.save(recipe);
	        return new RecipeDto(savedRecipe);
    	} catch (Exception e) {
    		throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR , "Error saving recipe: " + e.getMessage());
		}
    }
    
    /**
     * Edit a recipe in the database.
     *
     * @param id id of the recipe
     * @param recipeDto the DTO containing information about the recipe
     * @return the saved recipe as a DTO
     */
    public RecipeDto edit(Long id, RecipeDto recipeDto) {
//    	List<Long> ingredientIds = recipeDto.ingredients()
//    			.stream()
//                .map(IngredientDto::id)
//                .collect(Collectors.toList());
//    	
//    	if (!ingredientRepository.existsAllByIdIn(ingredientIds)) { 
//			throw new IllegalArgumentException("Ingredient not found");
//		}
//    	
//    	Recipe recipe = recipeDto.toEntity();
//    	recipe.setId(id);
    	
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

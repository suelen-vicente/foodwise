package com.foodrama.foodrama.service;

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
    		
    		Set<RecipeIngredient> ingredients = recipeDto.ingredients().stream()
                .map(ing -> {
                		Ingredient ingredient = ingredientRepository.findById(ing.ingredient().id())
                        		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingredient not found with id: " + ing.ingredient().id()));
                		
                		return ing.toEntity(recipe, ingredient);
                })
                .collect(Collectors.toSet());
    		
    		recipe.setIngredients(ingredients);
    		
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
	@Transactional
    public RecipeDto edit(Long id, RecipeDto recipeDto) {
    	try {
    		Recipe existingRecipe = recipeRepository.findById(id)
    			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Recipe not found with id: " + id));
    		
    		if (recipeDto.name() != null) {
                existingRecipe.setName(recipeDto.name());
            }
            if (recipeDto.price() != null) {
                existingRecipe.setPrice(recipeDto.price());
            }
            if (recipeDto.steps() != null) {
                existingRecipe.setSteps(recipeDto.steps());
            }
            if (recipeDto.portion() != null) {
                existingRecipe.setPortion(recipeDto.portion());
            }
            
            existingRecipe.getIngredients().clear();
            
            for(RecipeIngredientDto ing : recipeDto.ingredients()) {
            	Ingredient ingredient = ingredientRepository.findById(ing.ingredient().id())
                		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingredient not found with id: " + ing.ingredient().id()));
        		
        		RecipeIngredient recipeIngredient = ing.toEntity(existingRecipe, ingredient);
        		existingRecipe.getIngredients().add(recipeIngredient);
            }
    		
    		Recipe savedRecipe = recipeRepository.save(existingRecipe);
	        return new RecipeDto(savedRecipe);
    	} catch (Exception e) {
    		throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR , "Error saving recipe: " + e.getMessage());
		}
    }

    /**
     * Delete the ingredient with the specified ID from the database.
     *
     * @param id the ID of the ingredient to delete
     */
	@Transactional
    public void deleteById(Long id) {
    	recipeRepository.deleteById(id);
    }
}

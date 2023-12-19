package com.foodrama.foodrama.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodrama.foodrama.model.Ingredient;
import com.foodrama.foodrama.model.dto.IngredientDto;
import com.foodrama.foodrama.repository.IngredientRepository;

@Service
public class IngredientService {

	@Autowired
	private IngredientRepository ingredientRepository;
	
	/**
	 * Returns a list of all available ingredients in the database ordered alphabetically.
	 *
	 * @return list of ingredients sorted by name ascending
	 */
	public List<IngredientDto> getAllIngredients() {
		return ingredientRepository.findAll()
				.stream()
				.sorted()
				.map(ingredient -> new IngredientDto(ingredient))
				.toList();
	}
	
	/**
	 * Returns the ingredient with passed id.
	 *
	 * @param id id of the ingredient
	 * @return the ingredient with the matching id requested
	 */
	public IngredientDto getIngredientsById(Long id) {
		return ingredientRepository.findById(id)
				.map(ingredient -> new IngredientDto(ingredient))
				.orElse(null);
	}
	
	/**
     * Save a new ingredient to the database.
     *
     * @param ingredientDto the DTO containing information about the ingredient
     * @return the saved ingredient as a DTO
     */
    public IngredientDto saveIngredient(IngredientDto ingredientDto) {
        Ingredient savedIngredient = ingredientRepository.save(ingredientDto.toEntity());
        return new IngredientDto(savedIngredient);
    }
    
    /**
     * Edit a ingredient in the database.
     *
     * @param id id of the ingredient
     * @param ingredientDto the DTO containing information about the ingredient
     * @return the saved ingredient as a DTO
     */
    public IngredientDto editIngredient(Long id, IngredientDto ingredientDto) {
    	Ingredient ingredient = ingredientDto.toEntity();
    	ingredient.setId(id);
    	
        Ingredient savedIngredient = ingredientRepository.save(ingredient);
        return new IngredientDto(savedIngredient);
    }

    /**
     * Delete the ingredient with the specified ID from the database.
     *
     * @param id the ID of the ingredient to delete
     */
    public void deleteIngredientById(Long id) {
    	//This one is throwing a 500 internal error, because it can't find the relationship table
        ingredientRepository.deleteById(id);
    }
}

package com.foodrama.foodrama.service;

import java.util.List;
import java.util.stream.Collectors;

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
				.map(IngredientDto::new)
				.collect(Collectors.toList());
	}
	
	/**
	 * Returns the ingredient with passed id.
	 *
	 * @param id id of the ingredient
	 * @return the ingredient with the matching id requested
	 */
	public IngredientDto getIngredientsById(Long id) {
		return ingredientRepository.findById(id)
				.map(IngredientDto::new)
				.orElse(null);
	}
	
	/**
     * Save a new ingredient to the database.
     *
     * @param ingredientDto the DTO containing information about the ingredient
     * @return the saved ingredient as a DTO
     */
    public IngredientDto saveIngredient(IngredientDto ingredientDto) {
        return new IngredientDto(ingredientRepository.save(ingredientDto.toEntity()));
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
    	
        return new IngredientDto(ingredientRepository.save(ingredient));
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

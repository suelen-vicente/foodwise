package com.foodrama.foodrama.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodrama.foodrama.model.dto.ShoppingListDto;
import com.foodrama.foodrama.repository.IngredientRepository;
import com.foodrama.foodrama.repository.ShoppingListRepository;

@Service
public class ShoppingListService {

	@Autowired
	private ShoppingListRepository shoppingListRepository;
	
	@Autowired
	private IngredientRepository ingredientRepository;
	
	/**
	 * Returns the list of ingredients that a user has in their fridge
	 *
	 * @param id of the user
	 * @return the list of ingredients in the fridge
	 */
	public List<ShoppingListDto> getByUserId(Long userId) {
		return shoppingListRepository
				.findByUserId(userId)
				.stream()
				.map(ShoppingListDto::new)
				.collect(Collectors.toList());
	}
	
	/**
     * Save a new fridge ingredient to the database.
     *
     * @param ingredientDto the DTO containing information about the ingredient
     * @return the saved ingredient as a DTO
     */
    public ShoppingListDto addIngredient(ShoppingListDto ingredient) {
    	//Checks if ingredient exists
    	if(ingredientRepository.existsById(ingredient.ingredient().id())) {
    		throw new IllegalArgumentException("Ingredient not found");
    	}
    	
    	return new ShoppingListDto(shoppingListRepository.save(ingredient.toEntity()));
    }

    /**
     * Delete the ingredient with the specified ID from the shopping list.
     *
     * @param id the ID of the ingredient to delete
     */
    public void deleteByIngredientId(Long ingredientId) {
    	shoppingListRepository.deleteByIngredientId(ingredientId);
    }
    
    /**
     * Delete the entire shopping list of a user.
     *
     * @param id the ID of the user to delete
     */
    public void delete(Long userId) {
    	shoppingListRepository.deleteByUserId(userId);
    }
    
}

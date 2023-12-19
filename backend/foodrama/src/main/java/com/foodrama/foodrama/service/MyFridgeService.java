package com.foodrama.foodrama.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodrama.foodrama.model.dto.MyFridgeDto;
import com.foodrama.foodrama.repository.IngredientRepository;
import com.foodrama.foodrama.repository.MyFridgeRepository;

@Service
public class MyFridgeService {
	
	@Autowired
	private MyFridgeRepository myFridgeRepository;
	
	@Autowired
	private IngredientRepository ingredientRepository;
	
	/**
	 * Returns the list of ingredients that a user has in their fridge
	 *
	 * @param id of the user
	 * @return the list of ingredients in the fridge
	 */
	public List<MyFridgeDto> getByUserId(Long userId) {
		return myFridgeRepository
				.findByUserId(userId)
				.stream()
				.map(MyFridgeDto::new)
				.collect(Collectors.toList());
	}
	
	/**
     * Save a new fridge ingredient to the database.
     *
     * @param ingredientDto the DTO containing information about the ingredient
     * @return the saved ingredient as a DTO
     */
    public MyFridgeDto addIngredient(MyFridgeDto fridgeIngredient) {
    	//Checks if ingredient exists
    	if(ingredientRepository.existsById(fridgeIngredient.ingredient().id())) {
    		throw new IllegalArgumentException("Ingredient not found");
    	}
    	
    	return new MyFridgeDto(myFridgeRepository.save(fridgeIngredient.toEntity()));
    }
    
    /**
     * Edit a fridge ingredient
     *
     * @param fridgeIngredient the DTO containing information about the ingredient
     * @return the saved ingredient as a DTO
     */
    public MyFridgeDto editIngredient(Long userId, Long ingredientId, MyFridgeDto fridgeIngredient) {
    	//Checks if ingredient exists
    	if(!myFridgeRepository.findByUserIdAndIngredientId(userId, ingredientId).isPresent()) {
    		throw new IllegalArgumentException("My Fridge Ingredient not found");
    	}
    	
    	return new MyFridgeDto(myFridgeRepository.save(fridgeIngredient.toEntity()));
    }

    /**
     * Delete the ingredient with the specified ID from the fridge.
     *
     * @param id the ID of the ingredient to delete
     */
    public void deleteIngredient(Long ingredientId) {
    	myFridgeRepository.deleteByIngredientId(ingredientId);
    }
    
    /**
     * Delete the entire fridge of a user.
     *
     * @param id the ID of the user to delete
     */
    public void delete(Long userId) {
    	myFridgeRepository.deleteByUserId(userId);
    }
}

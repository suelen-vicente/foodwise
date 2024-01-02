package com.foodrama.foodrama.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.foodrama.foodrama.model.AppUser;
import com.foodrama.foodrama.model.Ingredient;
import com.foodrama.foodrama.model.MyFridge;
import com.foodrama.foodrama.model.ShoppingList;
import com.foodrama.foodrama.model.dto.ShoppingListDto;
import com.foodrama.foodrama.repository.AppUserRepository;
import com.foodrama.foodrama.repository.IngredientRepository;
import com.foodrama.foodrama.repository.ShoppingListRepository;

import jakarta.transaction.Transactional;

@Service
public class ShoppingListService {

	@Autowired
	private ShoppingListRepository shoppingListRepository;
	
	@Autowired
	private IngredientRepository ingredientRepository;
	
	@Autowired
	private AppUserRepository userRepository;
	
	/**
	 * Returns the list of ingredients that a user has in their shopping list
	 *
	 * @param id of the user
	 * @return the list of ingredients in the shopping list
	 */
	@Transactional
	public List<ShoppingListDto> getByUserId(Long userId) {
		return shoppingListRepository.findByUserId(userId).stream()
				.map(ShoppingListDto::new)
				.collect(Collectors.toList());
	}
	
	/**
     * Save a new shopping list ingredient to the database.
     *
     * @param shoppingListIngredientDto the DTO containing information about the ingredient
     * @return the saved ingredient as a DTO
     */
	@Transactional
    public ShoppingListDto addIngredient(ShoppingListDto shoppingListIngredientDto) {
    	AppUser user = userRepository.findById(shoppingListIngredientDto.user().id())
        		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + shoppingListIngredientDto.user().id()));
    	
    	Ingredient ingredient = ingredientRepository.findById(shoppingListIngredientDto.ingredient().id())
        		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingredient not found with id: " + shoppingListIngredientDto.ingredient().id()));
    	try {
    		ShoppingList savedIngredient = shoppingListRepository.save(shoppingListIngredientDto.toEntity(user, ingredient));
        	return new ShoppingListDto(savedIngredient);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR , "Error saving ingredient: " + e.getMessage());
		}
    }
    
    /**
     * Edit a shopping list ingredient
     *
     * @param shoppingListIngredient the DTO containing information about the ingredient
     * @return the saved ingredient as a DTO
     */
	@Transactional
    public ShoppingListDto editIngredient(Long userId, Long ingredientId, ShoppingListDto shoppingListIngredientDto) {
    	
    	userRepository.findById(userId)
        		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + userId));
    	
    	ingredientRepository.findById(ingredientId)
        		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingredient not found with id: " + ingredientId));
    	
    	ShoppingList shoppingListIngredient = shoppingListRepository.findByUserIdAndIngredientId(userId, ingredientId)
        		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingredient not found in My Fridge with id: " + ingredientId));
    	
    	try {
    		
    		shoppingListIngredient.setQuantity(shoppingListIngredientDto.quantity());
    		shoppingListIngredient.setQuantityUnit(shoppingListIngredientDto.quantityUnit().getLabel());
    		
    		ShoppingList savedIngredient = shoppingListRepository.save(shoppingListIngredient);
        	return new ShoppingListDto(savedIngredient);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR , "Error saving ingredient: " + e.getMessage());
		}
    }

    /**
     * Delete the ingredient with the specified ID from the shopping list.
     *
     * @param id the ID of the ingredient to delete
     */
	@Transactional
    public void deleteIngredient(Long userId, Long ingredientId) {
		userRepository.findById(userId)
        		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + userId));
    	
    	try {
    		if(ingredientId != null) {
    	    	ingredientRepository.findById(ingredientId)
    	        		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingredient not found with id: " + ingredientId));
    	    	
    	    	ShoppingList myFridgeIngredient = shoppingListRepository.findByUserIdAndIngredientId(userId, ingredientId)
    	        		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingredient not found in My Fridge with id: " + ingredientId + " for the user id: " + userId));
    	    	
    	    	shoppingListRepository.delete(myFridgeIngredient);
    		} else {
    			shoppingListRepository.deleteByUserId(userId);
    		}
    	} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR , "Error deleting ingredient: " + e.getMessage());
		}
    }
}

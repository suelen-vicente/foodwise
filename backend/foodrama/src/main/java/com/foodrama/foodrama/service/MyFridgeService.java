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
import com.foodrama.foodrama.model.dto.MyFridgeDto;
import com.foodrama.foodrama.repository.AppUserRepository;
import com.foodrama.foodrama.repository.IngredientRepository;
import com.foodrama.foodrama.repository.MyFridgeRepository;

import jakarta.transaction.Transactional;

@Service
public class MyFridgeService {
	
	@Autowired
	private MyFridgeRepository myFridgeRepository;
	
	@Autowired
	private IngredientRepository ingredientRepository;
	
	@Autowired
	private AppUserRepository userRepository;
	
	/**
	 * Returns the list of ingredients that a user has in their fridge
	 *
	 * @param id of the user
	 * @return the list of ingredients in the fridge
	 */
	@Transactional
	public List<MyFridgeDto> getByUserId(Long userId) {
		return myFridgeRepository.findByUserId(userId).stream()
				.map(MyFridgeDto::new)
				.collect(Collectors.toList());
	}
	
	/**
     * Save a new fridge ingredient to the database.
     *
     * @param ingredientDto the DTO containing information about the ingredient
     * @return the saved ingredient as a DTO
     */
	@Transactional
    public MyFridgeDto addIngredient(MyFridgeDto fridgeIngredient) {
    	AppUser user = userRepository.findById(fridgeIngredient.user().id())
        		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + fridgeIngredient.user().id()));
    	
    	Ingredient ingredient = ingredientRepository.findById(fridgeIngredient.ingredient().id())
        		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingredient not found with id: " + fridgeIngredient.ingredient().id()));
    	try {
    		MyFridge savedIngredient = myFridgeRepository.save(fridgeIngredient.toEntity(user, ingredient));
        	return new MyFridgeDto(myFridgeRepository.save(savedIngredient));
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR , "Error saving ingredient: " + e.getMessage());
		}
    }
    
    /**
     * Edit a fridge ingredient
     *
     * @param fridgeIngredient the DTO containing information about the ingredient
     * @return the saved ingredient as a DTO
     */
	@Transactional
    public MyFridgeDto editIngredient(Long userId, Long ingredientId, MyFridgeDto fridgeIngredient) {
    	
    	AppUser user = userRepository.findById(userId)
        		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + userId));
    	
    	Ingredient ingredient = ingredientRepository.findById(ingredientId)
        		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingredient not found with id: " + ingredientId));
    	
    	MyFridge myFridgeIngredient = myFridgeRepository.findByUserIdAndIngredientId(userId, ingredientId)
        		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingredient not found in My Fridge with id: " + ingredientId + " for the user id: " + userId));
    	
    	try {
    		
    		myFridgeIngredient.setAvailableQuantity(fridgeIngredient.availableQuantity());
    		myFridgeIngredient.setQuantityUnit(fridgeIngredient.quantityUnit().getLabel());
    		
    		MyFridge savedIngredient = myFridgeRepository.save(fridgeIngredient.toEntity(user, ingredient));
        	return new MyFridgeDto(myFridgeRepository.save(savedIngredient));
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR , "Error saving ingredient: " + e.getMessage());
		}
    }

    /**
     * Delete the ingredient with the specified ID from the fridge.
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
    	    	
    	    	MyFridge myFridgeIngredient = myFridgeRepository.findByUserIdAndIngredientId(userId, ingredientId)
    	        		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingredient not found in My Fridge with id: " + ingredientId + " for the user id: " + userId));
    	    	
    	    	myFridgeRepository.delete(myFridgeIngredient);
    		} else {
    			myFridgeRepository.deleteByUserId(userId);
    		}
    	} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR , "Error deleting ingredient: " + e.getMessage());
		}
    }
    
    /**
     * Delete the entire fridge of a user.
     *
     * @param id the ID of the user to delete
     */
//	@Transactional
//    public void delete(Long userId) {
//		userRepository.findById(userId)
//		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + userId));
//		
//		try {
//			
//		} catch (Exception e) {
//			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR , "Error deleting ingredient: " + e.getMessage());
//		}
//    }
}

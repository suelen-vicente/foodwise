package com.foodrama.foodrama.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodrama.foodrama.model.Ingredient;
import com.foodrama.foodrama.model.MyFridge;
import com.foodrama.foodrama.model.QuantityUnit;
import com.foodrama.foodrama.model.dto.IngredientDto;
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
	 * Returns the ingredient with passed id.
	 *
	 * @param id id of the ingredient
	 * @return the ingredient with the matching id requested
	 */
	public MyFridgeDto getFridgeById(Long userId) {
		
		MyFridge fridge = myFridgeRepository.findById(userId).orElse(null);
		Ingredient fridgeIngredient = fridge.getIngredient();
		
		IngredientDto ingredient = 
				new IngredientDto(
						fridgeIngredient.getId(), 
						fridgeIngredient.getName(), 
						fridgeIngredient.getPrice(), 
						fridgeIngredient.getPackageQuantity(), 
						QuantityUnit.fromLabel(fridgeIngredient.getQuantityUnit()));
		
		return new MyFridgeDto(
				fridge.getUserId(), 
				ingredient, 
				fridge.getAvailableQuantity(), 
				QuantityUnit.fromLabel(fridge.getQuantityUnit()));
	}
	
	/**
     * Save a new ingredient to the database.
     *
     * @param ingredientDto the DTO containing information about the ingredient
     * @return the saved ingredient as a DTO
     */
    public MyFridgeDto addIngredient(MyFridgeDto fridgeIngredient) {
    	Ingredient ingredient = ingredientRepository.findById(fridgeIngredient.ingredient().id()).orElse(null);
    	
    	MyFridge myFridgeIngredient = new MyFridge();
    	myFridgeIngredient.setUserId(fridgeIngredient.userId());
    	myFridgeIngredient.setIngredient(ingredient);
    	myFridgeIngredient.setAvailableQuantity(fridgeIngredient.availableQuantity());
    	myFridgeIngredient.setQuantityUnit(fridgeIngredient.quantityUnit().getLabel());

        MyFridge savedFridgeIngredient = myFridgeRepository.save(myFridgeIngredient);

        return new MyFridgeDto(
        		savedFridgeIngredient.getUserId(),
        		fridgeIngredient.ingredient(),
        		savedFridgeIngredient.getAvailableQuantity(),
                QuantityUnit.fromLabel(savedFridgeIngredient.getQuantityUnit())
        );
    }

    /**
     * Delete the ingredient with the specified ID from the database.
     *
     * @param id the ID of the ingredient to delete
     */
    public void deleteIngredientById(Long id) {
        ingredientRepository.deleteById(id);
    }
}

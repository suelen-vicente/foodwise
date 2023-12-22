package com.foodrama.foodrama.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
	public List<IngredientDto> getAll() {
		List<IngredientDto> ingredients = ingredientRepository.findAll()
				.stream()
				.sorted()
				.map(IngredientDto::new)
				.collect(Collectors.toList());
		
		if(ingredients.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No ingredients found");
		}
		
		return ingredients;
	}
	
	/**
	 * Returns the ingredient with passed id.
	 *
	 * @param id id of the ingredient
	 * @return the ingredient with the matching id requested
	 */
	public IngredientDto getById(Long id) {
		
		IngredientDto ingredient = ingredientRepository.findById(id)
				.map(IngredientDto::new)
				.orElse(null);
		
		if(ingredient == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No ingredient found");
		}
		
		return ingredient;
		
	}
	
	/**
     * Save a new ingredient to the database.
     *
     * @param ingredientDto the DTO containing information about the ingredient
     * @return the saved ingredient as a DTO
     */
    public IngredientDto save(IngredientDto ingredientDto) {
    	try {
    		return new IngredientDto(ingredientRepository.save(ingredientDto.toEntity()));
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR , "Error saving ingredient");
		}
    }
    
    /**
     * Edit a ingredient in the database.
     *
     * @param id id of the ingredient
     * @param ingredientDto the DTO containing information about the ingredient
     * @return the saved ingredient as a DTO
     */
    public IngredientDto edit(Long id, IngredientDto ingredientDto) {
    	try {
	    	Ingredient ingredient = ingredientDto.toEntity();
	    	ingredient.setId(id);
	    	
	        return new IngredientDto(ingredientRepository.save(ingredient));
    	} catch (Exception e) {
    		throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR , "Error editing ingredient");
    	}
    }

    /**
     * Delete the ingredient with the specified ID from the database.
     *
     * @param id the ID of the ingredient to delete
     */
    public void delete(Long id) {
    	try {
    		ingredientRepository.deleteById(id);
    	} catch (Exception e) {
    		throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR , "Error deleting ingredient");
    	}
    }
}

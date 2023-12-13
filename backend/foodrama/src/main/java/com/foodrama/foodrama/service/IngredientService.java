package com.foodrama.foodrama.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodrama.foodrama.model.Ingredient;
import com.foodrama.foodrama.model.QuantityUnit;
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
				.map(i -> new IngredientDto(i.getId(), i.getName(), i.getPrice(), i.getPackageQuantity(), QuantityUnit.fromLabel(i.getQuantityUnit())))
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
				.map(i -> new IngredientDto(i.getId(), i.getName(), i.getPrice(), i.getPackageQuantity(), QuantityUnit.fromLabel(i.getQuantityUnit())))
				.orElse(null);
	}
	
	/**
     * Save a new ingredient to the database.
     *
     * @param ingredientDto the DTO containing information about the ingredient
     * @return the saved ingredient as a DTO
     */
    public IngredientDto saveIngredient(IngredientDto ingredientDto) {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(ingredientDto.name());
        ingredient.setPrice(ingredientDto.price());
        ingredient.setPackageQuantity(ingredientDto.packageQuantity());
        ingredient.setQuantityUnit(ingredientDto.quantityUnit().getLabel());

        Ingredient savedIngredient = ingredientRepository.save(ingredient);

        return new IngredientDto(
                savedIngredient.getId(),
                savedIngredient.getName(),
                savedIngredient.getPrice(),
                savedIngredient.getPackageQuantity(),
                QuantityUnit.fromLabel(savedIngredient.getQuantityUnit())
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

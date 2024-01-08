package com.foodrama.foodrama.model.dto;

import com.foodrama.foodrama.model.Ingredient;
import com.foodrama.foodrama.model.QuantityUnit;

public record IngredientDto(Long id, String name, String barCode, Double price, Double packageQuantity, QuantityUnit quantityUnit) {
	
	public IngredientDto(Ingredient ingredient) {
		this(ingredient.getId(), 
				ingredient.getName(), 
				ingredient.getBarCode(),
				ingredient.getPrice(), 
				ingredient.getPackageQuantity(), 
				QuantityUnit.fromLabel(ingredient.getQuantityUnit()));
	}
	
	public Ingredient toEntity() {
		Ingredient ingredient = new Ingredient();
        ingredient.setName(this.name());
        ingredient.setBarCode(this.barCode);
        ingredient.setPrice(this.price());
        ingredient.setPackageQuantity(this.packageQuantity());
        ingredient.setQuantityUnit(this.quantityUnit().getLabel());
        
        return ingredient;
	}
}
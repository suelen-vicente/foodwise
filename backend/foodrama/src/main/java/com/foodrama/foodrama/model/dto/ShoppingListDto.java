package com.foodrama.foodrama.model.dto;

import com.foodrama.foodrama.model.QuantityUnit;
import com.foodrama.foodrama.model.ShoppingList;

public record ShoppingListDto(Long userId, IngredientDto ingredient, Double quantity, QuantityUnit quantityUnit) {
	
	public ShoppingListDto(ShoppingList shoppingList) {
		this(shoppingList.getUserId(), 
				new IngredientDto(shoppingList.getIngredient()),
				shoppingList.getQuantity(),
				QuantityUnit.fromLabel(shoppingList.getQuantityUnit()));
	}
	
	public ShoppingList toEntity() {
		ShoppingList shoppingList = new ShoppingList();
		shoppingList.setUserId(this.userId());
		shoppingList.setIngredient(this.ingredient().toEntity());
		shoppingList.setQuantity(this.quantity());
		shoppingList.setQuantityUnit(this.quantityUnit().getLabel());
        
        return shoppingList;
	}
}
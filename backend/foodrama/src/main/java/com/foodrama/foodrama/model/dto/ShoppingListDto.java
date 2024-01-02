package com.foodrama.foodrama.model.dto;

import com.foodrama.foodrama.model.AppUser;
import com.foodrama.foodrama.model.Ingredient;
import com.foodrama.foodrama.model.QuantityUnit;
import com.foodrama.foodrama.model.ShoppingList;
import com.foodrama.foodrama.model.ShoppingListId;

public record ShoppingListDto(AppUserDto user, IngredientDto ingredient, Double quantity, QuantityUnit quantityUnit) {
	
	public ShoppingListDto(ShoppingList shoppingList) {
		this(new AppUserDto(shoppingList.getUser()), 
				new IngredientDto(shoppingList.getIngredient()),
				shoppingList.getQuantity(),
				QuantityUnit.fromLabel(shoppingList.getQuantityUnit()));
	}
	
	public ShoppingList toEntity(AppUser user, Ingredient ingredient) {
		ShoppingList shoppingList = new ShoppingList();
		shoppingList.setShoppingListId(new ShoppingListId(user.getId(), ingredient.getId()));
		shoppingList.setUser(user);
		shoppingList.setIngredient(ingredient);
		shoppingList.setQuantity(this.quantity());
		shoppingList.setQuantityUnit(this.quantityUnit().getLabel());
        
        return shoppingList;
	}
}
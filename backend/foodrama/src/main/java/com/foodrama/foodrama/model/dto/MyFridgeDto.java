package com.foodrama.foodrama.model.dto;

import com.foodrama.foodrama.model.AppUser;
import com.foodrama.foodrama.model.Ingredient;
import com.foodrama.foodrama.model.MyFridge;
import com.foodrama.foodrama.model.MyFridgeId;
import com.foodrama.foodrama.model.QuantityUnit;

public record MyFridgeDto(AppUserDto user, IngredientDto ingredient, Double availableQuantity, QuantityUnit quantityUnit) {
	
	public MyFridgeDto(MyFridge myFridge) {
		this(new AppUserDto(myFridge.getUser()), 
				new IngredientDto(myFridge.getIngredient()),
				myFridge.getAvailableQuantity(),
				QuantityUnit.fromLabel(myFridge.getQuantityUnit()));
	}
	
	public MyFridge toEntity(AppUser user, Ingredient ingredient) {
		MyFridge myFridge = new MyFridge();
		myFridge.setMyFridgeId(new MyFridgeId(user.getId(), ingredient.getId()));
		myFridge.setUser(user);
		myFridge.setIngredient(ingredient);
		myFridge.setAvailableQuantity(this.availableQuantity());
		myFridge.setQuantityUnit(this.quantityUnit().getLabel());
        
        return myFridge;
	}
}
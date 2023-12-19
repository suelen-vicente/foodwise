package com.foodrama.foodrama.model.dto;

import com.foodrama.foodrama.model.MyFridge;
import com.foodrama.foodrama.model.QuantityUnit;

public record MyFridgeDto(Long userId, IngredientDto ingredient, Double availableQuantity, QuantityUnit quantityUnit) {
	
	public MyFridgeDto(MyFridge myFridge) {
		this(myFridge.getUserId(), 
				new IngredientDto(myFridge.getIngredient()),
				myFridge.getAvailableQuantity(),
				QuantityUnit.fromLabel(myFridge.getQuantityUnit()));
	}
	
	public MyFridge toEntity() {
		MyFridge myFridge = new MyFridge();
		myFridge.setUserId(this.userId());
		myFridge.setIngredient(this.ingredient().toEntity());
		myFridge.setAvailableQuantity(this.availableQuantity());
		myFridge.setQuantityUnit(this.quantityUnit().getLabel());
        
        return myFridge;
	}
}
package com.foodrama.foodrama.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ShoppingListId {
	
	@Column(name = "user_id")
	private Long userId;

	@Column(name = "ingredient_id")
	private Long ingredientId;
	
	public ShoppingListId() {
    }

	public ShoppingListId(Long userId, Long ingredientId) {
		this.userId = userId;
		this.ingredientId = ingredientId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long ingredientId) {
		this.userId = ingredientId;
	}

	public Long getIngredientId() {
		return ingredientId;
	}

	public void setIngredient(Long ingredientId) {
		this.ingredientId = ingredientId;
	}
}

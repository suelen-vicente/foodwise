package com.foodrama.foodrama.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class ShoppingList {
//	@OneToOne(optional = false)
//	@JoinColumn(name = "user_id", nullable = false)
//	private User user;
	
	@OneToMany
	@JoinColumn(name = "ingredient_id", nullable = false)
	private Ingredient ingredient;
	
	private Double availableQuantity;
	
	private String quantityUnit;
	
	private Long userId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Ingredient getIngredient() {
		return ingredient;
	}

	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}

	public Double getAvailableQuantity() {
		return availableQuantity;
	}

	public void setAvailableQuantity(Double availableQuantity) {
		this.availableQuantity = availableQuantity;
	}

	public String getQuantityUnit() {
		return quantityUnit;
	}

	public void setQuantityUnit(String quantityUnit) {
		this.quantityUnit = quantityUnit;
	}
}

package com.foodrama.foodrama.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

@Entity
public class ShoppingList {
	
	@EmbeddedId
	private ShoppingListId shoppingListId;
	
	@MapsId("userId")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
	private AppUser user;
	
	@MapsId("ingredientId")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ingredient_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
	private Ingredient ingredient;
	
	private Double quantity;
	
	private String quantityUnit;

	public ShoppingListId getShoppingListId() {
		return shoppingListId;
	}

	public void setShoppingListId(ShoppingListId shoppingListId) {
		this.shoppingListId = shoppingListId;
	}

	public AppUser getUser() {
		return user;
	}

	public void setUser(AppUser user) {
		this.user = user;
	}

	public Ingredient getIngredient() {
		return ingredient;
	}

	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public String getQuantityUnit() {
		return quantityUnit;
	}

	public void setQuantityUnit(String quantityUnit) {
		this.quantityUnit = quantityUnit;
	}
}

package com.foodrama.foodrama.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

@Entity(name = "my_fridge")
public class MyFridge {
	
	@EmbeddedId
	private MyFridgeId myFridgeId;
	
	@MapsId("userId")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
	private AppUser user;
	
	@MapsId("ingredientId")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ingredient_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
	private Ingredient ingredient;
	
	private Double availableQuantity;
	
	private String quantityUnit;
	
	public MyFridgeId getMyFridgeId() {
		return myFridgeId;
	}

	public void setMyFridgeId(MyFridgeId myFridgeId) {
		this.myFridgeId = myFridgeId;
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

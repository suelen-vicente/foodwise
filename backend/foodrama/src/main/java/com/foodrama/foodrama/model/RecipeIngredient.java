package com.foodrama.foodrama.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class RecipeIngredient {
	
	@EmbeddedId
	private RecipeIngredientId recipeIngredientId;

	private String name;

	private Double price;
	
	private Double packageQuantity;
	
	private String quantityUnit;
	
	private String barCode;

	public RecipeIngredientId getRecipeIngredientId() {
		return recipeIngredientId;
	}

	public void setRecipeIngredientId(RecipeIngredientId recipeIngredientId) {
		this.recipeIngredientId = recipeIngredientId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getPackageQuantity() {
		return packageQuantity;
	}

	public void setPackageQuantity(Double packageQuantity) {
		this.packageQuantity = packageQuantity;
	}

	public String getQuantityUnit() {
		return quantityUnit;
	}

	public void setQuantityUnit(String quantityUnit) {
		this.quantityUnit = quantityUnit;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
	
}

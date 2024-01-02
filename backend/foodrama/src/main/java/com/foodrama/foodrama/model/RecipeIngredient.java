package com.foodrama.foodrama.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

@Entity
public class RecipeIngredient {
	
	@EmbeddedId
	private RecipeIngredientId recipeIngredientId;

	@MapsId("ingredientId")
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ingredient_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Ingredient ingredient;
	
	@MapsId("recipeId")
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "recipe_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Recipe recipe;

	private Double ingredientQuantity;
	
	@Column(name = "ingredient_quantity_unit")
	private String quantityUnit;

	public RecipeIngredientId getRecipeIngredientId() {
		return recipeIngredientId;
	}

	public void setRecipeIngredientId(RecipeIngredientId recipeIngredientId) {
		this.recipeIngredientId = recipeIngredientId;
	}

	public Ingredient getIngredient() {
		return ingredient;
	}

	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public Double getIngredientQuantity() {
		return ingredientQuantity;
	}

	public void setIngredientQuantity(Double ingredientQuantity) {
		this.ingredientQuantity = ingredientQuantity;
	}

	public String getQuantityUnit() {
		return quantityUnit;
	}

	public void setQuantityUnit(String quantityUnit) {
		this.quantityUnit = quantityUnit;
	}
	
}

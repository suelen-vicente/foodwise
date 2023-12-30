package com.foodrama.foodrama.model;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Recipe implements Comparable<Recipe>{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recipe_seq")
	@SequenceGenerator(name = "recipe_seq", sequenceName = "recipe_seq", allocationSize = 1)
	private Long id;
	
	private String name;
	
	private Double price;
	
	@Column(length = 4096)
	private String steps;
	
	private Long portion;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<RecipeIngredient> ingredients;
	
	@Override
    public int compareTo(Recipe recipe) {
        return this.name.compareTo(recipe.name);
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getSteps() {
		return steps;
	}

	public void setSteps(String steps) {
		this.steps = steps;
	}

	public Long getPortion() {
		return portion;
	}

	public void setPortion(Long portion) {
		this.portion = portion;
	}
	
	public Set<RecipeIngredient> getIngredients(){
		return ingredients;
	}
	
	public void setIngredients(Set<RecipeIngredient> ingredients){
		this.ingredients = ingredients;
	}
	
}
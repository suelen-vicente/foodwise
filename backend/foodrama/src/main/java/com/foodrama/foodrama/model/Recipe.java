package com.foodrama.foodrama.model;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Recipe {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recipe_seq")
	@SequenceGenerator(name = "recipe_seq", sequenceName = "recipe_seq", allocationSize = 1)
	private Long id;
	
	private String name;
	
	private Double price;
	
	@Column(length = 4096)
	private String steps;
	
	private Long portion;
	
	@OneToMany(
        cascade = CascadeType.ALL,
        orphanRemoval = true)
	@JoinTable(
	  name = "recipe_ingredient", 
	  joinColumns = @JoinColumn(name = "ingredient_id"), 
	  inverseJoinColumns = @JoinColumn(name = "recipe_id"))
    Set<Ingredient> ingredients;

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
	
	public Set<Ingredient> getIngredients(){
		return ingredients;
	}
	
	public void setIngredients(Set<Ingredient> ingredients){
		this.ingredients = ingredients;
	}
	
}
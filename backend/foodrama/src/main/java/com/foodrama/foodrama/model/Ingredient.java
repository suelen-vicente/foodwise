package com.foodrama.foodrama.model;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Ingredient implements Comparable<Ingredient>{
	@Id
	@GeneratedValue
	private Long id;

	private String name;

	private Double price;
	
	private Double packageQuantity;
	
	private String quantityUnit;
	
	@ManyToMany
    Set<Recipe> recipes;
	
	@Override
    public int compareTo(Ingredient ingredient) {
        return this.name.compareTo(ingredient.name);
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

}

package com.foodrama.foodrama.model.dto;

import java.util.Set;
import java.util.stream.Collectors;

import com.foodrama.foodrama.model.Recipe;

public record RecipeDto(Long id, String name, Double price, String steps, Long portion, Set<IngredientDto> ingredients) {

	public RecipeDto(Recipe recipe) {
		this(recipe.getId(), 
				recipe.getName(), 
				recipe.getPrice(), 
				recipe.getSteps(), 
				recipe.getPortion(), 
				recipe.getIngredients()
					.stream()
					.map(IngredientDto::new)
					.collect(Collectors.toSet()));
	}
	
	public Recipe toEntity() {
		Recipe recipe = new Recipe();
		recipe.setName(this.name());
		recipe.setPrice(this.price());
		recipe.setSteps(this.steps);
		recipe.setPortion(this.portion);
		recipe.setIngredients(this.ingredients()
				.stream()
				.map(ing -> ing.toEntity())
				.collect(Collectors.toSet()));
        
        return recipe;
	}
	
	//Na verdade, o ingredient q tem na receita nao eh exatamente IngredientDto, 
	// tem q ser um Dto diferente, pq tem q ter a quantidade que vai nessa receita
	// e pra calcular o preco, precisa fazer a regra de tres com o preco pela quantidade do pacote
	// e o preco pela quantidade que vai na receita, eh mais complexo q so somar o preco de cada ingrediente
	// Talvez isso aqui deva ser feito no front-end, pq ele precisa ser um calculo em tempo real na tela.
	private Double calculateRecipePrice(Set<IngredientDto> ingredients) {
        return ingredients.stream()
        		.mapToDouble(IngredientDto::price)
                .sum();
    }

}

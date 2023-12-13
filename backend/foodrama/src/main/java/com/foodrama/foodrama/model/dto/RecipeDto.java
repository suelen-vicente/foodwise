package com.foodrama.foodrama.model.dto;

import java.util.Set;

public record RecipeDto(Long id, String name, Double price, String steps, Long portion, Set<IngredientDto> ingredients) {

}

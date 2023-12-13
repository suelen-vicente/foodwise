package com.foodrama.foodrama.model.dto;

import com.foodrama.foodrama.model.QuantityUnit;

public record IngredientDto(Long id, String name, Double price, Double packageQuantity, QuantityUnit quantityUnit) {

}
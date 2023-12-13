package com.foodrama.foodrama.model.dto;

import com.foodrama.foodrama.model.QuantityUnit;

public record MyFridgeDto(Long userId, IngredientDto ingredient, Double availableQuantity, QuantityUnit quantityUnit) {

}
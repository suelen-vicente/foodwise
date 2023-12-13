package com.foodrama.foodrama.model.dto;

import com.foodrama.foodrama.model.QuantityUnit;

public record ShoppingListDto(Long userId, Long ingredientId, Double quantity, QuantityUnit quantityUnit) {

}
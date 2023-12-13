package com.foodrama.foodrama.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodrama.foodrama.model.ShoppingList;

public interface ShoppingListRepository extends JpaRepository<ShoppingList, Long>{

}

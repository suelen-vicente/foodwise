package com.foodrama.foodrama.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodrama.foodrama.repository.ShoppingListRepository;

@Service
public class ShoppingListService {

	@Autowired
	private ShoppingListRepository shoppingListRepository;
}

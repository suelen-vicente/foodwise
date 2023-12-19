package com.foodrama.foodrama.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.foodrama.foodrama.model.dto.ShoppingListDto;
import com.foodrama.foodrama.service.ShoppingListService;

@Controller
@RestController
@RequestMapping("/shopping-list")
public class ShoppingListController {
	@Autowired
	private ShoppingListService shoppingListService; 
	
	@GetMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public List<ShoppingListDto> getByUserId(@PathVariable Long id) {
		return shoppingListService.getByUserId(id);
	}
	
	@PostMapping(value = "/ingredient")
	@ResponseStatus(HttpStatus.CREATED)
	public ShoppingListDto addIngredient(@RequestBody ShoppingListDto ingredient) {
		return shoppingListService.addIngredient(ingredient);
	}
	
	@PutMapping(value = "/{userId}/ingredient/{ingredientId}")
	@ResponseStatus(HttpStatus.OK)
	public ShoppingListDto editIngredient(@PathVariable Long userId, @PathVariable Long ingredientId, @RequestBody ShoppingListDto ingredient) {
		return shoppingListService.editIngredient(userId, ingredientId, ingredient);
	}
	
	@DeleteMapping(value = "/ingredient/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteIngredient(@PathVariable Long ingredientId) {
		shoppingListService.deleteIngredient(ingredientId);
	}
	
	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteAll(@PathVariable Long userId) {
		shoppingListService.delete(userId);
	}
}

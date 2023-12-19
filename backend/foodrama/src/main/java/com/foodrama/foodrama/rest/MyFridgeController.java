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

import com.foodrama.foodrama.model.dto.MyFridgeDto;
import com.foodrama.foodrama.model.dto.ShoppingListDto;
import com.foodrama.foodrama.service.MyFridgeService;

@Controller
@RestController
@RequestMapping("/my-fridge")
public class MyFridgeController {
	
	@Autowired
	private MyFridgeService myFridgeService; 
	
	@GetMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public List<MyFridgeDto> getByUserId(@PathVariable Long id) {
		return myFridgeService.getByUserId(id);
	}
	
	@PostMapping(value = "/ingredient")
	@ResponseStatus(HttpStatus.CREATED)
	public MyFridgeDto addIngredient(@RequestBody MyFridgeDto ingredient) {
		return myFridgeService.addIngredient(ingredient);
	}
	
	@PutMapping(value = "/{userId}/ingredient/{ingredientId}")
	@ResponseStatus(HttpStatus.OK)
	public MyFridgeDto editIngredient(@PathVariable Long userId, @PathVariable Long ingredientId, @RequestBody MyFridgeDto ingredient) {
		return myFridgeService.editIngredient(userId, ingredientId, ingredient);
	}
	
	@DeleteMapping(value = "/ingredient/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteIngredient(@PathVariable Long ingredientId) {
		myFridgeService.deleteIngredient(ingredientId);
	}
	
	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteAll(@PathVariable Long userId) {
		myFridgeService.delete(userId);
	}
}

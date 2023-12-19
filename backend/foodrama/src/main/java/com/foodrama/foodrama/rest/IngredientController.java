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

import com.foodrama.foodrama.model.dto.IngredientDto;
import com.foodrama.foodrama.service.IngredientService;;

@Controller
@RestController
@RequestMapping("/ingredient")
public class IngredientController {
	
	@Autowired
	private IngredientService ingredientService; 
	
	@GetMapping()
	@ResponseStatus(HttpStatus.OK)
	public List<IngredientDto> getAllIngredients() {
		return ingredientService.getAllIngredients();
	}
	
	@GetMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public IngredientDto getIngredientById(@PathVariable Long id) {
		return ingredientService.getIngredientsById(id);
	}
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public IngredientDto addIngredient(@RequestBody IngredientDto ingredient) {
		return ingredientService.saveIngredient(ingredient);
	}
	
	@PutMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public IngredientDto editIngredient(@PathVariable Long id, @RequestBody IngredientDto ingredient) {
		return ingredientService.editIngredient(id, ingredient);
	}
	
	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteIngredient(@PathVariable Long id) {
		ingredientService.deleteIngredientById(id);
	}
}

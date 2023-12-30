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

import com.foodrama.foodrama.model.dto.RecipeDto;
import com.foodrama.foodrama.service.RecipeService;

@Controller
@RestController
@RequestMapping("/recipe")
public class RecipeController {
	@Autowired
	private RecipeService recipeService; 
	
	@GetMapping()
	@ResponseStatus(HttpStatus.OK)
	public List<RecipeDto> getAllRecipes() {
		return recipeService.getAll();
	}
	
	@GetMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public RecipeDto getRecipeById(@PathVariable Long id) {
		return recipeService.getById(id);
	}
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public RecipeDto addRecipe(@RequestBody RecipeDto Recipe) {
		return recipeService.save(Recipe);
	}
	
	@PutMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public RecipeDto editRecipe(@PathVariable Long id, @RequestBody RecipeDto Recipe) {
//		recipeService.deleteAllRecipeIngredients(id);
		return recipeService.edit(id, Recipe);
	}
	
	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteRecipe(@PathVariable Long id) {
		recipeService.deleteById(id);
	}
}

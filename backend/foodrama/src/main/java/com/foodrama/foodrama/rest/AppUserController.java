package com.foodrama.foodrama.rest;

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

import com.foodrama.foodrama.model.dto.AppUserDto;
import com.foodrama.foodrama.service.AppUserService;

@Controller
@RestController
@RequestMapping("/user")
public class AppUserController {
	
	@Autowired
	private AppUserService userService; 
	
	@GetMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public AppUserDto getUserById(@PathVariable Long id) {
		return userService.getById(id);
	}
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public AppUserDto addUser(@RequestBody AppUserDto user) {
		return userService.save(user);
	}
	
	@PutMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public AppUserDto editUser(@PathVariable Long id, @RequestBody AppUserDto user) {
		return userService.edit(id, user);
	}
	
	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteUser(@PathVariable Long id) {
		userService.delete(id);
	}
}

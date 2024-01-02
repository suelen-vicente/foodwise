package com.foodrama.foodrama.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.foodrama.foodrama.model.AppUser;
import com.foodrama.foodrama.model.dto.AppUserDto;
import com.foodrama.foodrama.repository.AppUserRepository;

import jakarta.transaction.Transactional;

@Service
public class AppUserService {
	
	@Autowired
	private AppUserRepository userRepository;
	
	/**
	 * Returns the AppUser with passed id.
	 *
	 * @param id id of the AppUser
	 * @return the AppUser with the matching id requested
	 */
	@Transactional
	public AppUserDto getById(Long id) {
		AppUserDto AppUser = userRepository.findById(id)
				.map(AppUserDto::new)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "AppUser not found with id: " + id));
		
		return AppUser;
	}
	
	/**
     * Save a new AppUser to the database.
     *
     * @param AppUserDto the DTO containing information about the AppUser
     * @return the saved AppUser as a DTO
     */
	@Transactional
    public AppUserDto save(AppUserDto userDto) {
    	try {
    		return new AppUserDto(userRepository.save(userDto.toEntity()));
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR , "Error saving AppUser: " + e.getMessage());
		}
    }
    
    /**
     * Edit a AppUser in the database.
     *
     * @param id id of the AppUser
     * @param AppUserDto the DTO containing information about the AppUser
     * @return the saved AppUser as a DTO
     */
	@Transactional
    public AppUserDto edit(Long id, AppUserDto userDto) {
		AppUser user = userRepository.findById(id)
        		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + id));
    	
		try {
			user.setName(userDto.name());
	        return new AppUserDto(userRepository.save(user));
    	} catch (Exception e) {
    		throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR , "Error editing AppUser: " + e.getMessage());
    	}
    }

    /**
     * Delete the AppUser with the specified ID from the database.
     *
     * @param id the ID of the AppUser to delete
     */
	@Transactional
    public void delete(Long id) {
		userRepository.findById(id)
        		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + id));
    	
		try {
    		userRepository.deleteById(id);
    	} catch (Exception e) {
    		throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR , "Error deleting AppUser: " + e.getMessage());
    	}
    }
}

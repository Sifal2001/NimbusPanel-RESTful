package com.NimbusPanel.NimbusPanelService.user;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {
	
	private final UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public List<User> getUsers() {
		return userService.getUsers();

	}
	
	@PostMapping
	public User createUser(@RequestBody User user) {
		return userService.saveUser(user);
	}
	
	@GetMapping("/emails")
	public List<String> getAllEmails(){
		return userService.getAllEmails();
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<User> login(@RequestBody AuthenticateRequest request){
		try {
			User user = userService.authenticate(request.getEmail(), request.getPassword());
			return ResponseEntity.ok(user);
		} catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}
	
    @PostMapping("/{id}/favourites")
    public ResponseEntity<FavouriteLocations> addFavourite(@PathVariable Long id, @RequestBody String locationName) {
    	FavouriteLocations saved = userService.addFavoriteLocation(id, locationName);
    	return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @DeleteMapping("/{id}/favourites/{favouriteId}")
    public ResponseEntity<Void> deleteFavourite(@PathVariable Long id, @PathVariable Long favouriteId){
    	userService.deleteFavouriteLocation(id, favouriteId);
    	return ResponseEntity.noContent().build();
    	
    }
}

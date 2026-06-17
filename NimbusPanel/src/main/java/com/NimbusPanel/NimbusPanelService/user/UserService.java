package com.NimbusPanel.NimbusPanelService.user;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	private final UserRepository userRepository;
	private final FavouriteLocationsRepository favouriteLocationsRepository;
	private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@Autowired
	public UserService(UserRepository userRepository, FavouriteLocationsRepository favouriteLocationsRepository) {
		this.userRepository = userRepository;
		this.favouriteLocationsRepository = favouriteLocationsRepository;
	}

	public List<User> getUsers() {
		return userRepository.findAll();
	}
	
	public User saveUser(User user) {
	    String hashed = passwordEncoder.encode(user.getPassword());
	    user.setPassword(hashed);
		return userRepository.save(user);
	}
	
	public List<String> getAllEmails(){
		return userRepository.findAll()
				.stream()
				.map(User::getEmail)
				.collect(Collectors.toList());	
	}
	
	public User authenticate(String email, String password) {
	    return userRepository.findByEmail(email)
	            .filter(user -> passwordEncoder.matches(password, user.getPassword()))
	            .orElseThrow(() -> new RuntimeException("Invalid email or password"));
	}
	
	public List<FavouriteLocations> getFavouriteLocations(Long userId){
		User user = userRepository.findById(userId)
					.orElseThrow(() -> new RuntimeException("User not found"));
        return user.getFavouriteLocations();
	}
	
    public FavouriteLocations addFavoriteLocation(Long userId, String locationName) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));

        FavouriteLocations fav = new FavouriteLocations();
        fav.setLocation(locationName);
        fav.setUser(user);
        
        return favouriteLocationsRepository.save(fav);
    }
    
    public void deleteFavouriteLocation(Long userId, Long favouriteId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));

        user.getFavouriteLocations().removeIf(fav -> fav.getId().equals(favouriteId));

        userRepository.save(user);
    }
}

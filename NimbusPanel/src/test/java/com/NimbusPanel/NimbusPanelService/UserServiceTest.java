package com.NimbusPanel.NimbusPanelService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.NimbusPanel.NimbusPanelService.user.UserService;
import com.NimbusPanel.NimbusPanelService.user.UserRepository;
import com.NimbusPanel.NimbusPanelService.user.FavouriteLocationsRepository;
import com.NimbusPanel.NimbusPanelService.user.User;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

	@Mock
	private UserRepository userRepository;

	@Mock
	private FavouriteLocationsRepository favouriteLocationsRepository;

	private UserService userService;
	private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@BeforeEach
	void setUp() {
		userService = new UserService(userRepository, favouriteLocationsRepository);
	}

	@Test
	void correctPassword_authenticatesSuccessfully() {

		String rawPassword = "password123";

		String hashedPassword = passwordEncoder.encode(rawPassword);

		User fakeUser = new User("Test", "User", "sifyak@gmail.com", hashedPassword);

		when(userRepository.findByEmail("sifyak@gmail.com")).thenReturn(Optional.of(fakeUser));

		User result = userService.authenticate("sifyak@gmail.com", rawPassword);

		assertNotNull(result);
		assertEquals("sifyak@gmail.com", result.getEmail());
	}

	@Test
	void wrongPassword_throwsException() {
		String rawPassword = "password123";
		String wrongRawPassword = "falsepass";

		String hashedPassword = passwordEncoder.encode(rawPassword);

		User fakeUser = new User("Test", "User", "sifyak@gmail.com", hashedPassword);

		when(userRepository.findByEmail("sifyak@gmail.com")).thenReturn(Optional.of(fakeUser));

		assertThrows(RuntimeException.class, () -> {
			userService.authenticate("sifyak@gmail.com", wrongRawPassword);
		});
	}
}
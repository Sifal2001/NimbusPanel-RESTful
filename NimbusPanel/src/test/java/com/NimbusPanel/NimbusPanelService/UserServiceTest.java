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

// This line switches Mockito on for the class. It makes the @Mock fields
// below into working fakes automatically before each test
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

	// @Mock = "create a fake version of this type." These are the two things
	// UserService's constructor demands. We fake BOTH so no real DB is touched
	@Mock
	private UserRepository userRepository;

	@Mock
	private FavouriteLocationsRepository favouriteLocationsRepository;

	// The REAL object we're testing. Not a mock — we want its actual logic.
	// We'll build it in setUp, feeding it the two mocks above
	private UserService userService;
	private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@BeforeEach
	void setUp() {
		// Build the REAL UserService, but hand it the two MOCK repositories.
		// This is constructor injection by hand — the same idea as Option B
		// and as Spring's @Autowired, just done manually in the test
		userService = new UserService(userRepository, favouriteLocationsRepository);
	}

	@Test
	void correctPassword_authenticatesSuccessfully() {
		// ---- ARRANGE ----
		// a) the raw password the "user typed"
		String rawPassword = "password123";

		// b) THE PUZZLE: make a BCrypt hash of that raw password.
		// UserService hashes with BCryptPasswordEncoder, so make one here
		// and call .encode(rawPassword) to get a hash string.
		// String hashedPassword = ... ;
		String hashedPassword = passwordEncoder.encode(rawPassword);

		// c) build a fake User whose stored password is that HASH (not the raw).
		// Use whichever User constructor is convenient — it needs at least
		// an email and the hashed password set.
		// User fakeUser = ... ;
		User fakeUser = new User("Test", "User", "sifyak@gmail.com", hashedPassword);

		// d) program the mock: when findByEmail is called with the user's email,
		// return Optional.of(fakeUser). Remember findByEmail returns Optional.
		// when(userRepository.findByEmail(...)).thenReturn(...);
		when(userRepository.findByEmail("sifyak@gmail.com")).thenReturn(Optional.of(fakeUser));
		// ---- ACT ----
		// call the real method with the email and the RAW password
		// User result = userService.authenticate(...);
		User result = userService.authenticate("sifyak@gmail.com", rawPassword);
		// ---- ASSERT ----
		// check result is not null, and its email matches what you expect
		assertNotNull(result);
		assertEquals("sifyak@gmail.com", result.getEmail());
	}

	@Test
	void wrongPassword_throwsException() {
		// ---- ARRANGE ----
		// Same as the passing test: raw password, hash it, build fakeUser with
		// the hash, program the mock to return it for the email.
		// (write these four lines yourself — they mirror the other test)
		String rawPassword = "password123";
		String wrongRawPassword = "falsepass";

		String hashedPassword = passwordEncoder.encode(rawPassword);

		User fakeUser = new User("Test", "User", "sifyak@gmail.com", hashedPassword);
		
		when(userRepository.findByEmail("sifyak@gmail.com")).thenReturn(Optional.of(fakeUser));

		// ---- ACT + ASSERT (combined for exceptions) ----
		// assertThrows checks that running the lambda throws the given exception.
		// Call authenticate with the SAME email but a DIFFERENT (wrong) raw password.
		assertThrows(RuntimeException.class, () -> {userService.authenticate("sifyak@gmail.com", wrongRawPassword);
			});
		
		
		// assertThrows(RuntimeException.class, () -> {
		// ... call authenticate with the wrong password ...
		// });
	}
}
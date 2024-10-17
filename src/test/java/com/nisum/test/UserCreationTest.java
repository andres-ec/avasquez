package com.nisum.test;

import com.nisum.dto.registration.UserRequestDTO;
import com.nisum.dto.registration.UserResponseDTO;
import com.nisum.dto.registration.PhoneDTO;
import com.nisum.entity.User;
import com.nisum.exception.email.EmailAlreadyExistsException;
import com.nisum.exception.email.InvalidEmailException;
import com.nisum.repository.UserRepository;
import com.nisum.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests the full flow of user registration, interacting with the actual Spring context,
 * including database operations. Tests cover successful registration, duplicate email handling,
 * invalid email format handling, and registration with phone data.
 *
 * The @SpringBootTest annotation loads the entire Spring application context for the tests.
 * The @ActiveProfiles("test") annotation ensures that the 'test' profile is used.
 *
 * @author avasquez
 */
@SpringBootTest
@ActiveProfiles("test")
class UserCreationTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    /**
     * Setup method to clear the database before each test. This ensures a clean state.
     */
    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    /**
     * Test case for successful user registration.
     * It verifies that a new user can be registered, a token is generated, and
     * the user is saved in the database.
     */
    @Test
    void testRegisterUser_Success() {
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setName("Test User");
        userRequestDTO.setEmail("test@example.com");
        userRequestDTO.setPassword("password123");
        userRequestDTO.setPhones(new ArrayList<>()); // No phones provided

        UserResponseDTO responseDTO = userService.registerUser(userRequestDTO);

        // Verify that the response is not null and contains valid data
        assertNotNull(responseDTO);
        assertNotNull(responseDTO.getId());
        assertTrue(responseDTO.isActive());
        assertNotNull(responseDTO.getToken());

        Optional<User> savedUser = userRepository.findByEmail("test@example.com");
        assertTrue(savedUser.isPresent()); // The user should exist in the DB
    }

    /**
     * Test case for handling registration with an already existing email.
     * It verifies that the system throws an EmailAlreadyExistsException when trying to register
     * a user with an email that already exists in the database.
     */
    @Test
    void testRegisterUser_EmailAlreadyExists() {
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setName("Test User");
        userRequestDTO.setEmail("existing@example.com");
        userRequestDTO.setPassword("password123");

        // Pre-save a user with the same email to simulate the existing email condition
        User existingUser = new User();
        existingUser.setEmail("existing@example.com");
        existingUser.setPassword("password123");
        userRepository.save(existingUser); // Save the existing user in the database

        // Verify that registering the same email throws an EmailAlreadyExistsException
        assertThrows(EmailAlreadyExistsException.class, () -> userService.registerUser(userRequestDTO));
    }

    /**
     * Test case for handling invalid email format during registration.
     * It verifies that the system throws an InvalidEmailException when trying to register
     * a user with an invalid email format.
     */
    @Test
    void testRegisterUser_InvalidEmail() {
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setName("Test User");
        userRequestDTO.setEmail("invalid-email"); // Invalid email format
        userRequestDTO.setPassword("password123");

        // Verify that trying to register an invalid email throws an InvalidEmailException
        assertThrows(InvalidEmailException.class, () -> userService.registerUser(userRequestDTO));
    }

    /**
     * Test case for user registration with phone numbers.
     * It verifies that a user can be registered with phone details and the phones are
     * saved in the database along with the user.
     */
    @Test
    void testRegisterUser_WithPhones() {
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setName("Test User");
        userRequestDTO.setEmail("test@example.com");
        userRequestDTO.setPassword("password123");

        PhoneDTO phoneDTO = new PhoneDTO();
        phoneDTO.setNumber("123456789");
        phoneDTO.setCitycode("1");
        phoneDTO.setCountrycode("57");

        userRequestDTO.setPhones(new ArrayList<>());
        userRequestDTO.getPhones().add(phoneDTO);

        UserResponseDTO responseDTO = userService.registerUser(userRequestDTO);

        assertNotNull(responseDTO);
        assertNotNull(responseDTO.getPhones());
        assertFalse(responseDTO.getPhones().isEmpty()); // There should be at least one phone
        assertEquals("123456789", responseDTO.getPhones().get(0).getNumber());

        Optional<User> savedUser = userRepository.findByEmail("test@example.com");
        assertTrue(savedUser.isPresent());
        assertNotNull(savedUser.get().getPhones());
    }
}

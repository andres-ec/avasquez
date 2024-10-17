package com.nisum.service;

import com.nisum.dto.registration.UserRequestDTO;
import com.nisum.dto.registration.UserResponseDTO;

/**
 * Service interface for user-related operations, including user registration
 * and email validation. Implementations of this interface should handle
 * the logic for managing user data and interactions with the underlying
 * data source.
 *
 * @author avasquez
 */
public interface UserService {

    /**
     * Registers a new user based on the provided user request details.
     *
     * @param userRequestDTO the DTO containing user registration details
     * @return UserResponseDTO containing the registered user's details
     */
    UserResponseDTO registerUser(UserRequestDTO userRequestDTO);

    /**
     * Validates the provided email against defined criteria.
     *
     * @param email the email address to validate
     */
    void validateEmail(String email);
}

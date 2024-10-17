package com.nisum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.nisum.dto.registration.UserRequestDTO;
import com.nisum.dto.registration.UserResponseDTO;
import com.nisum.service.impl.UserServiceImpl;

/**
 * REST controller for handling user-related requests.
 * <p>
 * This controller manages user registration operations and is mapped to the
 * "/users" URL. It interacts with the {@code UserServiceImpl} to process requests.
 * </p>
 *
 * @author avasquez
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    /**
     * Handles user registration requests.
     * <p>
     * This method accepts a {@code UserRequestDTO} and returns a
     * {@code UserResponseDTO} after processing the registration logic
     * in the {@code UserServiceImpl}.
     * </p>
     *
     * @param userRequestDTO the request payload containing user registration details
     * @return a {@code ResponseEntity} containing the registered user's response data
     */
    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO userResponseDTO = userServiceImpl.registerUser(userRequestDTO);
        return ResponseEntity.ok(userResponseDTO);
    }
}

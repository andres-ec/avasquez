package com.nisum.service.impl;

import com.nisum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.nisum.dto.registration.UserRequestDTO;
import com.nisum.dto.registration.UserResponseDTO;
import com.nisum.dto.registration.PhoneDTO;
import com.nisum.entity.User;
import com.nisum.entity.Phone;
import com.nisum.exception.email.EmailAlreadyExistsException;
import com.nisum.exception.email.InvalidEmailException;
import com.nisum.repository.UserRepository;
import com.nisum.utils.MessagesHelper;
import com.nisum.utils.JwtUtil;

/**
 * Implementation of the UserService interface, responsible for user
 * registration and management. This service handles the registration
 * process, including email validation, user creation, and response
 * generation.
 *
 * @author avasquez
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessagesHelper messagesHelper;

    @Autowired
    private JwtUtil jwtUtil;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Value("${regex.email}")
    private String emailRegex;

    /**
     * Registers a new user with the provided details.
     *
     * @param userRequestDTO the DTO containing user registration details
     * @return UserResponseDTO containing the registered user's details
     */
    public UserResponseDTO registerUser(UserRequestDTO userRequestDTO) {
        validateEmail(userRequestDTO.getEmail());

        // Check if email already exists
        if (userRepository.findByEmail(userRequestDTO.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException(messagesHelper.getMessage("email.already_registered"));
        }

        // Convert DTO to Entity
        User user = convertToUserEntity(userRequestDTO);

        // Set timestamps and other fields
        LocalDateTime dateTime = LocalDateTime.now();
        user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
        user.setCreated(dateTime);
        user.setModified(dateTime);
        user.setLastLogin(dateTime);
        user.setActive(true);
        user.setToken(jwtUtil.generateToken(userRequestDTO.getEmail()));

        // Save user in database
        User savedUser = userRepository.save(user);

        // Convert saved entity to response DTO
        return convertToUserResponseDTO(savedUser);
    }

    /**
     * Validates the email format against the defined regex.
     *
     * @param email the email address to validate
     */
    public void validateEmail(String email) {
        Pattern pattern = Pattern.compile(emailRegex);
        if (!pattern.matcher(email).matches()) {
            throw new InvalidEmailException(messagesHelper.getMessage("email.invalid"));
        }
    }

    /**
     * Converts UserRequestDTO to User entity.
     *
     * @param userRequestDTO the user request DTO to convert
     * @return the converted User entity
     */
    protected User convertToUserEntity(UserRequestDTO userRequestDTO) {
        User user = new User();
        user.setName(userRequestDTO.getName());
        user.setEmail(userRequestDTO.getEmail());

        // Convert phone DTOs to phone entities
        List<Phone> phones = userRequestDTO.getPhones()
                .stream()
                .map(this::convertToPhoneEntity)
                .collect(Collectors.toList());
        user.setPhones(phones);
        return user;
    }

    /**
     * Converts PhoneDTO to Phone entity.
     *
     * @param phoneDTO the phone DTO to convert
     * @return the converted Phone entity
     */
    protected Phone convertToPhoneEntity(PhoneDTO phoneDTO) {
        Phone phone = new Phone();
        phone.setNumber(phoneDTO.getNumber());
        phone.setCityCode(phoneDTO.getCitycode());
        phone.setCountryCode(phoneDTO.getCountrycode());
        return phone;
    }

    /**
     * Converts User entity to UserResponseDTO.
     *
     * @param user the User entity to convert
     * @return the converted UserResponseDTO
     */
    protected UserResponseDTO convertToUserResponseDTO(User user) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(user.getId());
        userResponseDTO.setName(user.getName());
        userResponseDTO.setEmail(user.getEmail());
        userResponseDTO.setEncryptedPassword(user.getPassword());
        userResponseDTO.setCreated(user.getCreated());
        userResponseDTO.setModified(user.getModified());
        userResponseDTO.setLastLogin(user.getLastLogin());
        userResponseDTO.setActive(user.isActive());

        // Map phones to DTO
        List<PhoneDTO> phones = user.getPhones()
                .stream()
                .map(this::convertToPhoneDTO)
                .collect(Collectors.toList());
        userResponseDTO.setPhones(phones);

        userResponseDTO.setToken(user.getToken());

        return userResponseDTO;
    }

    /**
     * Converts Phone entity to PhoneDTO.
     *
     * @param phone the Phone entity to convert
     * @return the converted PhoneDTO
     */
    protected PhoneDTO convertToPhoneDTO(Phone phone) {
        PhoneDTO phoneDTO = new PhoneDTO();
        phoneDTO.setNumber(phone.getNumber());
        phoneDTO.setCitycode(phone.getCityCode());
        phoneDTO.setCountrycode(phone.getCountryCode());
        return phoneDTO;
    }
}

package com.nisum.dto.registration;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Data Transfer Object for user response data.
 * <p>
 * This class represents the information returned after a user registers,
 * including user details and metadata.
 * </p>
 *
 * @author avasquez
 */
@Data
public class UserResponseDTO {
    private UUID id;
    private LocalDateTime created;
    private LocalDateTime modified;
    private LocalDateTime lastLogin;
    private String name;
    private String email;
    private String encryptedPassword;
    private String token;
    private boolean isActive;
    private List<PhoneDTO> phones;
}

package com.nisum.dto.registration;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.util.List;

/**
 * Data Transfer Object for user registration requests.
 * <p>
 * This class encapsulates the user data required for registration, including
 * validation annotations to ensure the integrity of the input data.
 * </p>
 *
 * @author avasquez
 */
@Data
public class UserRequestDTO {
    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    private List<PhoneDTO> phones;
}

package com.nisum.dto.registration;

import lombok.Data;

/**
 * Data Transfer Object representing a phone number.
 * <p>
 * This class holds the information related to a user's phone number,
 * including the number, city code, and country code.
 * </p>
 *
 * @author avasquez
 */
@Data
public class PhoneDTO {
    private String number;
    private String citycode;
    private String countrycode;
}

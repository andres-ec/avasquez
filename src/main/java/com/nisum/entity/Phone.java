package com.nisum.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity representing a phone record in the database.
 * <p>
 * This class maps to the "phones" table and holds the phone details, including
 * an auto-generated identifier.
 * </p>
 *
 * @author avasquez
 */
@Entity
@NoArgsConstructor
@Data
@Table(name = "phones")
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String number;
    private String cityCode;
    private String countryCode;
}

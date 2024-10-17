package com.nisum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

import com.nisum.entity.User;

/**
 * Repository interface for managing {@code User} entities.
 * <p>
 * This interface extends {@code JpaRepository} to provide CRUD operations
 * and custom query methods for the {@code User} entity identified by a
 * {@code UUID}.
 * </p>
 *
 * @author avasquez
 */
public interface UserRepository extends JpaRepository<User, UUID> {

    /**
     * Retrieves a user by their email address.
     *
     * @param email the email address of the user
     * @return an {@code Optional} containing the user if found, or empty if not
     */
    Optional<User> findByEmail(String email);
}

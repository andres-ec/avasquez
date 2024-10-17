package com.nisum.exception.email;

/**
 * Custom exception thrown when a user attempts to register with an email
 * that already exists in the system.
 * <p>
 * This exception extends {@code RuntimeException} and provides a message
 * to indicate the reason for the error.
 * </p>
 *
 * @author avasquez
 */
public class EmailAlreadyExistsException extends RuntimeException {

    /**
     * Constructs a new EmailAlreadyExistsException with the specified detail message.
     *
     * @param message the detail message explaining the reason for the exception
     */
    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}

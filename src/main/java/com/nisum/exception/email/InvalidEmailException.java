package com.nisum.exception.email;

/**
 * Custom exception thrown when an invalid email format is encountered.
 * <p>
 * This exception extends {@code RuntimeException} and provides a message
 * to indicate the reason for the error.
 * </p>
 *
 * @author avasquez
 */
public class InvalidEmailException extends RuntimeException {

    /**
     * Constructs a new InvalidEmailException with the specified detail message.
     *
     * @param message the detail message explaining the reason for the exception
     */
    public InvalidEmailException(String message) {
        super(message);
    }
}

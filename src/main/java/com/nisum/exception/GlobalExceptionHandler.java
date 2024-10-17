package com.nisum.exception;

import com.nisum.exception.email.EmailAlreadyExistsException;
import com.nisum.exception.email.InvalidEmailException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Global exception handler for the application.
 * <p>
 * This class handles exceptions thrown by the application and returns appropriate
 * HTTP responses, including specific handlers for invalid email and email already
 * existing exceptions.
 * </p>
 *
 * @author avasquez
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles InvalidEmailException and returns a 400 Bad Request response.
     *
     * @param ex the exception thrown
     * @return a ResponseEntity containing error details and HTTP status
     */
    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<Map<String, String>> handleInvalidEmail(InvalidEmailException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles EmailAlreadyExistsException and returns a 409 Conflict response.
     *
     * @param ex the exception thrown
     * @return a ResponseEntity containing error details and HTTP status
     */
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleEmailAlreadyExists(EmailAlreadyExistsException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    /**
     * Handles generic exceptions and returns a 500 Internal Server Error response.
     *
     * @param ex the exception thrown
     * @return a ResponseEntity containing error details and HTTP status
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGeneralException(Exception ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "An unexpected error occurred: " + ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

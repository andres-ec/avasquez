package com.nisum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class for the User Management API.
 * This class serves as the entry point for the Spring Boot application,
 * initializing the application context and starting the embedded web server.
 *
 * @author avasquez
 */
@SpringBootApplication
public class ApiUserManagement {
    public static void main(String[] args) {
        SpringApplication.run(ApiUserManagement.class, args);
    }
}

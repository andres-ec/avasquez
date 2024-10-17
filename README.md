# User Creation API

## Overview

This API is a RESTful service designed to facilitate the seamless creation of users within an application. Built with security and scalability in mind, this API allows developers to programmatically register new users, ensuring that essential user data such as email and password are securely handled.

Key features of this API include:

- **User Registration**: Users can be created with various attributes, allowing for flexibility in user management.
- **Input Validation**: Ensures that user data adheres to specified formats and constraints, reducing the risk of invalid data entries.
- **Error Handling**: Comprehensive error responses guide developers in troubleshooting issues related to user creation.
- **Security Features**: Passwords are securely hashed before storage, adhering to best practices for user data protection.

## Getting Started

### Prerequisites
- Java 21.

### Installation
Step-by-step instructions on how to set up the API locally:
1. Clone the repository:
   ```bash
   git clone <repository-url>
2. Navigate to the project directory.
3. Build Gradle dependencies:
* For Unix/Linux/Mac:
  ```bash
  ./gradlew build

* For Windows:
  ```bash
  gradlew.bat build

4. Run the service:

You can use your preferred development environment to run the application. Examples of supported tools are:

* Spring Tool Suite (STS)
* IntelliJ IDEA
* Visual Studio Code (with the proper extension)

There’s no need to run a script for the database as the tables are created automatically.
Simply import the project into your chosen IDE and run the main application class.

5. Access the Swagger UI:
   To explore the API endpoints and test them interactively, visit:

    ```bash
    http://localhost:8080/swagger-ui.html

6. Access the H2 Console:
   Once the application is running, you can access the H2 database console at:

   ```bash
   http://localhost:8080/h2-console
Use the following credentials to log in:

JDBC URL: jdbc:h2:mem:db

User Name: sa

Password: *(leave blank)*

7. You can run unit tests by running this class:
    ```bash
    com.nisum.test.UserCreationTest

## Author

This API was developed by **Andrés Vásquez** for **Nisum**. 

For questions or any feedback, please feel free to reach out at **andresvasquez.ec@gmail.com**.

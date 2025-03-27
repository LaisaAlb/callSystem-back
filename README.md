# CallSystem API

## Description
The **CallSystem API** is a Spring Boot 3.4.3 application for managing service calls. It provides CRUD operations for clients, technicians, and service calls, along with authentication and authorization using JWT tokens.

## Technologies Used
- **Java 17**
- **Spring Boot 3.4.3**
- **Spring Data JPA**
- **Spring Security**
- **JWT (JSON Web Token)**
- **H2 Database (for testing)**
- **MySQL (for production)**

## Features
- CRUD operations for **Clients**, **Technicians**, and **Service Calls**.
- **Data Validation** to ensure information integrity.
- **Business Rules:**
    - A **service call cannot be deleted**.
    - When creating a service call, it must be linked to both a **client** and a **technician**.
- **Authentication and Authorization** using JWT.
- **Security** implemented with Spring Security.

## Installation and Configuration
1. **Clone the repository:**
   ```bash
   git clone https://github.com/your-repository/callSystem.git
   ```
2. **Navigate to the project directory:**
   ```bash
   cd callSystem
   ```
3. **Configure the MySQL database:**
    - Create a database named `call_system`.
    - Update the credentials in the `application.properties` file.

4. **Install dependencies and run the application:**
   ```bash
   mvn spring-boot:run
   ```

## Main Endpoints

### Authentication
- `POST /auth/login` - Generates a JWT token for authentication.

### Clients
- `GET /clients` - Lists all clients.
- `POST /clients` - Creates a new client.
- `GET /clients/{id}` - Retrieves a client by ID.
- `PUT /clients/{id}` - Updates a client's details.
- `DELETE /clients/{id}` - Deletes a client.

### Technicians
- `GET /technicians` - Lists all technicians.
- `POST /technicians` - Creates a new technician.
- `GET /technicians/{id}` - Retrieves a technician by ID.
- `PUT /technicians/{id}` - Updates a technician's details.
- `DELETE /technicians/{id}` - Deletes a technician.

### Service Calls
- `GET /calls` - Lists all service calls.
- `POST /calls` - Creates a new service call (must be associated with a client and a technician).
- `GET /calls/{id}` - Retrieves a service call by ID.
- `PUT /calls/{id}` - Updates a service call's details.

⚠ **A service call cannot be deleted.**

## Security
- The API uses **JWT** for authentication and authorization.
- Some routes require authenticated users.
- Spring Security protects the API endpoints.

## Dependencies
The dependencies used are listed in the `pom.xml` file and include:
- `spring-boot-starter-web`
- `spring-boot-starter-data-jpa`
- `spring-boot-starter-security`
- `spring-boot-starter-validation`
- `jjwt` (for JWT token generation)
- `H2` and `MySQL` for database management

## Contribution
If you would like to contribute to the project, submit a **Pull Request** or open an **Issue** with your suggestions or problem reports.

## Author
Developed by **Laisa**


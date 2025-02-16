### Product Management System

### Table of Contents
1. [Introduction](#introduction)
2. [Technologies Used](#technologies-used)
3. [Prerequisites](#prerequisites)
4. [Project Setup](#project-setup)
5. [Endpoints](#endpoints)
6. [Building and Running the Application](#building-and-running-the-application)
7. [Development Guidelines](#development-guidelines)
8. [Contact](#contact)

---

### Introduction

This project is built using **Jakarta EE**, **Spring Data MongoDB**, **Spring MVC**, **Lombok**, and runs on **Java 17**. This application is designed to Product Management System and provides APIs to handle core functionality.

---

### Technologies Used

- **Jakarta EE**: For framework and enterprise technologies.
- **Spring MVC**: For implementing the web layer.
- **Spring Data MongoDB**: To interact seamlessly with MongoDB as the database.
- **Java 17**: As the core programming language, utilizing the latest features.
- **Lombok**: To reduce boilerplate code for model classes.
- **MongoDB**: As the NoSQL database.
- **Maven**: For build automation and dependency management.

---

### Prerequisites

Before running the project, ensure you have the following installed and configured:

1. **Java Development Kit (JDK)** version 17
   - [Download JDK](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)
2. **MongoDB** (NoSQL database)
   - [Install MongoDB](https://www.mongodb.com/try/download/community)
3. An IDE such as **IntelliJ IDEA Community Edition**.
4. **Git** for source control.

---

### Project Setup

Follow these steps to set up the project environment:

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd <project-directory>
   ```

2. Make sure MongoDB is running locally or provide the cloud `connection-string` in the configuration.

3. Configure `application.properties` in the `src/main/resources` directory:
   ```properties
   # MongoDB Configuration
   spring.data.mongodb.uri=mongodb://<username>:<password>@<host>:<port>/<database>

   # Server and other configurations
   server.port=8080
   ```
   
4. Run the project:
   ```bash
   gradle bootRun
   ```

---

### Endpoints

Below is the list of key REST API endpoints exposed by the application:

| HTTP Method | Endpoint            | Description                           |
|-------------|---------------------|---------------------------------------|
| POST        | `/api/authenticate` | Provide JWT for accessing product API |
| POST        | `/api/register`     | Create a new user in system           |
| GET         | `/api/product`      | Fetch all product                     |
| GET         | `/api/product/{id}` | Fetch a specific product by ID        |
| POST        | `/api/product`      | Create a new product                  |
| PUT         | `/api/product/{id}` | Update a product by ID                |
| DELETE      | `/api/product/{id}` | Delete a product by ID                |

---

### Building and Running the Application

1. Build the Gradle project:
   ```bash
    gradle bootRun
   ```
   
2. Access the application:
   - API Base URL: `http://localhost:8080/api/`

   - Swagger (if enabled): `http://localhost:8080/swagger-ui/index.html`

---

### Development Guidelines

- Follow a component-wise folder structure for better maintainability.
- Use Lombok annotations (`@Getter`, `@Setter`, `@Builder`, etc.) to reduce boilerplate code in model classes.
- Always add logs for debugging purposes.

---

### Contact

For any queries or support, contact us at paritajapadiya@gmail.com.
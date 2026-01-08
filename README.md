# Task Management REST API

Backend REST API for managing users and tasks, built using **Spring Boot 3**, **JPA/Hibernate**, **PostgreSQL**, and **Flyway**.

---

## Tech Stack
- Java 17
- Spring Boot 3.2.x
- Spring Data JPA
- Spring Security (JWT)
- PostgreSQL
- Flyway
- Lombok
- Maven

---

## Features
- User management
- Task management with status & priority
- Pagination & filtering
- Validation & global error handling
- Database migrations
- JWT-based authentication (bonus)

---

## Setup Instructions

### 1. Database
Create a PostgreSQL database:
```
CREATE DATABASE taskmanager;
```
Update credentials in application.yml.

### 2. Run Application
```
mvn clean install
mvn spring-boot:run
```

### 3. Public Endpoints
| Method | Endpoint        |
| ------ | --------------- |
| POST   | /api/auth/login |
| POST   | /api/users      |

### 4. Protected Enpoints
| Method | Endpoint               |
| ------ | ---------------------- |
| GET    | /api/users             |
| GET    | /api/users/{id}        |
| POST   | /api/tasks             |
| GET    | /api/tasks             |
| PUT    | /api/tasks/{id}        |
| PATCH  | /api/tasks/{id}/status |
| DELETE | /api/tasks/{id}        |

### 5. Error Handling
| Scenario           | Status |
| ------------------ | ------ |
| Validation error   | 400    |
| Resource not found | 404    |
| Duplicate email    | 409    |
| Unauthorized       | 401    |

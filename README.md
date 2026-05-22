Car Rental System (Backend) — Usenkhan Yersultan (IT2-2310)
Spring Boot backend project for the final exam requirements:

REST API (CRUD)
PostgreSQL + Spring Data JPA (Hibernate)
DTOs + mapping
Validation + centralized error handling
Pagination + sorting + search + filtering (Cars endpoint)
Spring Security (registration, authentication, authorization) + JWT
Protected endpoints (role-based access via @PreAuthorize)
File upload/download
Async processing (@Async, CompletableFuture)
Swagger UI (OpenAPI)
Request + action + error logging
Docker (multi-stage build, docker-compose, health checks, volumes)
Exam note: the exam requires English answers. This README is written in English to match that requirement.

Tech stack
Java 17, Spring Boot 3.x
PostgreSQL
Spring Web, Spring Data JPA, Spring Security, Spring Validation, Actuator
JJWT (JWT tokens)
SpringDoc OpenAPI (Swagger UI)
1) Quick run (Docker)
Requirements: Docker Desktop.

cd usenkhan-yersultan-car-rental-system
docker compose up --build
Then open:

Swagger UI: http://localhost:8080/swagger-ui.html
Actuator health: http://localhost:8080/actuator/health
Docker compose starts:

db: PostgreSQL 16 (with healthcheck + persistent volume)
app: Spring Boot container (with healthcheck + storage volume)
2) Run locally (Mac)
Requirements: Java 17+, Maven 3.9+ (or IntelliJ IDEA with Maven).

Install Maven (if you don't have it):

brew install maven
Start PostgreSQL (Docker recommended):

cd usenkhan-yersultan-car-rental-system
docker compose up -d db
Run the app:

mvn spring-boot:run
3) Configuration (env variables)
The app reads configuration from environment variables (with defaults):

DB_URL (default: jdbc:postgresql://localhost:5432/carrental)
DB_USERNAME (default: carrental)
DB_PASSWORD (default: carrental)
JWT_SECRET (default: change-me-change-me-change-me-change-me-change-me-123)
STORAGE_DIR (default: ./storage)
SPRING_PROFILES_ACTIVE (Docker uses docker)
3) Default users
On first start the app creates an admin user:

email: admin@carrental.local
password: admin12345
Registering via /api/auth/register creates a user with role ROLE_CUSTOMER.

4) Auth flow (JWT) + protected endpoints
Register:
POST /api/auth/register
Login:
POST /api/auth/login → returns accessToken
Use token:
Authorization: Bearer <token>
Public endpoints (no token required):

/api/auth/**
/swagger-ui.html, /swagger-ui/**, /v3/api-docs/**
/actuator/health
All other endpoints require JWT authentication.

Example (admin login)
curl -sS -X POST http://localhost:8080/api/auth/login \
  -H 'Content-Type: application/json' \
  -d '{"email":"admin@carrental.local","password":"admin12345"}'
5) One endpoint with pagination/sorting/search/filter
GET /api/cars?q=&brand=&status=&branchId=&minRate=&maxRate=&page=0&size=10&sort=dailyRate,asc

Notes:

Pagination: page, size
Sorting: sort=field,asc|desc (can be repeated)
Search: q (brand/model/plateNumber)
Filters: brand, status, branchId, minRate, maxRate
6) File upload/download
Upload: POST /api/files (multipart file)
Download: GET /api/files/{id}/download
7) Async processes
When a rental is created, the app triggers multiple async tasks (@Async + CompletableFuture), visible in logs:

audit log
invoice generation
customer notification
8) Entities (minimum 5–6)
Implemented JPA entities:

Car, Branch, Customer, Rental, Payment, StoredFile, AppUser
9) Error handling format
All errors are handled by a global @ControllerAdvice. Validation errors return a structured response with validationErrors (field → message).

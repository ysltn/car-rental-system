# Car Rental System (Backend) — Usenkhan Yersultan (IT2-2310)
Spring Boot backend project for the final exam requirements (REST, PostgreSQL, DTO, validation, errors, pagination/sorting/search/filter, JWT security, async, file upload/download, Swagger, logging, Docker).

## 1) Quick run (Docker)
Requirements: Docker Desktop.

```bash
cd usenkhan-yersultan-car-rental-system
docker compose up --build
```

Then open:
- Swagger UI: `http://localhost:8080/swagger-ui.html`
- Actuator health: `http://localhost:8080/actuator/health`

## 2) Run locally (Mac)
Requirements: Java 17+, Maven 3.9+ (or IntelliJ IDEA with Maven).

Install Maven (if you don't have it):
```bash
brew install maven
```

Start PostgreSQL (Docker recommended):
```bash
cd usenkhan-yersultan-car-rental-system
docker compose up -d db
```

Run the app:
```bash
mvn spring-boot:run
```

## 3) Default users
On first start the app creates an admin user:
- email: `admin@carrental.local`
- password: `admin12345`

## 4) Auth flow (JWT)
1) Register:
`POST /api/auth/register`
2) Login:
`POST /api/auth/login` → returns `accessToken`
3) Use token:
`Authorization: Bearer <token>`

### Example (admin login)
```bash
curl -sS -X POST http://localhost:8080/api/auth/login \
  -H 'Content-Type: application/json' \
  -d '{"email":"admin@carrental.local","password":"admin12345"}'
```

## 5) One endpoint with pagination/sorting/search/filter
`GET /api/cars?q=&brand=&status=&branchId=&minRate=&maxRate=&page=0&size=10&sort=dailyRate,asc`

## 6) File upload/download
- Upload: `POST /api/files` (multipart `file`)
- Download: `GET /api/files/{id}/download`

# 🏥 Patient Management System

A microservices-based system to manage patient information, billing, authentication, analytics, and more. Built using Java Spring Boot, Docker, Kafka, gRPC, and Spring Cloud Gateway.

---

## 📦 Services

This monorepo contains the following core microservices:

### 1. `patient-service`
- Handles patient data CRUD operations.
- Communicates with `billing-service` via gRPC.
- Publishes patient-related events to Kafka.

### 2. `auth-service`
- Manages user authentication.
- Issues and validates JWT tokens.
- Uses Spring Security with a basic in-memory H2/PostgreSQL DB.

### 3. `billing-service`
- Exposes billing operations via gRPC.
- Listens to patient actions/events.

### 4. `api-gateway`
- Routes incoming traffic to internal services using Spring Cloud Gateway.
- Validates JWT using a custom filter.
- Supports different environments (dev/prod).

### 5. `analytics-service`
- Consumes Kafka events for tracking patient-related activity.
- Designed for future data insights and reporting.

### 6. `infrastructure`
- AWS CDK-based stack (simulated using LocalStack).
- Docker-based local development setup with `docker-compose`.

### 7. `integration-tests`
- Contains integration tests across services using JUnit.

---

## 🐳 Local Development

### 🧰 Prerequisites

- Docker & Docker Compose
- Java 17+
- Maven
- LocalStack (via CDK or script)

### 🔧 Run with Docker Compose

```bash
docker-compose up --build

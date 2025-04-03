# Document Service

Quarkus based microservice for managing documents with REST and gRPC APIs.

## Features

- Document creation and retrieval
- Multi-tenant support
- JWT authentication
- REST and gRPC APIs

## Getting Started

### Prerequisites

- Java 17
- Maven 3.8+
- Docker

### Running the Application

1. Start Keycloak:
```bash
docker run -p 8080:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:latest start-dev
```

- Setup test-relam, users (admin-user,viewer-user) along with roles (admin, viewer) and JWT claim attribute (tenant_id). 

2. Start the application:
```bash
./mvnw quarkus:dev
```

The application will start at http://localhost:8080

### Running Tests

```bash
./mvnw test
```

## API Endpoints

### REST API

- `GET /documents` - Get all documents
- `GET /documents/{id}` - Get a document by ID
- `POST /documents` - Create a new document

### gRPC API

- `GetDocument` - Get a document by ID

## Configuration

Configuration is in `application.properties`:

- Database connection
- JWT settings
- gRPC server settings

## How to Test

### REST API Testing

1. Get a JWT token:
```bash
curl -X POST http://localhost:8080/auth/realms/test-realm/protocol/openid-connect/token \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"admin"}'
```

2. Create a document:
```bash
curl -X POST http://localhost:9090/documents \
  -H "Authorization: Bearer YOUR_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{"title":"Test Document","content":"Test Content"}'
```

3. Get a document:
```bash
curl -X GET http://localhost:9090/documents/DOCUMENT_ID \
  -H "Authorization: Bearer YOUR_TOKEN"
```

### gRPC Testing

1. Install grpcurl:
```bash
brew install grpcurl  # macOS
# or
sudo apt-get install grpcurl  # Linux
```

2. Test document retrieval:
```bash
grpcurl -plaintext -d '{"documentId": "DOCUMENT_ID"}' \
  -H "Authorization: Bearer YOUR_TOKEN" \
  localhost:9000 document.DocumentProcessor/GetDocument
```

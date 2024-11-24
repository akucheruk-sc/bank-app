# Simple Bank Application

## Docker startup environment:
1. Run docker using command: 
```console
docker compose up
```

## Local install:
1. Startup PostgreSQL server 17.X
2. Create DB with name: bank_app_db
3. Create or use exist public schema
4. Create user with name: postgres and password: 12344321

## DB creation:
1. Run command for creating tables and inserting test data:
```console
mvnw flyway:migrate
```

## Open API
Swagger UI url: http://localhost:8080/swagger-ui/index.html


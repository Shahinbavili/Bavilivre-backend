# Bavilivre Backend

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3-green)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-blue)
![Tests](https://img.shields.io/badge/tests-JUnit5%20%2B%20Mockito-success)

Frontend Angular développé séparément.

Backend de **Bavilivre**, une plateforme web de partage de livres entre particuliers.

Ce projet est développé avec **Java 21** et **Spring Boot** dans une approche inspirée du **DDD (Domain-Driven Design)** et de l’**architecture hexagonale**.

L’objectif principal du projet est de construire une application backend moderne, maintenable et évolutive, tout en renforçant les compétences backend Java/Spring Boot, persistence, testing et architecture logicielle.

---

# Technologies

- Java 21
- Spring Boot 3
- Spring Data JPA
- PostgreSQL
- H2 Database
- Maven
- JUnit 5
- Mockito
- AssertJ

---

# Architecture

Le projet suit une architecture inspirée du DDD et de l’architecture hexagonale.

## Structure principale

### `domain.model`

Contient la logique métier pure :

- entités métier
- comportements métier
- règles métier
- objets de valeur

Exemples :

- `Book`
- `Borrowing`
- `User`
- `BookId`
- `BorrowingId`
- `UserId`

---

### `application`

Contient les cas d’usage applicatifs :

- use cases
- DTOs
- mappers
- repository ports

Exemples :

- `BorrowBook`
- `ReturnBook`
- `BorrowingRepository`

---

### `infrastructure`

Contient les détails techniques :

- adapters
- persistence JPA
- Spring Data repositories
- controllers REST
- configuration Spring

Exemples :

- `BorrowingJpaAdapter`
- `BorrowingSpringDataRepository`
- `BorrowingJpaMapper`

---

# Persistence

Le projet utilise :

- PostgreSQL pour l’environnement principal
- H2 pour les tests et le développement léger

La couche persistence est isolée grâce à :

- repository ports
- JPA adapters
- Spring Data repositories
- entity/domain mappers

---

# Testing

Le projet contient plusieurs niveaux de tests.

## Unit tests

Tests des comportements métier et des use cases avec :

- JUnit 5
- Mockito
- AssertJ

Exemples :

- `BorrowingTest`
- `BorrowBookTest`
- `ReturnBookTest`

---

## Integration tests

Tests de persistence avec vraie base H2 via :

- `@DataJpaTest`

Exemple :

- `BorrowingJpaAdapterTest`

Ces tests valident :

- la persistence réelle
- les mappings JPA
- les repositories Spring Data
- les adapters

---

# Current Features

## Borrowing Workflow

- Borrow a book
- Return a borrowed book
- Persist borrowings
- Retrieve borrowed books
- Retrieve lent books
- Store borrowing history

---

## Persistence Layer

- PostgreSQL configuration
- Spring profiles (`dev` / `prod`)
- Spring Data JPA repositories
- JPA adapters
- Entity/domain mapping

---

## Architecture & Quality

- DDD-inspired structure
- Hexagonal architecture principles
- Separation between domain and infrastructure
- Unit tests
- Integration tests
- Clean commit history
- GitHub roadmap with issues and milestones

---

# Run the Project

## Development profile

Uses lightweight H2 database.

```bash
./mvnw spring-boot:run --spring.profiles.active=dev

# Next Steps

- REST API endpoints
- Authentication & JWT
- Book catalog management
- AWS deployment
- Angular frontend integration

# Bavilivre Backend

![Java](https://img.shields.io/badge/Java%2021-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)

![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=hibernate&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-4169E1?style=for-the-badge&logo=postgresql&logoColor=white)

![JUnit5](https://img.shields.io/badge/JUnit5-25A162?style=for-the-badge&logo=junit5&logoColor=white)
![Mockito](https://img.shields.io/badge/Mockito-78A641?style=for-the-badge)
![AssertJ](https://img.shields.io/badge/AssertJ-EFD000?style=for-the-badge)

![DDD](https://img.shields.io/badge/DDD-blueviolet?style=for-the-badge)
![Hexagonal](https://img.shields.io/badge/Hexagonal_Architecture-6A5ACD?style=for-the-badge)

Backend de **Bavilivre**, une plateforme web de partage de livres entre particuliers.

## Frontend

Frontend Angular développé séparément.

- Frontend repository:  

  https://github.com/Shahinbavili/Bavilivre-frontend

Ce projet est développé avec **Java 21** et **Spring Boot 3** dans une approche inspirée du **DDD (Domain-Driven Design)** et de l’**architecture hexagonale**.

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
```

## Production profile

Uses PostgreSQL.

```bash
./mvnw spring-boot:run --spring.profiles.active=prod
```

---

# Run Tests

```bash
./mvnw test
```

---

# Database

## PostgreSQL

Default local configuration:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/bavilivre
```

---

# Project Vision

Bavilivre est un projet personnel conçu pour :

* renforcer les compétences backend Java/Spring Boot
* pratiquer le DDD et l’architecture hexagonale
* construire une application full-stack réaliste
* améliorer la qualité de code et les pratiques de testing
* préparer un futur déploiement cloud AWS

Le projet est organisé avec :

* GitHub Issues
* Milestones
* Kanban board
* Sprint planning

---

# Next Steps

- REST API endpoints
- Authentication & JWT
- Book catalog management
- AWS deployment
- Angular frontend integration

---

# Author

Shahin Bavili

Full Stack Developer — Java / Spring Boot / Angular

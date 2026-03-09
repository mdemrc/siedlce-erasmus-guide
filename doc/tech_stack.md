# Technology Stack

## Overview

This document describes the technology choices for the Siedlce Erasmus Guide mobile application. The stack is split into two main layers: a native Android frontend built with Kotlin and a backend API built with Java and Spring Boot.

---

## Frontend (Mobile Application)

| Component | Technology | Version |
|---|---|---|
| Language | Kotlin | 1.9+ |
| Platform | Android (Native) | Min SDK 26 (Android 8.0) |
| Build System | Gradle (Kotlin DSL) | 8.x |
| UI Framework | Jetpack Compose | 1.5+ |
| Navigation | Jetpack Navigation Compose | 2.7+ |
| Networking | Retrofit + OkHttp | 2.9+ / 4.12+ |
| JSON Parsing | Kotlinx Serialization | 1.6+ |
| Image Loading | Coil | 2.5+ |
| Maps | Google Maps SDK for Android | 18.x |
| Dependency Injection | Hilt (Dagger) | 2.48+ |
| Local Storage | Room Database | 2.6+ |
| Async Operations | Kotlin Coroutines + Flow | 1.7+ |
| Architecture | MVVM (Model-View-ViewModel) | -- |

### Key Decisions -- Frontend

- **Jetpack Compose** over XML layouts for modern, declarative UI development
- **MVVM architecture** for clean separation of concerns and testability
- **Retrofit** for type-safe HTTP communication with the backend
- **Room** for offline caching of city guide content
- **Hilt** for compile-time dependency injection with Android lifecycle awareness
- **Coil** over Glide/Picasso for Kotlin-first image loading with Compose support

---

## Backend (API Server)

| Component | Technology | Version |
|---|---|---|
| Language | Java | 17 (LTS) |
| Framework | Spring Boot | 3.2+ |
| Build System | Maven | 3.9+ |
| API Style | RESTful (JSON) | -- |
| Database | PostgreSQL | 16+ |
| ORM | Spring Data JPA (Hibernate) | 3.2+ |
| API Documentation | SpringDoc OpenAPI (Swagger) | 2.3+ |
| Authentication | Spring Security + JWT | 3.2+ |
| Validation | Jakarta Bean Validation | 3.0+ |
| Migration | Flyway | 10+ |
| Testing | JUnit 5 + Mockito | 5.10+ / 5.8+ |

### Key Decisions -- Backend

- **Java 17** as the LTS version with modern language features (records, sealed classes, pattern matching)
- **Spring Boot 3.2+** for production-ready REST API with minimal configuration
- **PostgreSQL** for reliable relational data storage with spatial query support (PostGIS for map data)
- **Flyway** for version-controlled database migrations
- **JWT authentication** for stateless API security suitable for mobile clients
- **SpringDoc OpenAPI** for automatic API documentation and testing interface

---

## API Communication

```
[Android App (Kotlin)] <---> [REST API (JSON over HTTPS)] <---> [Spring Boot Backend (Java)]
                                                                        |
                                                                  [PostgreSQL]
```

- Communication between mobile app and backend uses REST endpoints over HTTPS
- JSON is the data exchange format
- JWT tokens handle authentication for protected endpoints

---

## Development Tools

| Tool | Purpose |
|---|---|
| Android Studio | Android/Kotlin IDE |
| IntelliJ IDEA | Backend Java IDE |
| Git + GitHub | Version control and collaboration |
| Postman | API testing |
| Docker | Local PostgreSQL and backend containerization |
| pgAdmin | Database management |

---

## Project Structure

```
siedlce-erasmus-guide/
├── doc/                    # Project documentation
│   ├── prd.md              # Product Requirements Document
│   └── tech_stack.md       # This file
├── android/                # Kotlin Android application
│   ├── app/
│   │   ├── src/
│   │   │   ├── main/
│   │   │   │   ├── java/   # Kotlin source files
│   │   │   │   └── res/    # Resources (layouts, strings, drawables)
│   │   │   └── test/       # Unit tests
│   │   └── build.gradle.kts
│   ├── build.gradle.kts
│   └── settings.gradle.kts
├── backend/                # Java Spring Boot application
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/       # Java source files
│   │   │   └── resources/  # Application config, migrations
│   │   └── test/           # Unit and integration tests
│   └── pom.xml
├── docs/                   # Internal developer documentation
├── .gitignore
└── README.md
```

---

## Deployment (Planned)

| Component | Target |
|---|---|
| Backend API | Docker container on a VPS or university server |
| Database | PostgreSQL in Docker |
| Mobile App | APK distribution (direct install or Google Play internal testing) |

# Technology Stack

## Overview

This document describes the technology choices for the Siedlce Erasmus Guide mobile application. The stack follows a simplified, single-language approach: a native Android app built entirely in Kotlin, with data served from static JSON files bundled in the APK and optionally synced via Firebase.

The previous Java/Spring Boot/PostgreSQL backend has been removed to reduce complexity, eliminate the language split (Kotlin vs. Java), and remove unnecessary infrastructure overhead (VPS, Docker, database migrations, JWT authentication) for what is essentially static guide content.

---

## Architecture

```
┌─────────────────────────────────────┐
│         Android App (Kotlin)        │
│                                     │
│  ┌───────────┐   ┌───────────────┐  │
│  │ Map Screen│   │Checklist Screen│  │
│  └─────┬─────┘   └───────┬───────┘  │
│        │                  │          │
│  ┌─────┴──────────────────┴───────┐  │
│  │     Repository Layer           │  │
│  └─────┬──────────────────┬───────┘  │
│        │                  │          │
│  ┌─────┴─────┐   ┌───────┴───────┐  │
│  │ Local JSON │   │ Firebase (opt)│  │
│  │ (assets/) │   │  Firestore    │  │
│  └───────────┘   └───────────────┘  │
└─────────────────────────────────────┘
```

**Data flow:** The app reads POI and checklist data from JSON files in `assets/`. If Firebase is configured, it can pull updated data remotely. No custom backend server is needed.

---

## Frontend (Mobile Application)

| Component | Technology | Version |
|---|---|---|
| Language | Kotlin | 1.9+ |
| Platform | Android (Native) | Min SDK 26 (Android 8.0) |
| Build System | Gradle (Kotlin DSL) | 8.x |
| UI Framework | Jetpack Compose | 1.5+ |
| Navigation | Jetpack Navigation Compose | 2.7+ |
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
- **Room** for persisting checklist state and caching POI data locally
- **Hilt** for compile-time dependency injection with Android lifecycle awareness
- **Coil** for Kotlin-first image loading with Compose support
- **Kotlinx Serialization** for parsing bundled JSON data files

---

## Data Layer (Replaces Backend)

| Component | Technology | Purpose |
|---|---|---|
| Static Data | JSON files in `assets/` | POI data, checklist items, city info |
| Remote Sync (optional) | Firebase Firestore | Update POI data without app release |
| Image Storage (optional) | Firebase Storage | Host POI photos remotely |
| Analytics (optional) | Firebase Analytics | Basic usage tracking |

### Why No Custom Backend

The previous stack included Java 17, Spring Boot, PostgreSQL, Flyway, JWT, and Docker. This has been removed because:

- **No dynamic data:** POI locations and checklist items are static content that rarely changes
- **No user accounts:** There are no private features requiring authentication, so JWT adds complexity with zero business value
- **Operational overhead:** Running a VPS with Docker, PostgreSQL, and database migrations is unnecessary for a 2-person student project
- **Language split:** Maintaining both Kotlin (Android) and Java (backend) doubles the context-switching cost
- **Static JSON** bundled in the APK provides the same functionality with zero infrastructure

If remote updates are needed (e.g., adding new POIs without releasing a new APK), Firebase Firestore provides this out of the box with no server to maintain.

---

## Development Tools

| Tool | Purpose |
|---|---|
| Android Studio | Kotlin/Android IDE |
| Git + GitHub | Version control and collaboration |
| Firebase Console | Data management (if Firebase is used) |
| Google Maps Platform | API key management for Maps SDK |

---

## Project Structure

```
siedlce-erasmus-guide/
├── doc/                        # Project documentation
│   ├── prd.md                  # Product Requirements Document
│   └── tech_stack.md           # This file
├── android/                    # Kotlin Android application
│   ├── app/
│   │   ├── src/
│   │   │   ├── main/
│   │   │   │   ├── java/       # Kotlin source files
│   │   │   │   ├── res/        # Resources (strings, drawables)
│   │   │   │   └── assets/     # Bundled JSON data files
│   │   │   │       ├── pois.json
│   │   │   │       └── checklist.json
│   │   │   └── test/           # Unit tests
│   │   └── build.gradle.kts
│   ├── build.gradle.kts
│   └── settings.gradle.kts
├── docs/                       # Internal developer documentation
├── .gitignore
└── README.md
```

---

## Deployment

| Component | Target |
|---|---|
| Mobile App | APK distribution (direct install or Google Play internal testing) |
| Data Updates | Firebase Firestore (optional) or new APK release |
| Future | PWA version if long-term university adoption is planned |

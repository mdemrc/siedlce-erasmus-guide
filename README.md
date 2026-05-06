# Siedlce Erasmus Guide

Android application for incoming Erasmus students at the University in Siedlce (UwS). Shows a Google Maps screen with categorized points of interest and an arrival checklist.

## Status

MVP in progress. Map screen and checklist screen are implemented and the app builds and runs on an Android 8.0+ device. Bundled JSON contains 21 POIs and 17 checklist items.

## Tech Stack

- Kotlin, Jetpack Compose (Material 3), Navigation Compose
- MVVM with Hilt for DI
- Room for local cache (POIs) and persisted checklist state
- Google Maps SDK for Android + maps-compose
- kotlinx.serialization for JSON parsing
- Coil for POI image loading
- Accompanist Permissions for runtime location permission
- Optional remote source interface (`RemotePoiSource`) with a no-op default; a Firestore implementation template is included but commented out

## Project Structure

```
├── doc/          # Product requirements and tech stack
├── docs/         # Developer docs (data schema)
└── android/      # Android application module
    └── app/src/main/
        ├── assets/                # pois.json, checklist.json
        ├── java/com/siedlce/erasmusguide/
        │   ├── data/local/        # Room entities, DAOs, AppDatabase
        │   ├── data/model/        # Poi, ChecklistItem
        │   ├── data/remote/       # RemotePoiSource interface + no-op
        │   ├── data/repository/   # DataRepository, PoiRepository, ChecklistRepository
        │   ├── di/                # Hilt modules
        │   └── ui/                # map/, checklist/, theme/, Navigation.kt
        └── res/
```

## Getting Started

Requirements: Android Studio (or command-line tools), Android SDK with API 34, JDK 17 or 21.

1. Clone the repo.
2. Copy `android/local.properties.example` to `android/local.properties` and set:
   - `MAPS_API_KEY=<your Google Maps API key>` (required for the map to render)
   - `sdk.dir=<path to Android SDK>` (Android Studio sets this automatically)
3. Open `android/` in Android Studio, or build from the command line:

```powershell
cd android
.\gradlew.bat :app:assembleDebug          # build debug APK
.\gradlew.bat :app:installDebug           # install on connected device/emulator
.\gradlew.bat :app:testDebugUnitTest      # run unit tests
```

The Google Maps API key is read from `local.properties` and injected into the manifest via `manifestPlaceholders`. `local.properties` is git-ignored.

## Documentation

- [Product Requirements Document](doc/prd.md)
- [Technology Stack](doc/tech_stack.md)
- [Data Schema](docs/data_schema.md)

## Team

- Mehmet Demirci -- Erasmus student, UwS
- Süleyman Efe Metik -- Erasmus student, UwS

## License

University assignment for the Mobile Applications course at UwS (Uniwersytet w Siedlcach).


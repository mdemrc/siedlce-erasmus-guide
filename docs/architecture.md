# Architecture

A short overview of how the Android app is structured. Only describes what is implemented today.

## Layers

```
ui (Compose)  →  ViewModel (Hilt)  →  Repository  →  Room DAO   (single source of truth)
                                                ↘  RemotePoiSource (no-op by default)
                                                ↘  DataRepository (assets/*.json seed)
```

- **UI** uses Jetpack Compose with Material 3 (`ErasmusGuideTheme`, dynamic color on Android 12+).
- **ViewModels** expose `StateFlow`s; UI reads via `collectAsStateWithLifecycle`. ViewModels are constructor-injected with Hilt (`@HiltViewModel`).
- **Repositories** are the only thing ViewModels talk to. They combine Room (cache / persisted state) and `DataRepository` (bundled JSON seed). Optionally they can pull from a `RemotePoiSource`.
- **Room** holds two tables: `pois` (cached POI list) and `checked_items` (which checklist items the user has ticked). Both survive process death.

## Modules

There is a single Gradle module (`:app`). Source layout:

```
com.siedlce.erasmusguide/
├── ErasmusGuideApp.kt          # @HiltAndroidApp
├── MainActivity.kt             # @AndroidEntryPoint, hosts the NavHost
├── data/
│   ├── local/
│   │   ├── AppDatabase.kt      # Room database (v1)
│   │   ├── dao/                # PoiDao, CheckedItemDao
│   │   └── entity/             # CachedPoiEntity, CheckedItemEntity
│   ├── model/                  # Poi, ChecklistItem (kotlinx.serialization)
│   ├── remote/
│   │   ├── RemotePoiSource.kt          # interface
│   │   ├── NoopRemotePoiSource.kt      # default: returns null (offline)
│   │   └── FirestorePoiSource.kt       # commented-out template
│   └── repository/
│       ├── DataRepository.kt           # reads bundled JSON from assets
│       ├── PoiRepository.kt            # observePois / ensureSeeded / syncFromRemote
│       └── ChecklistRepository.kt      # loadItems / observeCheckedIds / toggle
├── di/
│   ├── DatabaseModule.kt       # provides AppDatabase + DAOs
│   └── RemoteModule.kt         # binds RemotePoiSource = NoopRemotePoiSource
└── ui/
    ├── Navigation.kt           # NavHost: map → checklist
    ├── map/
    │   ├── MapScreen.kt
    │   ├── MapViewModel.kt
    │   ├── PoiDetailSheet.kt           # ModalBottomSheet with Coil image
    │   ├── PoiCategoryStyle.kt         # marker color by category
    │   └── PoiCategoryLabels.kt        # category key → strings.xml label
    ├── checklist/
    │   ├── ChecklistScreen.kt
    │   └── ChecklistViewModel.kt
    └── theme/                  # Color, Type, Theme (Material 3)
```

## Data flow

**First launch.** `MapViewModel.init` calls `PoiRepository.ensureSeeded()`. If the `pois` table is empty, it loads `assets/pois.json` via `DataRepository`, maps to entities, and inserts. After that, `PoiRepository.syncFromRemote()` is invoked; the default no-op binding makes this a cheap no-op, and Firestore can be wired in later by swapping the binding in `RemoteModule`.

**Map screen.** `MapScreen` collects `filteredPois`, `categories`, `selectedCategory`, and `selectedPoi` from `MapViewModel`. Markers are coloured per category (`PoiCategoryStyle.markerIcon`). Tapping a marker calls `selectPoi(id)`; the resulting `selectedPoi` opens `PoiDetailSheet`.

**Checklist screen.** `ChecklistScreen` collects `itemsByCategory` and `checkedIds`. Toggling an item calls `ChecklistViewModel.toggleItem(id)`, which delegates to `ChecklistRepository.toggle()` -- it inserts a `CheckedItemEntity` or deletes by id. State persists in Room.

## Configuration

- `MAPS_API_KEY` is read from `android/local.properties` in `app/build.gradle.kts` and injected into `AndroidManifest.xml` via `manifestPlaceholders`. `local.properties` is git-ignored.
- `local.properties.example` documents the required keys.

## Testing

JVM unit tests live under `app/src/test/`:

- `ChecklistRepositoryTest` -- toggle behaviour against a fake `CheckedItemDao`.
- `PoiSerializationTest` -- kotlinx.serialization parsing for `Poi`, including `ignoreUnknownKeys`.

Run with:

```
.\gradlew.bat :app:testDebugUnitTest
```

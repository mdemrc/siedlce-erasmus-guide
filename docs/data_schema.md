# Data Schema Documentation

## POI Schema (`pois.json`)

Each Point of Interest follows this structure:

```json
{
  "id": "uws_main",
  "name": "UwS Main Building",
  "category": "university",
  "latitude": 52.1234,
  "longitude": 22.2900,
  "address": "ul. Konarskiego 2, 08-110 Siedlce",
  "phone": "+48 25 643 10 00",
  "website": "https://www.uws.edu.pl",
  "description": "Main administrative building of the University in Siedlce.",
  "openingHours": "Mon-Fri 08:00-16:00"
}
```

### Fields

| Field | Type | Required | Description |
|---|---|---|---|
| `id` | string | yes | Unique identifier (snake_case) |
| `name` | string | yes | Display name in English |
| `category` | string | yes | One of the predefined categories |
| `latitude` | number | yes | GPS latitude |
| `longitude` | number | yes | GPS longitude |
| `address` | string | yes | Street address |
| `phone` | string | no | Contact phone number |
| `website` | string | no | URL |
| `description` | string | yes | Short English description |
| `openingHours` | string | no | Human-readable hours |
| `imageUrl` | string | no | URL to a representative image (loaded with Coil) |

### Categories

| Category | Icon | Color |
|---|---|---|
| `university` | School | Blue |
| `dormitory` | Hotel | Purple |
| `food` | Restaurant | Orange |
| `grocery` | ShoppingCart | Green |
| `health` | LocalHospital | Red |
| `transport` | DirectionsBus | Teal |
| `finance` | ATM | Amber |
| `recreation` | Park | LightGreen |

---

## Checklist Schema (`checklist.json`)

Each checklist item follows this structure:

```json
{
  "id": "register_university",
  "title": "Register at the university",
  "description": "Visit the International Office with your acceptance letter and passport.",
  "category": "arrival",
  "order": 1
}
```

### Fields

| Field | Type | Required | Description |
|---|---|---|---|
| `id` | string | yes | Unique identifier (snake_case) |
| `title` | string | yes | Short action title |
| `description` | string | yes | Detailed instruction |
| `category` | string | yes | One of the predefined categories |
| `order` | number | yes | Display order within category |

### Categories

| Category | Description |
|---|---|
| `before_arrival` | Tasks to complete before traveling |
| `arrival` | First days in Siedlce |
| `settling` | First week setup tasks |
| `ongoing` | Recurring or long-term tasks |

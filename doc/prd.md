# Product Requirements Document (PRD)

## Project Name

**Siedlce Erasmus Guide** -- Mobile Application for Erasmus Students in Siedlce

## Overview

A mobile application designed to help Erasmus students arriving at the University in Siedlce (UwS). The app provides an interactive map of student-relevant locations and a practical checklist to help international students settle in quickly.

## Problem Statement

Erasmus students arriving in Siedlce face common challenges:

- Unfamiliarity with the city layout, transportation, and key locations
- Language barriers when navigating daily life (shopping, healthcare, administration)
- Difficulty finding student-relevant places (dormitories, faculties, libraries, social spots)
- Lack of a centralized, mobile-friendly information source tailored to exchange students

## Target Users

- Incoming Erasmus/exchange students at UwS
- International students planning their semester in Siedlce
- University international office staff (as a recommendation tool)

## Goals

1. Provide an interactive map of Siedlce with student-relevant points of interest
2. Help Erasmus students settle in quickly with a practical arrival checklist
3. Deliver content in English (primary)
4. Keep the tech stack simple and maintainable by a 2-person team

## MVP Features (Minimum Viable Product)

These are the core features that must be delivered this semester.

### 1. Interactive Map

- University buildings and faculties
- Student dormitories
- Restaurants, cafes, and grocery stores
- Healthcare facilities (hospitals, pharmacies, clinics)
- Public transportation stops
- ATMs and currency exchange points
- Categorized POI markers with filtering

### 2. Erasmus Practical Checklist

- Step-by-step arrival checklist (with checkable items)
- Document requirements and registration process
- Public transportation guide (local buses, train station)
- Healthcare and insurance information
- Emergency contacts and useful phone numbers
- Banking and SIM card setup tips

## Future Features (Post-MVP)

These features are planned for future versions if time permits. They are not part of the core delivery.

### City Guide

- General information about Siedlce
- Photo gallery of key landmarks

### University Information

- Library information and working hours
- International office contact details
- Academic calendar and important dates

### Events and Social

- Local events and activities
- Nearby cities worth visiting (Warsaw day trips, etc.)
- Cultural tips and Polish customs

## Known Risks and Challenges

| Risk | Impact | Mitigation |
|---|---|---|
| Content creation for 50+ POIs | High time cost -- gathering, verifying, translating location data is a hidden time sink | Start with ~15-20 essential POIs, expand incrementally |
| Offline map tiles | Technically difficult, large storage footprint | Use online-only maps for MVP; evaluate offline caching later |
| APK distribution | Google plans to restrict sideloaded APKs starting fall 2026 | Consider PWA as future alternative (see section below) |

## PWA Alternative (Noted)

The instructor raised an important point: if this app is intended to be used long-term by the university (not just as a course project), a Progressive Web App (PWA) would be a better fit:

- Easy to update (replace JSON on server)
- No APK installation or Play Store needed
- Works cross-platform (Android + iOS + desktop)
- No risk from Google's upcoming sideloading restrictions

**Decision:** We proceed with native Android (Kotlin) for this semester as it is the course requirement. The architecture (static JSON data, modular structure) is designed so that a PWA migration would be straightforward in the future.

## Non-Functional Requirements

- **Platform:** Android (Kotlin native)
- **Minimum SDK:** API 26 (Android 8.0)
- **Language:** English (primary UI language)
- **Offline Support:** Checklist and basic POI data available offline (bundled JSON); maps require internet
- **Performance:** App launch under 3 seconds, smooth scrolling and map interaction
- **Accessibility:** Following Material Design accessibility guidelines

## Success Metrics

- App is functional and installable on Android devices
- Interactive map displays categorized POIs correctly
- Erasmus checklist is complete and usable
- Content is accurate for current semester
- Positive feedback from test users (fellow Erasmus students)

## Project Scope

| MVP (This Semester) | Future Versions | Out of Scope |
|---|---|---|
| Android app (Kotlin) | City guide with photos | iOS version |
| Static JSON data (bundled in APK) | University info section | Real-time chat |
| Interactive map with categorized POIs | Events and social features | User-generated content |
| Erasmus arrival checklist | Offline map tile caching | Payment/booking features |
| Firebase remote data (optional) | PWA version | Multi-university support |
| | | JWT / user authentication |

## Timeline

| Phase | Description | Status |
|---|---|---|
| Phase 1 | Project setup, PRD, tech stack documentation | Complete |
| Phase 2 | POI data collection, JSON schema design, Android skeleton | Complete |
| Phase 3 | Android app UI -- map screen, checklist screen | In Progress |
| Phase 4 | Map integration (Google Maps SDK), POI rendering | Planned |
| Phase 5 | Testing, content review, and delivery | Planned |

## Team

| Role | Name |
|---|---|
| Developer | Mehmet Demirci (Erasmus Student) |
| Developer | Süleyman Efe Metik (Erasmus Student) |

- Mobile Development: Kotlin (Android)
- Data: Static JSON bundled in APK + optional Firebase Firestore
- Project submitted for: Mobile Applications course, UwS (Uniwersytet w Siedlcach)

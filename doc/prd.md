# Product Requirements Document (PRD)

## Project Name

**Siedlce Erasmus Guide** -- Mobile Application for Erasmus Students in Siedlce

## Overview

A mobile application designed to help Erasmus students arriving at Siedlce University of Natural Sciences and Humanities (UPH Siedlce). The app introduces the city of Siedlce, provides essential information for international students, and serves as a practical daily companion throughout their exchange period.

## Problem Statement

Erasmus students arriving in Siedlce face common challenges:

- Unfamiliarity with the city layout, transportation, and key locations
- Language barriers when navigating daily life (shopping, healthcare, administration)
- Difficulty finding student-relevant places (dormitories, faculties, libraries, social spots)
- Lack of a centralized, mobile-friendly information source tailored to exchange students

## Target Users

- Incoming Erasmus/exchange students at UPH Siedlce
- International students planning their semester in Siedlce
- University international office staff (as a recommendation tool)

## Goals

1. Provide a comprehensive city guide for Siedlce tailored to student needs
2. Help Erasmus students settle in quickly with practical information
3. Offer an interactive map with points of interest relevant to students
4. Deliver content in English (primary) with potential Polish support
5. Create a maintainable, extensible platform for future semesters

## Core Features

### 1. City Guide

- General information about Siedlce (history, population, climate)
- Districts and neighborhoods overview
- Photo gallery of key landmarks

### 2. Interactive Map

- University buildings and faculties
- Student dormitories
- Restaurants, cafes, and grocery stores
- Healthcare facilities (hospitals, pharmacies, clinics)
- Public transportation stops and routes
- ATMs and currency exchange points
- Parks and recreation areas

### 3. Erasmus Practical Guide

- Step-by-step arrival checklist
- Document requirements and registration process
- Public transportation guide (local buses, train station)
- Healthcare and insurance information
- Emergency contacts and useful phone numbers
- Banking and SIM card setup tips

### 4. University Information

- Campus map and faculty locations
- Library information and working hours
- International office contact details
- Academic calendar and important dates
- Student organization contacts

### 5. Events and Social

- Local events and activities
- Student meetup suggestions
- Nearby cities worth visiting (Warsaw day trips, etc.)
- Cultural tips and Polish customs

## Non-Functional Requirements

- **Platform:** Android (Kotlin native)
- **Minimum SDK:** API 26 (Android 8.0)
- **Language:** English (primary UI language)
- **Offline Support:** Core city guide content available offline
- **Performance:** App launch under 3 seconds, smooth scrolling and map interaction
- **Accessibility:** Following Material Design accessibility guidelines

## Success Metrics

- App is functional and installable on Android devices
- Core features (city guide, map, Erasmus guide) are implemented
- Content is accurate and up-to-date for current semester
- Positive feedback from test users (fellow Erasmus students)

## Project Scope -- Phase 1 (Current Semester)

| In Scope | Out of Scope |
|---|---|
| Android app (Kotlin) | iOS version |
| Backend API (Java/Spring Boot) | Real-time chat |
| Static city guide content | User-generated content |
| Interactive map with POIs | Social media integration |
| Erasmus checklist | Payment/booking features |
| University info section | Multi-university support |

## Timeline

| Phase | Description | Status |
|---|---|---|
| Phase 1 | Project setup, PRD, tech stack documentation | In Progress |
| Phase 2 | Backend API development, database design | Planned |
| Phase 3 | Android app UI/UX implementation | Planned |
| Phase 4 | Map integration, content population | Planned |
| Phase 5 | Testing, polish, and delivery | Planned |

## Team

- Mobile Development: Kotlin (Android)
- Backend Development: Java (Spring Boot)
- Project submitted for: Mobile Applications course, UPH Siedlce

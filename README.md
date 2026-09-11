# Java Travel Simulation Desktop Application

Desktop application developed in **Java** as an individual academic practice for *Introducción a la Programación y Computación 1* at Universidad de San Carlos de Guatemala (USAC).

The project simulates a small transportation service through a Swing-based graphical interface. Its main technical focus is the combination of object-oriented programming, collections, file persistence, Java serialization, and concurrent trip execution using threads.

## Features

- administrator login for the desktop application;
- route loading and distance management;
- vehicle selection and trip generation;
- simultaneous trip execution;
- visual trip progress;
- fuel consumption simulation and refueling;
- outbound and return journeys;
- completed-trip history;
- persistence of trips, routes, and application state through serialized files;
- Swing-based user interface.

## Technical focus

- **Java 21**
- **Java Swing / AWT**
- Object-oriented programming
- Java Collections
- `Thread`-based concurrency
- `Serializable`
- `ObjectInputStream` / `ObjectOutputStream`
- File persistence
- Event-driven UI
- JTattoo Look & Feel
- Maven
- GitHub Actions

The original coursework was built with NetBeans/Ant. A Maven configuration was later added to make compilation reproducible without machine-specific library paths.

## Notable implementation

Each active route simulation executes independently through Java threads. The application tracks traveled distance and fuel consumption while updating the graphical representation of the trip.

Application state is persisted locally using Java object serialization so selected data can be restored between executions.

## Project structure

```text
.
├── IPC1_Practica2_201222010/
│   ├── Archivos/
│   ├── Manuales/
│   ├── src/
│   │   └── ipc1_practica2_201222010/
│   │       ├── Main.java
│   │       ├── Login.java
│   │       ├── GenerarViaje.java
│   │       ├── IniciarViaje.java
│   │       ├── Recorrido.java
│   │       ├── Viaje.java
│   │       └── ...
│   ├── build.xml
│   └── nbproject/
├── pom.xml
└── .github/workflows/java-ci.yml
```

## Build

Requirements:

- JDK 21+
- Maven 3.9+

Compile and package:

```bash
mvn clean package
```

Run from Maven:

```bash
mvn exec:java
```

## Demo credentials

Default local demo credentials:

```text
User: admin
Password: admin
```

They can be overridden without modifying source code:

```bash
IPC1_TRAVEL_ADMIN_USER='demo-user' \
IPC1_TRAVEL_ADMIN_PASSWORD='local-password' \
mvn exec:java
```

These credentials are intentionally demo-only. This academic desktop project does not implement a production authentication model.

## CI

GitHub Actions compiles and packages the project with Maven and Java 21 for pull requests and pushes to `main`.

## Repository cleanup

The original academic history and application logic are preserved. Portfolio cleanup removes generated binaries and machine-specific artifacts, replaces the vendored UI dependency with Maven dependency management, and adds reproducible build documentation and CI.

## Context

This repository demonstrates foundational Java development beyond basic CRUD: desktop event handling, object modeling, local persistence, serialization, state management, and concurrency with threads.

# con terra Application Program

This repository contains the code for my application at con terra GmbH. The project is a small Spring Boot service that
exposes a weather forecast endpoint and uses the OpenWeather API to fetch forecast data.

---

## Table of Contents

- [About](#about)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
    - [Configuration](#configuration)
    - [Run the app](#run-the-app)
- [Usage](#usage)
- [Testing](#testing)

---

## About

A short summary of what this project does and who it's for.

This service exposes a `/forecast` endpoint which accepts latitude (`lat`) and longitude (`lon`) and returns a minimal
forecast object based on data fetched from the OpenWeather API. It was created as part of an assignment and is intended
as a small example of integrating an external REST API into a Spring Boot application. The service also provides a
second
variant of the endpoint that accepts an address (country, city, street, housenumber) and resolves coordinates via
OpenCage.

## Features

- Fetch weather forecast from OpenWeather
- Minimal forecast representation returned to clients
- Optional address-to-coordinate lookup via OpenCage
- Basic error handling for missing or invalid responses from the external API

## Tech Stack

- Java 21
- Spring Boot 3.5.7
- Gradle (wrapper included)

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing
purposes.

### Prerequisites

- Java JDK 21 or later
- Git
- On Windows: a terminal (cmd.exe or PowerShell) — the included `gradlew.bat` will be used to run Gradle tasks.

### Installation

Clone the repository:

```cmd
git clone https://github.com/7erra/conterra.git
cd conterra
```

### Configuration

This application requires an API key for OpenWeather. The address-based endpoint additionally requires an OpenCage API
key
(if you plan to use the address lookup).

Set environment variables on Windows (cmd.exe):

```cmd
set OPENWEATHER_API_KEY "YOUR_OPENWEATHER_API_KEY"
set OPENCAGE_API_KEY "YOUR_OPENCAGE_API_KEY"
```

### Run the app

Start the application locally using the Gradle wrapper (Windows cmd):

```cmd
gradlew.bat bootRun
```

## Usage

Once the application is running on the default port (8080), call the forecast endpoint.

Coordinate-based example (Windows cmd):

```cmd
curl "http://localhost:8080/forecast?lat=51.935132&lon=7.652511"
```

Address-based example (requires `OPENCAGE_API_KEY`):

```cmd
curl "http://localhost:8080/forecast?country=Germany&city=Münster&street=Martin-Luther-King-Weg&housenumber=20"
```

## Testing

The project contains tests in `src/test/java`. Run tests with Gradle:

```cmd
gradlew.bat test
```

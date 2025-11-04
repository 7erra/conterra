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
as a small example of integrating an external REST API into a Spring Boot application.

## Features

- Fetch weather forecast from OpenWeather
- Minimal forecast representation returned to clients
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

1. Clone the repository:

```cmd
git clone https://github.com/7erra/conterra.git
cd conterra
```

2. (Optional) Verify the Gradle wrapper works:

```cmd
gradlew.bat --version
```

### Configuration

The application uses the OpenWeather API. Set an environment variable (Windows cmd):

```cmd
setx OPENWEATHER_API_KEY "YOUR_OPENWEATHER_API_KEY"
```

### Run the app

Start the application locally using the Gradle wrapper (Windows):

```cmd
gradlew.bat bootRun
```

Or build and run the jar:

```cmd
gradlew.bat build
java -jar build\libs\conterra-<version>.jar
```

Replace `<version>` with the actual artifact name produced by the build.

## Usage

Once the application is running, call the forecast endpoint. Example (Windows cmd):

```cmd
curl "http://localhost:8080/forecast?lat=51.935132&lon=7.652511"
```

Expected behavior:

- On success: the service returns a JSON object with timestamp, temperature, feels-like temperature, and humidity.
- On external API failure or empty response: the service returns an HTTP 502 (Bad Gateway) with a short error message.

## Testing

The project contains tests in `src/test/java`. Run tests with Gradle:

```cmd
gradlew.bat test
```

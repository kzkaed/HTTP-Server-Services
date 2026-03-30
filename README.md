# Web Server (HTTP)

[![Build](https://github.com/kzkaed/Web-Server/actions/workflows/build.yml/badge.svg)](https://github.com/kzkaed/Web-Server/actions/workflows/build.yml)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=kzkaed&metric=alert_status)](https://sonarcloud.io/summary/overall?id=kzkaed)

A Java HTTP/1.1 server built around a registry pattern. Request handling is composed from interchangeable `Asset` implementations registered with an `AssetManager` — adding a new route or content type means writing a new `Asset`, not modifying the server.

## Architecture

```
Request → RequestParser → AssetManager → Asset → Response
```

**Key interfaces:**

- `Asset` — handles a request type (`canHandle` + `execute`)
- `AssetManager` — registry that finds the first matching asset for a request
- `View` / `ViewFactory` — renders HTML; injected into assets so rendering is swappable
- `ParametersParser` — parses query strings and URL-encoded parameters
- `Logger` — error and info logging interface; `SystemLogger` routes all output through `System.out`/`System.err` instead of raw `printStackTrace` calls

**Built-in asset handlers:**

| Asset | Handles |
|---|---|
| `StaticAsset` | Static files from the public directory |
| `StaticPathExt` | Static files at `/test/static` path extension |
| `DirectoryAsset` | Directory listings |
| `ImageAsset` | Image files (JPEG, PNG, GIF) |
| `DynamicAsset` | Dynamic routes with query parameters |
| `DynamicPathExt` | Dynamic routes at `/test/dynamic` path extension |
| `Parameter` | `/parameters` route — echoes query params |
| `Options` | HTTP OPTIONS requests |
| `Post` | HTTP POST requests |
| `Put` | HTTP PUT requests |
| `FileNotFound` | 404 fallback |

The `com.scutigera.color` package demonstrates extending the server with a custom `Color` asset registered via `Application`.

## Requirements

- Java 21+ — install via Homebrew: `brew install openjdk`
- [Gradle](https://gradle.org/install/) — install via Homebrew: `brew install gradle`

## Running Tests

```
gradle test
```

## Code Quality

Static analysis runs via [SonarCloud](https://sonarcloud.io/summary/overall?id=kzkaed). The GitHub Actions workflow runs tests and SonarCloud analysis on every push to `main` and on pull requests.

To run analysis locally:

```
gradle test sonar -Dsonar.token=$SONAR_TOKEN
```

## CI

The project uses GitHub Actions (`.github/workflows/build.yml`) to:

1. Build and run all tests with JUnit 5
2. Generate JaCoCo coverage reports
3. Run SonarCloud static analysis

## Starting the Server

```
gradle build -x test
java -cp build/classes/java/main server.Main <port> <public-directory>
```

Defaults: port `8080`, public directory `public`.

Example — start on port 9090 instead of the default:

```
java -cp build/classes/java/main server.Main 9090
```

Then visit http://localhost:9090/

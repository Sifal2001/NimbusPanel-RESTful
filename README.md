# NimbusPanel

Full-stack weather app — a JavaFX desktop client backed by a Spring Boot/PostgreSQL REST API.

<img width="1197" height="688" alt="image" src="https://github.com/user-attachments/assets/5bbb983c-6719-45f1-8dfd-613ad4d9b56a" />

## Features

- **User accounts** — registration and login with BCrypt-hashed passwords
- **Persistent favourites** — per-user saved locations stored in PostgreSQL
- **Live forecasts** — current conditions, 24-hour hourly, and 5-day outlook via an OpenWeather proxy
- **Responsive UI** — network calls run on background threads so the interface never freezes
- **Per-location time** — each city's local time derived from its own UTC offset
- **Containerized backend** — the full server + database stack runs with a single `docker compose up`

## Tech stack

**Client** — Java, JavaFX, Gson, JUnit 5

**Server** — Spring Boot, Spring Security, Spring Data JPA, Spring WebFlux (WebClient), PostgreSQL, Mockito, Docker

**External** — OpenWeather One Call API

## Architecture

NimbusPanel is split into two independently deployable repositories: a JavaFX desktop client and a Spring Boot REST API backed by PostgreSQL. The client never talks to OpenWeather directly — all external calls are proxied through the server, which keeps the API key server-side and out of the distributed client.

```
┌─────────────────┐         HTTP/JSON          ┌──────────────────────────┐
│  JavaFX Client  │  ───────────────────────▶  │   Spring Boot REST API   │
│                 │                            │                          │
│  • UI + scenes  │  ◀───────────────────────  │  Controllers → Services  │
│  • HTTP calls   │                            │       → JPA Repos        │
│  • Gson parsing │                            └───────────┬──────────────┘
└─────────────────┘                                        │
                                          ┌────────────────┴────────────────┐
                                          ▼                                 ▼
                                  ┌───────────────┐               ┌──────────────────┐
                                  │  PostgreSQL   │               │   OpenWeather    │
                                  │  (users,      │               │   One Call 3.0   │
                                  │   favourites) │               │   (proxied)      │
                                  └───────────────┘               └──────────────────┘
```

**Request lifecycle** — when a user searches for a city, the client first calls `GET /api/v1/castLocation` to geocode the name into coordinates, then `GET /api/v1/castRequest?lat=&lon=` to fetch the forecast. The server injects the OpenWeather key, calls the upstream API, and returns the JSON. The client parses it into a structured `WeatherCast` object (current, hourly, daily) with Gson and renders it — all on a background thread so the UI stays responsive.

**Authentication** — `POST /api/v1/user/authenticate` validates an email and password against a BCrypt hash and returns the user record on success or `401` on failure. Favourites are then managed as nested resources scoped by user id (`POST` / `DELETE /api/v1/user/{id}/favourites`).

## API reference

| Method | Path | Description |
|--------|------|-------------|
| `GET` | `/api/v1/castLocation?q={city}` | Geocode a city name to coordinates |
| `GET` | `/api/v1/castRequest?lat={lat}&lon={lon}` | Fetch current, hourly, and daily forecast |
| `GET` | `/api/v1/user` | List users |
| `POST` | `/api/v1/user` | Create a user |
| `POST` | `/api/v1/user/authenticate` | Validate credentials, return the user |
| `POST` | `/api/v1/user/{id}/favourites` | Add a favourite location for a user |
| `DELETE` | `/api/v1/user/{id}/favourites/{favouriteId}` | Remove a favourite location |

## Configuration

**Database** — datasource settings resolve from environment variables with local defaults, so the app runs out of the box for local development and is overridden in Docker:

```properties
spring.datasource.url=${DB_URL:jdbc:postgresql://localhost:5432/nimbuspanel}
spring.datasource.username=${DB_USERNAME:postgres}
spring.datasource.password=${DB_PASSWORD:}
spring.jpa.hibernate.ddl-auto=update
```

In Docker the compose file supplies Spring's native `SPRING_DATASOURCE_URL`, `SPRING_DATASOURCE_USERNAME`, and `SPRING_DATASOURCE_PASSWORD`, which take precedence and point the server at the `db` container. Hibernate's `ddl-auto=update` generates and evolves the schema from the JPA entities, so no manual SQL migration is required.

**OpenWeather API key** — the key is never committed. It is read from the `API_KEY` environment variable first, falling back to a gitignored `config.properties` file for local development. In Docker, `API_KEY` is passed through from the host:

```yaml
environment:
  API_KEY: ${API_KEY}
```

Copy `config.properties.example` to `config.properties` and add your key, or export `API_KEY` in your environment before running.














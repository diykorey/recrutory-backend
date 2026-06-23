# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Overview

**Kandidato** — a recruitment / candidate-management (ATS) web app. Despite the `recrutory-backend` name, this is a **full-stack** application: a Java Spring backend with a Backbone.js single-page UI bundled into the same WAR.

> This directory is a near-duplicate of `../recrutory-ui` (same `com.kandidato` Maven app). Changes here likely need mirroring there. See the root `../CLAUDE.md`.

Stack: Java **1.7**, Spring 4.0.2, Spring Security 3.2.3, Spring Data JPA 1.5.1, Hibernate 4.3.4, MySQL (5.1.28 driver), Elasticsearch 1.0.1, Quartz 2.2.1, HikariCP, Flyway 3.0, Logback. Frontend: Backbone.js + Underscore + RequireJS (AMD) + jQuery.

## Modules (Maven multi-module)

- `kandidato-db` — Flyway SQL migrations (no Java).
- `kandidato-core` — JPA entities, Spring Data repositories, manager/business layer, persistence config (`com/kandidato/config/PersistenceConfig.java`).
- `kandidato-search` — Elasticsearch integration (resume indexing/search).
- `kandidato-web` — Spring MVC controllers/services, Spring Security, scheduling, the bundled Backbone.js frontend; packaged as the WAR.

## Build / run / test

Requires a Java 1.7 toolchain and (for running) a local MySQL and Elasticsearch.

```bash
mvn clean install                                              # build everything
mvn clean package -Popenshift                                 # package for deploy -> webapps/ROOT.war
mvn test                                                       # run all tests
mvn -Dtest=CommentServiceTest test                            # run a single test class
mvn -Dtest=CommentServiceTest#someMethod test                 # run a single test method
mvn -f kandidato-db/pom.xml clean package -Dskip.integration.tests=true   # run DB migrations only
```

Note: many integration tests hit `localhost:8080` via `RestTemplate` and are commented out; they assume a running app + MySQL + Elasticsearch.

## Architecture

Request flow:

```
DispatcherServlet
  -> @Controller / @RequestMapping services (kandidato-web)
  -> Manager (business logic)
  -> Spring Data JPA repository
  -> Hibernate -> MySQL
```

Key config classes:
- `kandidato-web/.../config/`: `WebXmlConfig` (web initializer), `MainConfig` (root context), `WebMvcConfig`, `SecurityConfig` (BCrypt, DAO auth), `SocialConfig` (LinkedIn OAuth), `ElasticSearchConfig`, `ScheduleConfig` (Quartz; `ResumeIndexingJob` runs every ~2 min), `SpringSecurityInitializer`.
- `kandidato-core/.../config/PersistenceConfig.java`: JPA + Hibernate + HikariCP datasource + transactions.

## Conventions

From the wiki (`../recrutory-wiki/Coding conventions.md`) and the code:
- Layered: **Service → Manager → Repository**.
- `Impl` suffix on implementations; custom repos annotated `@Repository`, paired with `{Entity}Query` builder classes.
- Cross-cutting interfaces: `CommentableEntity` (polymorphic comments), `CreatorAware`.
- **Quirk to watch:** some web controllers are named `{Entity}ServiceImpl` and mix controller + business logic — don't treat the name as proof it's a pure service.

## Configuration

Property files under `src/main/resources/com/kandidato/config/`:
- `kandidato-core/.../persistence.properties` — JDBC `jdbc:mysql://localhost:3306/KANDIDATO` (dev creds `root/root`); schemas `VERSION` (Flyway), `KANDIDATO` (app), `QUARTZ` (scheduler).
- `kandidato-web/.../elasticsearch.properties` — TransportClient `127.0.0.1:9300`.
- `kandidato-web/.../social.properties` — LinkedIn API key/secret.

Maven profiles: `local` (default) and `openshift` (final name `kandidato`, output to `webapps/`). Logging via `kandidato-web/src/main/resources/logback.xml`.

## Database migrations

Flyway scripts in `kandidato-db/sql/version-0.1/`, named `patch-N__Title.sql` (e.g. `patch-3__Kandidato_tables.sql`).

## Frontend (bundled in the WAR)

Backbone.js + Underscore + RequireJS (AMD) + jQuery, under `kandidato-web/src/main/webapp/js/`:
- Entry: `app.js`, `main.js`, `router.js` (hash-based routing), `text.js`.
- Layout: `model/`, `collection/`, `view/`, `template/`, `util/`, `lib/`.
- App shell: `kandidato-web/src/main/webapp/kandidato.html` (served statically from the WAR; `index.jsp` is a debug/landing page).
- **No JS build step / no npm** — files are served as-is.

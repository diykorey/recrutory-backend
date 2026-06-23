# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## What this repo is

A monorepo that **aggregates 7 previously-separate git repositories** of *Recrutory* (internal codename **Kandidato**) — a recruitment / candidate-management (ATS) system, mostly ~2014–2015 vintage. The parent repo is brand new (single "Initial commit"); the components are largely legacy/archival.

The actively-worked-on application is **Kandidato** (`recrutory-backend` + `recrutory-ui`). The other directories are legacy UIs, a landing page, deployment scripts, and docs.

## ⚠️ Nested git repositories

Each component directory has its **own `.git`**. The parent repo only has an initial commit and currently treats every component as untracked.

- For component-level git work, use `git -C <component> …` (or `cd` into the component first).
- Don't assume root-level `git log`/`git status` reflects a component's real history — it doesn't.

## Component map

| Directory | What it is | Stack | Status |
|---|---|---|---|
| `recrutory-backend` | Full-stack Kandidato app (Java backend + Backbone.js UI bundled in one WAR) | Spring 4 + Hibernate 4 + MySQL + Elasticsearch + Backbone.js | **ACTIVE** — see `recrutory-backend/CLAUDE.md` |
| `recrutory-ui` | **Near-duplicate snapshot of the same app** as `recrutory-backend` | same as above (+ stray, unused `.svelte-kit`/`node_modules`) | **ACTIVE** — see `recrutory-ui/CLAUDE.md` |
| `recrutory-old-ui` | Older standalone SPA | AngularJS 1.3 + Bower + Express proxy (npm) | legacy |
| `recrutory-landing` | Marketing landing page | Maven WAR, Google App Engine + RESTEasy, jQuery | legacy |
| `recrutory-deployment` | Provisioning scripts for dev/prod servers | Bash (installs Java 8, Tomcat 8) | ops |
| `recruitorywebdeployment` | Compiled AngularJS assets + IIS `web.config` (build output of `recrutory-old-ui`) | static/minified JS+CSS | generated artifact — do not hand-edit |
| `recrutory-wiki` | Markdown docs (~2014, OpenShift era) | markdown | reference only; outdated, but holds the only Coding-conventions notes |

## Important gotcha: backend ≈ ui

`recrutory-backend` and `recrutory-ui` are **two copies of one codebase** (`com.kandidato` Maven multi-module: `kandidato-db`, `kandidato-core`, `kandidato-search`, `kandidato-web`). Each contains both the Spring backend *and* the Backbone.js frontend. A change made in one almost certainly needs mirroring in the other — confirm with the user which copy is canonical before letting them diverge.

For build / run / test of the active app, see **`recrutory-backend/CLAUDE.md`** (and the matching **`recrutory-ui/CLAUDE.md`**).

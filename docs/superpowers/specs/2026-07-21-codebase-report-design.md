# Design: Recrutory / Kandidato codebase report

**Date:** 2026-07-21
**Branch:** `report/task-1-9b1677`
**Status:** Approved (design), pending implementation plan

## Goal

Produce a single, evidence-backed report on the Recrutory (internal codename
**Kandidato**) monorepo that aggregates 7 previously-separate git repositories.
The report covers four lenses on one subject:

1. **Full inventory** — catalog all 7 components (purpose, real stack, status, size/LOC, relationships).
2. **Architecture overview** — how the active app is built (modules, data flow, stack).
3. **Tech-debt & risk audit** — dependency/CVE-era exposure, dead/duplicate code, repo sprawl, security, doc drift.
4. **Revival feasibility** — effort, risk, blockers, and a go/no-go recommendation.

## Audience & deliverables

Two audiences, two artifacts kept in sync:

- **Markdown source-of-truth** — `docs/codebase-report.md`, committed to this branch. Developer-facing, diff-able.
- **HTML artifact** — a self-contained Claude Artifact (tables, charts, in-page nav) for stakeholders/non-devs. A copy of the HTML is also saved under `docs/`.

The Markdown is authored first and is canonical; the HTML is a rendered view of the same content.

## Analysis approach (Approach A — approved)

Parallel read-only subagents gather findings; the main session synthesizes and verifies.

- Fan out ~5 read-only subagents (Explore / general-purpose via the Agent tool), scoped one per major component or lens. Each returns **structured findings with evidence** (manifest versions, LOC, key source excerpts, file paths).
- The main session **verifies load-bearing claims** before they enter the report:
  - dependency / framework versions from real manifests (`pom.xml`, `package.json`, `bower.json`),
  - the true `recrutory-backend` ↔ `recrutory-ui` relationship,
  - whether the active app builds (best-effort, per existing local-run recipe),
  - LOC counts from actual file scans.
- Doc-vs-reality discrepancies are called out explicitly (see Known corrections).

Not chosen: (B) fully sequential single-context analysis — slower, heavier context; (C) full multi-agent Workflow with adversarial verification — requires explicit opt-in and higher token cost, overkill for this scope.

## Where the code lives (important constraint)

The 7 component directories are **untracked in the parent repo**, so they are
**absent from this worktree**. Analysis therefore **reads from the parent working
directory** (`/home/andriy/WorkVolume/work/projects/pet/recrutory/<component>`),
while the report is **written here** in the worktree
(`.worktrees/report/task-1-9b1677/docs/`).

## Report structure

1. **Executive summary** — revival verdict, top risks, one-paragraph "what this is."
2. **Inventory** (lens 1) — the 7-component table expanded: purpose, verified stack, status, size/LOC, relationships, doc corrections.
3. **Architecture** (lens 2) — deep on the active app: `kandidato-db` / `kandidato-core` / `kandidato-search` / `kandidato-web` modules, data flow, Spring 4 / Hibernate 4 / Elasticsearch / MySQL stack, the Backbone.js frontend, real backend↔ui relationship.
4. **Tech-debt & risk audit** (lens 3) — dependency/CVE-era exposure, dead/duplicate code, 7-repo sprawl, security concerns, documentation drift.
5. **Revival feasibility** (lens 4) — effort/risk/blockers, modernization path, go/no-go recommendation.
6. **Appendix** — evidence: versions, LOC counts, build-check results.

## Component scope map (verified during design)

| Component | Size | Verified nature | Depth |
|---|---|---|---|
| recrutory-backend | 140 MB | Maven multi-module Java app (active) | Deep-dive |
| recrutory-ui | 632 KB | Node/Bower/Grunt/Firebase — **not** a Maven duplicate | Correct-the-record + inventory |
| recrutory-old-ui | 252 KB | AngularJS + Bower + Express | Inventory |
| recrutory-landing | 2.6 MB | Maven WAR (Google App Engine landing) | Inventory |
| recruitorywebdeployment | 972 KB | Compiled AngularJS build artifact | Inventory |
| recrutory-deployment | 116 KB | Bash provisioning scripts | Inventory |
| recrutory-wiki | 48 KB | Markdown docs (~2014) | Inventory + source for coding conventions |

## Known corrections to surface (found during design recon)

- **`recrutory-ui` is not a near-duplicate of `recrutory-backend`.** `CLAUDE.md` describes it as "a near-duplicate snapshot of the same app" on the Spring/Hibernate/Maven stack. In reality it has no Maven modules — it is a Node/Bower/Grunt/Firebase project (`Gruntfile.js`, `firebase.json`, `content/`, `support/`). The report must verify the actual relationship and correct this claim. The "backend ≈ ui" gotcha in `CLAUDE.md` needs re-examination against evidence.

## Success criteria

- All four lenses present, each backed by cited evidence (file path / version / count).
- Every load-bearing claim verified, not inherited from `CLAUDE.md`.
- Markdown and HTML deliverables produced, presenting the same findings (the HTML may add charts and in-page navigation).
- Documentation discrepancies (starting with `recrutory-ui`) explicitly flagged.

## Out of scope

- Modifying any component source.
- Deep-diving legacy components at the same depth as the active app.
- Running the full application beyond a best-effort build check.

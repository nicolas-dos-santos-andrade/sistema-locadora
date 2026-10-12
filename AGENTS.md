# Repository Guidelines

## Project Context & Planned Technologies

`sistema-locadora` is a web application for managing a movie rental business. Its learning goals include Full Stack development, Git, Docker, and CI/CD. Explain technical decisions and important commands, including their purpose and effects, to support learning.

The selected backend stack is Java 25, Spring Boot, and Maven, with PostgreSQL for persistence. Local infrastructure will use Docker and Docker Compose. Version control will use Git and GitHub, and CI/CD will use GitHub Actions. The frontend technology is still undecided; do not select or introduce a frontend stack without an explicit decision.

These are project directions, not existing implementations or configurations. Implement the system gradually, starting with the Movies module.

## Project Structure & Module Organization

This workspace (`sistema-locadora`) is currently an empty project scaffold. No application source, tests, assets, or dependency manifests are present. The `.agents/`, `.codex/`, `.aws/`, and `.git/` directories are reserved workspace metadata; do not place application code in them.

When introducing the application, document its actual directory layout in this guide and a root `README.md`. Group code by feature or responsibility, keep tests easy to locate, and give static assets a dedicated directory. Avoid introducing directory structures for components that do not yet exist.

Start with a modular monolith. Organize the backend by business feature, with clear module boundaries. Within each feature, separate responsibilities between controllers (HTTP requests and responses), services (business rules), repositories (data access), entities (persistent domain data), and DTOs (data exchanged through the API). Validate input data and handle errors consistently with appropriate HTTP responses, without exposing sensitive internal details.

The planned business modules are:

1. Movies (Filmes): creation, editing, lookup, and deletion of movies.
2. Catalog (Catálogo): movie search and availability.
3. People (Pessoas): registration of customers and employees.
4. Rentals (Locações): loans, returns, and due dates.
5. Finance (Financeiro): payments, fines, and reports.

Introduce module packages and supporting components only when implementing their functionality. Do not describe planned modules as already available.

## Build, Test, and Development Commands

No build, development, test, or lint commands are currently configured. Do not assume commands such as `npm test` or `make build` work here.

The first backend implementation should add the Maven `pom.xml` and document prerequisites and exact commands for installation, local development, production builds, testing, and linting in `README.md`. Document configuration and Docker/Docker Compose commands when that infrastructure is introduced, and GitHub Actions workflows when CI/CD is added. Explain important commands for learning, and do not present unconfigured commands as usable. Commit dependency lockfiles when supported by the selected tooling.

## Coding Style & Naming Conventions

Java 25 is selected for the backend; no formatter or linter is configured yet. Follow Java conventions and configure formatting and linting before adding substantial code. Follow the conventions of the frontend language once its technology is defined.

Use descriptive names, consistent indentation within each file, and small modules with clear responsibilities. Prefer names that describe behavior or domain concepts over abbreviations. Keep formatting-only changes separate from functional changes when practical.

## Testing Guidelines

JUnit 5 is selected for backend automated tests, but no testing framework or coverage threshold is configured yet. Introduce automated tests with the first functional implementation and document their location, naming convention, and execution command in `README.md`.

Test observable behavior, including invalid inputs and failure paths. Every bug fix should include a regression test when feasible. Report which checks ran and any checks that could not run.

## Commit & Pull Request Guidelines

Git history is unavailable in this workspace, so no existing commit convention can be verified. Use concise, imperative commit subjects, such as `Add rental validation`, and keep each commit focused.

Work on feature or fix branches and submit changes through Pull Requests on GitHub. Keep changes incremental and explain relevant technical decisions to support review and learning.

Pull requests should explain the problem, summarize the change, link relevant issues, and list validation performed. Include screenshots for visible interface changes and setup instructions for new configuration.

## Security & Configuration

Never commit credentials, private keys, or populated environment files. Document required configuration using placeholders and provide an example configuration file when needed.

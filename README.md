# TraceLens AI

TraceLens AI is an AI-powered incident investigation copilot for event-driven microservices.

It helps engineering teams reduce MTTR by analyzing logs, traces, telemetry, and service behavior to generate incident summaries, root-cause suggestions, and remediation hints.

## Problem

Modern distributed systems are difficult to debug during production incidents.

Engineering teams often need to manually inspect:

- application logs
- traces
- metrics
- deployment history
- queue lag
- retries
- downstream failures

This slows down incident response and increases downtime.

## Solution

TraceLens AI acts as an intelligent investigation layer on top of existing observability data.

It helps engineers answer questions like:

- What happened?
- Which service is most suspicious?
- When did the issue start?
- Which logs are most relevant?
- What is the likely root cause?
- What should we check next?

## Initial MVP Scope

The first version focuses on uploaded application logs.

### MVP Features

- Upload incident logs
- Parse and store log entries
- Generate AI-powered incident summaries
- Identify suspicious errors and affected services
- Suggest possible root causes
- Provide remediation hints

## Target Users

TraceLens AI is designed for:

- backend engineers
- SRE teams
- platform teams
- DevOps engineers
- engineering managers operating microservices

## Tech Stack

### Backend

- Java 21
- Spring Boot
- PostgreSQL
- Spring Data JPA
- Spring Boot Actuator

### Frontend

- React
- TypeScript
- Vite

### AI

- OpenAI API
- Future: embeddings and semantic search

### Future Integrations

- OpenTelemetry
- Kubernetes
- Kafka
- AWS SQS
- Slack
- Jira

## Project Structure

```text
trace-lens-ai/
├── backend/
├── frontend/
├── infra/
├── docs/
└── README.md

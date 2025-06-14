# Usage Managment Server

This is a Java Spring Boot server application designed as a backend for a building management software. The software focuses on managing buildings, their tenants, and the consumption data associated with them.

## Overview
- **Technology**: Java, Spring Boot
- **Purpose**: Provides RESTful APIs to manage building entities, tenant entities, and usage data.
- **Entities**: 
  - `BuildingEntity`: Represents building details and associated tenant IDs.
  - `TenantEntity`: Manages tenant information and their usage records.
  - `UsageEntity`: Tracks consumption data (e.g., type, value, accounting month) for tenants.

## Features
- CRUD operations for buildings, tenants, and usage data.
- Relationship management between buildings and tenants.
- Consumption tracking and filtering by month/year.

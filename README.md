# Warehouse Management System (WMS)

![Java](https://img.shields.io/badge/Java-17+-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen)
![Maven](https://img.shields.io/badge/Maven-3.6+-red)
![License](https://img.shields.io/badge/License-MIT-green)

A comprehensive Spring Boot-based warehouse management system that provides scalable REST APIs for managing warehouses, products, inventory items, storage bins, and receiving operations. Built with enterprise-grade architecture patterns and best practices.

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Technology Stack](#technology-stack)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [API Endpoints](#api-endpoints)
- [Database Schema](#database-schema)
- [Architecture](#architecture)
- [Configuration](#configuration)
- [Running the Application](#running-the-application)
- [Testing](#testing)
- [Troubleshooting](#troubleshooting)
- [Performance Optimization](#performance-optimization)
- [Contributing](#contributing)
- [License](#license)
- [Support](#support)

## Overview

The Warehouse Management System (WMS) is a robust, scalable solution for managing warehouse operations. It provides an intuitive REST API interface for:

- Managing multiple warehouse locations
- Organizing products and inventory
- Tracking storage bin utilization
- Processing receiving/shipment operations
- Real-time inventory visibility
- Transactional consistency across operations

This system is designed for small to medium-sized warehouses and can be extended for enterprise deployments.

## Features

### Core Features

- **Multi-Warehouse Support**: Manage unlimited warehouse locations
- **Product Management**: Comprehensive product catalog with SKU tracking
- **Storage Bin Organization**: Organize storage with capacity management
- **Inventory Tracking**: Real-time inventory level tracking and updates
- **Receiving Management**: Process incoming shipments with transactional integrity
- **RESTful API**: Complete REST API with proper HTTP status codes
- **Data Persistence**: JPA/Hibernate with relational database support

### Advanced Features

- **Transactional Operations**: Ensure data consistency across operations
- **Error Handling**: Comprehensive error handling and validation
- **Auditing**: Track creation and update timestamps
- **Scalability**: Designed for horizontal scaling
- **Extensibility**: Modular architecture for easy feature additions

## Technology Stack

### Backend Framework

- **Framework**: Spring Boot 3.x
- **Language**: Java 17 (LTS)
- **Build Tool**: Maven 3.6+

### Data Layer

- **ORM**: Spring Data JPA / Hibernate
- **Databases**:
  - H2 (Development & Testing)
  - MySQL 8.0+ (Production)
  - PostgreSQL 13+ (Alternative Production)

### Additional Libraries

- **Validation**: Jakarta Bean Validation
- **Logging**: SLF4J with Logback
- **Testing**: JUnit 5, Mockito

### Infrastructure

- **Web Server**: Embedded Tomcat
- **REST Protocol**: HTTP/1.1
- **Port**: 8080 (default)

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/wms/
│   │       ├── controller/          # REST Controllers
│   │       │   ├── InventoryItemController.java
│   │       │   ├── ProductController.java
│   │       │   ├── ReceivingController.java
│   │       │   ├── StorageBinController.java
│   │       │   └── WarehouseController.java
│   │       ├── dto/                 # Data Transfer Objects
│   │       │   └── ReceivingRequest.java
│   │       ├── entity/              # JPA Entities
│   │       │   ├── InventoryItem.java
│   │       │   ├── Product.java
│   │       │   ├── StorageBin.java
│   │       │   └── Warehouse.java
│   │       ├── repository/          # Data Access Layer
│   │       │   ├── InventoryItemRepository.java
│   │       │   ├── ProductRepository.java
│   │       │   ├── StorageBinRepository.java
│   │       │   └── WarehouseRepository.java
│   │       ├── service/             # Business Logic
│   │       │   ├── InventoryItemService.java
│   │       │   ├── ProductService.java
│   │       │   ├── ReceivingService.java
│   │       │   ├── StorageBinService.java
│   │       │   └── WarehouseService.java
│   │       └── warehouseMS/
│   │           └── WarehouseMsApplication.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/
        └── com/wms/warehouseMS/
            └── WarehouseMsApplicationTests.java
```

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6+
- Git

### Installation

1. Clone the repository:

```bash
git clone https://github.com/Teja16sree/Warehouse_Management_System.git
cd Warehouse_Management_System
```

2. Build the project:

```bash
./mvnw clean build
```

## API Endpoints

### Warehouse Management

| Method | Endpoint               | Description              |
| ------ | ---------------------- | ------------------------ |
| GET    | `/api/warehouses`      | Retrieve all warehouses  |
| POST   | `/api/warehouses`      | Create a new warehouse   |
| GET    | `/api/warehouses/{id}` | Get warehouse by ID      |
| PUT    | `/api/warehouses/{id}` | Update warehouse details |
| DELETE | `/api/warehouses/{id}` | Delete warehouse         |

**Example Request:**

```bash
curl -X POST http://localhost:8080/api/warehouses \
  -H "Content-Type: application/json" \
  -d '{"name": "Main Warehouse", "location": "New York"}'
```

### Product Management

| Method | Endpoint             | Description            |
| ------ | -------------------- | ---------------------- |
| GET    | `/api/products`      | Retrieve all products  |
| POST   | `/api/products`      | Create a new product   |
| GET    | `/api/products/{id}` | Get product by ID      |
| PUT    | `/api/products/{id}` | Update product details |
| DELETE | `/api/products/{id}` | Delete product         |

**Example Request:**

```bash
curl -X POST http://localhost:8080/api/products \
  -H "Content-Type: application/json" \
  -d '{"name": "Widget A", "sku": "WIDG-A-001", "description": "Premium Widget"}'
```

### Storage Bin Management

| Method | Endpoint                 | Description               |
| ------ | ------------------------ | ------------------------- |
| GET    | `/api/storage-bins`      | Retrieve all storage bins |
| POST   | `/api/storage-bins`      | Create a new storage bin  |
| GET    | `/api/storage-bins/{id}` | Get storage bin by ID     |
| PUT    | `/api/storage-bins/{id}` | Update storage bin        |
| DELETE | `/api/storage-bins/{id}` | Delete storage bin        |

**Example Request:**

```bash
curl -X POST http://localhost:8080/api/storage-bins \
  -H "Content-Type: application/json" \
  -d '{"warehouseId": 1, "binCode": "BIN-A-01", "capacity": 100}'
```

### Inventory Management

| Method | Endpoint                    | Description                  |
| ------ | --------------------------- | ---------------------------- |
| GET    | `/api/inventory-items`      | Retrieve all inventory items |
| POST   | `/api/inventory-items`      | Create inventory item        |
| GET    | `/api/inventory-items/{id}` | Get inventory item by ID     |
| PUT    | `/api/inventory-items/{id}` | Update inventory item        |
| DELETE | `/api/inventory-items/{id}` | Delete inventory item        |

**Example Request:**

```bash
curl -X POST http://localhost:8080/api/inventory-items \
  -H "Content-Type: application/json" \
  -d '{"productId": 1, "storageBinId": 1, "quantity": 50}'
```

### Receiving Operations

| Method | Endpoint         | Description               |
| ------ | ---------------- | ------------------------- |
| POST   | `/api/receiving` | Process incoming shipment |
| GET    | `/api/receiving` | Get all receiving records |

**Example Request:**

```bash
curl -X POST http://localhost:8080/api/receiving \
  -H "Content-Type: application/json" \
  -d '{"warehouseId": 1, "items": [{"productId": 1, "quantity": 100}]}'
```

## Database Schema

### Warehouse Table

| Column      | Type         | Constraints                 | Description                 |
| ----------- | ------------ | --------------------------- | --------------------------- |
| `id`        | BIGINT       | PRIMARY KEY, AUTO_INCREMENT | Unique warehouse identifier |
| `name`      | VARCHAR(255) | NOT NULL                    | Warehouse name              |
| `location`  | VARCHAR(255) | NOT NULL                    | Warehouse location          |
| `createdAt` | TIMESTAMP    | DEFAULT CURRENT_TIMESTAMP   | Creation timestamp          |
| `updatedAt` | TIMESTAMP    | ON UPDATE CURRENT_TIMESTAMP | Last update timestamp       |

### Product Table

| Column        | Type         | Constraints                 | Description               |
| ------------- | ------------ | --------------------------- | ------------------------- |
| `id`          | BIGINT       | PRIMARY KEY, AUTO_INCREMENT | Unique product identifier |
| `name`        | VARCHAR(255) | NOT NULL                    | Product name              |
| `sku`         | VARCHAR(100) | UNIQUE, NOT NULL            | Stock Keeping Unit        |
| `description` | TEXT         |                             | Product description       |
| `createdAt`   | TIMESTAMP    | DEFAULT CURRENT_TIMESTAMP   | Creation timestamp        |
| `updatedAt`   | TIMESTAMP    | ON UPDATE CURRENT_TIMESTAMP | Last update timestamp     |

### StorageBin Table

| Column        | Type         | Constraints                 | Description            |
| ------------- | ------------ | --------------------------- | ---------------------- |
| `id`          | BIGINT       | PRIMARY KEY, AUTO_INCREMENT | Unique bin identifier  |
| `warehouseId` | BIGINT       | FOREIGN KEY                 | Reference to Warehouse |
| `binCode`     | VARCHAR(100) | NOT NULL                    | Bin code/identifier    |
| `capacity`    | INT          | NOT NULL                    | Maximum bin capacity   |
| `createdAt`   | TIMESTAMP    | DEFAULT CURRENT_TIMESTAMP   | Creation timestamp     |
| `updatedAt`   | TIMESTAMP    | ON UPDATE CURRENT_TIMESTAMP | Last update timestamp  |

### InventoryItem Table

| Column         | Type      | Constraints                 | Description                 |
| -------------- | --------- | --------------------------- | --------------------------- |
| `id`           | BIGINT    | PRIMARY KEY, AUTO_INCREMENT | Unique inventory identifier |
| `productId`    | BIGINT    | FOREIGN KEY, NOT NULL       | Reference to Product        |
| `storageBinId` | BIGINT    | FOREIGN KEY, NOT NULL       | Reference to StorageBin     |
| `quantity`     | INT       | NOT NULL, DEFAULT 0         | Current quantity in stock   |
| `createdAt`    | TIMESTAMP | DEFAULT CURRENT_TIMESTAMP   | Creation timestamp          |
| `updatedAt`    | TIMESTAMP | ON UPDATE CURRENT_TIMESTAMP | Last update timestamp       |

### Receiving Table

| Column        | Type        | Constraints                 | Description                |
| ------------- | ----------- | --------------------------- | -------------------------- |
| `id`          | BIGINT      | PRIMARY KEY, AUTO_INCREMENT | Unique receiving record ID |
| `warehouseId` | BIGINT      | FOREIGN KEY, NOT NULL       | Reference to Warehouse     |
| `receivedAt`  | TIMESTAMP   | DEFAULT CURRENT_TIMESTAMP   | Receiving timestamp        |
| `status`      | VARCHAR(50) | DEFAULT 'PENDING'           | Processing status          |
| `createdAt`   | TIMESTAMP   | DEFAULT CURRENT_TIMESTAMP   | Creation timestamp         |
| `updatedAt`   | TIMESTAMP   | ON UPDATE CURRENT_TIMESTAMP | Last update timestamp      |

### Entity Relationships

```
Warehouse (1) ──── (Many) StorageBin
Warehouse (1) ──── (Many) ReceivingRecord
Product (1) ──── (Many) InventoryItem
StorageBin (1) ──── (Many) InventoryItem
```

## Architecture

### Layered Architecture

The application follows a classic three-tier architecture:

```
┌─────────────────────────────────────┐
│        REST Controllers             │  (API Layer)
│  (Handle HTTP requests/responses)   │
└─────────────────────────────────────┘
            ↓↓↓
┌─────────────────────────────────────┐
│         Business Services           │  (Service Layer)
│    (Core business logic)            │
└─────────────────────────────────────┘
            ↓↓↓
┌─────────────────────────────────────┐
│    Spring Data JPA Repositories     │  (Data Access Layer)
│    (Database operations)            │
└─────────────────────────────────────┘
            ↓↓↓
┌─────────────────────────────────────┐
│       Relational Database           │  (Database Layer)
│    (H2/MySQL/PostgreSQL)            │
└─────────────────────────────────────┘
```

### Design Patterns Used

- **Repository Pattern**: Abstraction over data access
- **Service Pattern**: Business logic encapsulation
- **DTO Pattern**: Data transfer between layers
- **Dependency Injection**: Spring IoC container
- **Transaction Management**: Spring's @Transactional

## Configuration

### Environment Variables

Set these environment variables for production deployments:

```bash
export SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/warehouse_db
export SPRING_DATASOURCE_USERNAME=root
export SPRING_DATASOURCE_PASSWORD=password
export SPRING_JPA_HIBERNATE_DDL_AUTO=validate
export SERVER_PORT=8080
```

### Application Properties

Edit `src/main/resources/application.properties`:

```properties
# Application Configuration
spring.application.name=warehouseMS
server.port=8080
server.servlet.context-path=/

# Database Configuration (Development)
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

# JPA/Hibernate Configuration
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.format_sql=true

# H2 Console (Development only)
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# Logging Configuration
logging.level.root=INFO
logging.level.com.wms=DEBUG
```

### Production Configuration

For MySQL/PostgreSQL production deployments:

```properties
# MySQL Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/warehouse_db?useSSL=false&serverTimezone=UTC
spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=validate

# PostgreSQL Configuration (Alternative)
# spring.datasource.url=jdbc:postgresql://localhost:5432/warehouse_db
# spring.datasource.driverClassName=org.postgresql.Driver
# spring.jpa.database-platform=org.hibernate.dialect.PostgreSQL13Dialect
```

## Running the Application

### Prerequisites Check

```bash
# Check Java version (should be 17+)
java -version

# Check Maven version (should be 3.6+)
mvn -version
```

### Build the Project

```bash
# Clean and compile
./mvnw clean package

# Skip tests during build
./mvnw clean package -DskipTests
```

### Run with Maven

```bash
# Start the application
./mvnw spring-boot:run

# With specific profile
./mvnw spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=dev"
```

### Run from JAR File

```bash
# Build the JAR
./mvnw clean package

# Run the JAR
java -jar target/warehouseMS-1.0.0.jar

# Run with environment variables
java -Dspring.datasource.url=jdbc:mysql://localhost:3306/warehouse \
     -Dspring.datasource.username=root \
     -jar target/warehouseMS-1.0.0.jar
```

### Verify Application is Running

```bash
# Check if server is listening on port 8080
curl http://localhost:8080/api/warehouses

# Check H2 Console (if enabled)
curl http://localhost:8080/h2-console
```

The application will start on `http://localhost:8080`

## Testing

### Run All Tests

```bash
./mvnw test
```

### Run Specific Test Class

```bash
./mvnw test -Dtest=WarehouseMsApplicationTests
```

### Run Tests with Coverage

```bash
./mvnw test jacoco:report
# Coverage report will be in: target/site/jacoco/index.html
```

### Test Types Included

- **Unit Tests**: Individual component testing
- **Integration Tests**: Multi-layer functionality testing
- **Smoke Tests**: Basic application startup verification

## Troubleshooting

### Common Issues

#### 1. Port Already in Use

```bash
# Change the port in application.properties
server.port=8081

# Or via command line
./mvnw spring-boot:run -Dspring-boot.run.arguments="--server.port=8081"
```

#### 2. Database Connection Error

```bash
# Verify database URL and credentials
# Check if database server is running (for MySQL/PostgreSQL)
mysql -h localhost -u root -p

# Use H2 console for in-memory database
http://localhost:8080/h2-console
```

#### 3. Build Failures

```bash
# Clear Maven cache
rm -rf ~/.m2/repository

# Rebuild
./mvnw clean install
```

#### 4. Out of Memory Error

```bash
# Increase JVM memory
export MAVEN_OPTS="-Xmx1024m"
./mvnw spring-boot:run
```

## Performance Optimization

### Database Optimization

- **Indexing**: Add indexes on frequently queried columns
- **Connection Pooling**: Configure HikariCP for better connection management
- **Lazy Loading**: Use lazy loading for related entities

### Application Optimization

- **Caching**: Implement Redis caching for frequently accessed data
- **Pagination**: Use pagination for large result sets
- **Query Optimization**: Use custom JPQL queries for complex operations

### Monitoring

```bash
# Enable actuator endpoints (add to pom.xml)
# Spring Boot Actuator provides metrics at /actuator
curl http://localhost:8080/actuator/metrics
```

## Contributing

We welcome contributions to improve the Warehouse Management System! Here's how you can help:

### Getting Started

1. Fork the repository
2. Clone your fork: `git clone https://github.com/YOUR_USERNAME/Warehouse_Management_System.git`
3. Create a feature branch: `git checkout -b feature/AmazingFeature`
4. Make your changes
5. Commit your changes: `git commit -m 'Add some AmazingFeature'`
6. Push to your branch: `git push origin feature/AmazingFeature`
7. Open a Pull Request

### Code Guidelines

- Follow Java naming conventions
- Write meaningful commit messages
- Add tests for new features
- Update README if you add new features
- Ensure all tests pass before submitting PR

### Reporting Issues

- Check existing issues first
- Provide detailed description
- Include steps to reproduce
- Attach relevant logs if available

### Areas for Contribution

- Bug fixes
- New features
- Documentation improvements
- Test coverage enhancement
- Performance optimizations
- Security improvements

## License

This project is open source and available under the **MIT License**.

### MIT License Terms

- Free for personal and commercial use
- You can modify and distribute the code
- Must include license notice and copyright statement
- Provided as-is without warranty

See [LICENSE](LICENSE) file for more details.

## Support & Contact

### Getting Help

- 📧 Email: sindhuluritejasree@gmail.com
- 🐛 Bug Reports: Open an issue on GitHub
- 💬 Questions: Create a discussion thread

### Resources

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Data JPA Guide](https://spring.io/projects/spring-data-jpa)
- [Hibernate ORM](https://hibernate.org/)
- [Maven Documentation](https://maven.apache.org/)

## Roadmap

### Future Features

- [ ] Authentication & Authorization (JWT)
- [ ] Multi-warehouse analytics dashboard
- [ ] Barcode/QR code integration
- [ ] Mobile app support
- [ ] Real-time notifications
- [ ] Advanced reporting features
- [ ] Batch operations support
- [ ] Data export/import functionality

## Contributors

Special thanks to all contributors who have helped with:

- Code improvements
- Bug fixes
- Documentation
- Feature suggestions

## Changelog

### Version 1.0.0 (Current)

- Initial release with core WMS functionality
- Complete REST API implementation
- Database schema with proper relationships
- Transaction support for receiving operations
- Comprehensive test coverage

---

**Last Updated**: June 2, 2026

**Repository**: [Teja16sree/Warehouse_Management_System](https://github.com/Teja16sree/Warehouse_Management_System)

**Project Status**: Active Development ✅

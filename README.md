# Warehouse Management System (WMS)

A Spring Boot-based warehouse management system that provides comprehensive REST APIs for managing warehouses, products, inventory items, storage bins, and receiving operations.

## Table of Contents

- [Features](#features)
- [Technology Stack](#technology-stack)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [API Endpoints](#api-endpoints)
- [Database Schema](#database-schema)
- [Configuration](#configuration)
- [Running the Application](#running-the-application)
- [Testing](#testing)

## Features

- **Warehouse Management**: Create and manage multiple warehouses
- **Product Management**: Add and manage products in the inventory
- **Storage Bin Management**: Organize products in storage bins within warehouses
- **Inventory Management**: Track inventory items and stock levels
- **Receiving Management**: Process incoming shipments with transactional logic
- **RESTful API**: Complete REST API for all operations
- **Database Persistence**: JPA/Hibernate with relational database support

## Technology Stack

- **Framework**: Spring Boot 3.x
- **Language**: Java 17+
- **Build Tool**: Maven
- **Database**: H2 (Development), MySQL/PostgreSQL (Production)
- **ORM**: Spring Data JPA / Hibernate
- **API Documentation**: REST Endpoints

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

- `GET /api/warehouses` - Get all warehouses
- `POST /api/warehouses` - Create a new warehouse
- `GET /api/warehouses/{id}` - Get warehouse by ID
- `PUT /api/warehouses/{id}` - Update warehouse
- `DELETE /api/warehouses/{id}` - Delete warehouse

### Product Management

- `GET /api/products` - Get all products
- `POST /api/products` - Create a new product
- `GET /api/products/{id}` - Get product by ID
- `PUT /api/products/{id}` - Update product
- `DELETE /api/products/{id}` - Delete product

### Storage Bin Management

- `GET /api/storage-bins` - Get all storage bins
- `POST /api/storage-bins` - Create a new storage bin
- `GET /api/storage-bins/{id}` - Get storage bin by ID
- `PUT /api/storage-bins/{id}` - Update storage bin
- `DELETE /api/storage-bins/{id}` - Delete storage bin

### Inventory Management

- `GET /api/inventory-items` - Get all inventory items
- `POST /api/inventory-items` - Create inventory item
- `GET /api/inventory-items/{id}` - Get inventory item by ID
- `PUT /api/inventory-items/{id}` - Update inventory item
- `DELETE /api/inventory-items/{id}` - Delete inventory item

### Receiving Operations

- `POST /api/receiving` - Process incoming shipment
- `GET /api/receiving` - Get all receiving records

## Database Schema

### Warehouse

- `id` (Primary Key)
- `name` (String)
- `location` (String)
- Timestamps (createdAt, updatedAt)

### Product

- `id` (Primary Key)
- `name` (String)
- `sku` (String)
- `description` (Text)
- Timestamps (createdAt, updatedAt)

### StorageBin

- `id` (Primary Key)
- `warehouseId` (Foreign Key)
- `binCode` (String)
- `capacity` (Integer)
- Timestamps (createdAt, updatedAt)

### InventoryItem

- `id` (Primary Key)
- `productId` (Foreign Key)
- `storageBinId` (Foreign Key)
- `quantity` (Integer)
- Timestamps (createdAt, updatedAt)

## Configuration

Edit `src/main/resources/application.properties` to configure:

```properties
spring.application.name=warehouseMS
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
server.port=8080
```

## Running the Application

### Using Maven

```bash
./mvnw spring-boot:run
```

### Using Java

```bash
java -jar target/warehouseMS-1.0.0.jar
```

The application will start on `http://localhost:8080`

## Testing

Run the test suite:

```bash
./mvnw test
```

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is open source and available under the MIT License.

## Support

For support, email support@warehouse-ms.com or open an issue on the GitHub repository.

---

**Last Updated**: June 2, 2026

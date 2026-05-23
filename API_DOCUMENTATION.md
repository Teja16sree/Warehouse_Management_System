# Warehouse Management System - API Documentation

## Overview

This is a Spring Boot REST API for managing a warehouse with products, storage bins, and inventory.

## Base URL

`http://localhost:8080`

## Database Configuration

**Database:** MySQL (wms_db)  
**Username:** root  
**Password:** admin@123  
**Host:** localhost:3306

## API Endpoints

### 1. Products API

Base URL: `/products`

#### Create Product

- **POST** `/products`
- **Request Body:**

```json
{
  "sku": "PROD001",
  "name": "Product Name",
  "category": "Electronics"
}
```

- **Response:** Product object with generated ID

#### Get All Products

- **GET** `/products`
- **Response:** Array of all products

#### Get Product by ID

- **GET** `/products/{id}`
- **Response:** Single product object

#### Update Product

- **PUT** `/products/{id}`
- **Request Body:**

```json
{
  "sku": "PROD001-UPDATE",
  "name": "Updated Product Name",
  "category": "Updated Category"
}
```

- **Response:** Updated product object

#### Delete Product

- **DELETE** `/products/{id}`
- **Response:** Success message

---

### 2. Warehouses API

Base URL: `/warehouses`

#### Create Warehouse

- **POST** `/warehouses`
- **Request Body:**

```json
{
  "warehouseName": "Main Warehouse",
  "location": "New York"
}
```

- **Response:** Warehouse object with generated ID

#### Get All Warehouses

- **GET** `/warehouses`
- **Response:** Array of all warehouses

#### Get Warehouse by ID

- **GET** `/warehouses/{id}`
- **Response:** Single warehouse object

#### Update Warehouse

- **PUT** `/warehouses/{id}`
- **Request Body:**

```json
{
  "warehouseName": "Updated Warehouse",
  "location": "Updated Location"
}
```

- **Response:** Updated warehouse object

#### Delete Warehouse

- **DELETE** `/warehouses/{id}`
- **Response:** Success message

---

### 3. Storage Bins API

Base URL: `/storage-bins`

#### Create Storage Bin

- **POST** `/storage-bins`
- **Request Body:**

```json
{
  "binCode": "BIN-A1",
  "storageType": "Standard",
  "capacity": 100,
  "warehouse": {
    "id": 1
  }
}
```

- **Response:** Storage bin object with generated ID

#### Get All Storage Bins

- **GET** `/storage-bins`
- **Response:** Array of all storage bins

#### Get Storage Bin by ID

- **GET** `/storage-bins/{id}`
- **Response:** Single storage bin object

#### Update Storage Bin

- **PUT** `/storage-bins/{id}`
- **Request Body:**

```json
{
  "binCode": "BIN-A1-UPDATE",
  "storageType": "Premium",
  "capacity": 150
}
```

- **Response:** Updated storage bin object

#### Delete Storage Bin

- **DELETE** `/storage-bins/{id}`
- **Response:** Success message

---

### 4. Inventory Items API

Base URL: `/inventory-items`

#### Create Inventory Item

- **POST** `/inventory-items`
- **Request Body:**

```json
{
  "quantity": 50,
  "product": {
    "id": 1
  },
  "storageBin": {
    "id": 1
  }
}
```

- **Response:** Inventory item object with generated ID

#### Get All Inventory Items

- **GET** `/inventory-items`
- **Response:** Array of all inventory items

#### Get Inventory Item by ID

- **GET** `/inventory-items/{id}`
- **Response:** Single inventory item object

#### Update Inventory Item

- **PUT** `/inventory-items/{id}`
- **Request Body:**

```json
{
  "quantity": 75,
  "product": {
    "id": 1
  },
  "storageBin": {
    "id": 1
  }
}
```

- **Response:** Updated inventory item object

#### Add Quantity to Inventory

- **POST** `/inventory-items/{id}/add-quantity?quantity=10`
- **Response:** Updated inventory item object

#### Remove Quantity from Inventory

- **POST** `/inventory-items/{id}/remove-quantity?quantity=5`
- **Response:** Updated inventory item object

#### Delete Inventory Item

- **DELETE** `/inventory-items/{id}`
- **Response:** Success message

---

## Running the Application

### Prerequisites

- Java 17 or higher
- MySQL Server running
- Maven (or use Maven Wrapper)

### Steps

1. Navigate to project directory:

```bash
cd c:\Users\sindh\Downloads\warehouseMS\warehouseMS
```

2. Build the project:

```bash
.\mvnw.cmd clean package
```

3. Run the application:

```bash
.\mvnw.cmd spring-boot:run
```

4. The application will start on `http://localhost:8080`

---

## Database Tables

### products

- id (Primary Key)
- sku (Unique)
- name
- category

### warehouses

- id (Primary Key)
- warehouse_name
- location

### storage_bins

- id (Primary Key)
- bin_code (Unique)
- storage_type
- capacity
- warehouse_id (Foreign Key)

### inventory_items

- id (Primary Key)
- quantity
- product_id (Foreign Key)
- storage_bin_id (Foreign Key)

---

## Error Handling

All endpoints return appropriate HTTP status codes:

- **200 OK** - Successful GET, PUT request
- **201 CREATED** - Successful POST request
- **400 BAD REQUEST** - Invalid input data
- **404 NOT FOUND** - Resource not found
- **500 INTERNAL SERVER ERROR** - Server error

---

## Project Structure

```
src/main/java/com/wms/
├── controller/
│   ├── ProductController.java
│   ├── WarehouseController.java
│   ├── StorageBinController.java
│   └── InventoryItemController.java
├── entity/
│   ├── Product.java
│   ├── Warehouse.java
│   ├── StorageBin.java
│   └── InventoryItem.java
├── repository/
│   ├── ProductRepository.java
│   ├── WarehouseRepository.java
│   ├── StorageBinRepository.java
│   └── InventoryItemRepository.java
├── service/
│   ├── ProductService.java
│   ├── WarehouseService.java
│   ├── StorageBinService.java
│   └── InventoryItemService.java
└── warehouseMS/
    └── WarehouseMsApplication.java
```

---

## Technologies Used

- Spring Boot 3.5.15-SNAPSHOT
- Spring Data JPA
- MySQL Database
- Lombok (for code generation)
- Maven Build Tool
- Java 17

---

## Notes

- All API responses are in JSON format
- Database automatically creates/updates tables on application startup
- Validation is performed on all input data
- Relationships are properly maintained between entities

# ECommerceAPI - A Spring Boot REST API

This project is the complete backend for a modern e-commerce platform, built using Spring Boot. It provides a comprehensive RESTful API to manage products, users, shopping carts, and orders. The application is designed with a professional 3-tier architecture (Controller, Service, Repository) to ensure separation of concerns, scalability, and maintainability.

## Core Features
* **Product Management**: Full CRUD (Create, Read, Update, Delete) functionality for products.
* **User Management**: Endpoints for user registration and profile management.
* **Shopping Cart**: Functionality to add/remove products from a user's cart.
* **Order Placement**: Logic to convert a shopping cart into a permanent order, establishing relationships between users, products, and orders.

## Technologies Used
* **Framework**: Spring Boot 3
* **Language**: Java 21
* **Data Persistence**: Spring Data JPA & Hibernate
* **Database**: MySQL
* **Build Tool**: Maven

## Project Setup and Running

### 1. Prerequisites
* Java 17 or later installed.
* Maven installed.
* MySQL Server running on your local machine.

### 2. Clone the Repository
```bash
git clone https://github.com/azmisaud/ECommerceAPI-SpringBoot.git
cd ECommerceAPI-SpringBoot
```

### 3. Configure the Database
This project uses MySQL. You must provide your database password for the application to connect successfully. It is highly recommended to use environment variables for security.

1.  Open `src/main/resources/application.properties`.
2.  Ensure the configuration points to your local MySQL instance and reads the password from an environment variable. The database `ecommerce_db` will be created automatically if it doesn't exist.
    ```properties
    # MySQL Database Configuration
    spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce_db?createDatabaseIfNotExist=true
    spring.datasource.username=root
    spring.datasource.password=${DB_PASSWORD}

    # JPA/Hibernate Configuration
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
    ```
3.  Set the `DB_PASSWORD` environment variable in your terminal before running the app.
    * **macOS/Linux:**
        ```bash
        export DB_PASSWORD="your_mysql_password"
        ```
    * **Windows (CMD):**
        ```bash
        set DB_PASSWORD=your_mysql_password
        ```

### 4. Run the Application
You can run the application using the included Maven wrapper. This will download the correct Maven version and start the server.
```bash
./mvnw spring-boot:run
```
The API will be running on `http://localhost:8080`.

## API Endpoints Reference

This section details all planned API endpoints for the application.

### Product Management
* **URL Base:** `/api/products`

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `GET` | `/` | Retrieves a list of all products. |
| `GET` | `/{id}` | Retrieves a single product by its unique ID. |
| `POST` | `/` | Creates a new product. |
| `PUT` | `/{id}` | Updates an existing product by its ID. |
| `DELETE` | `/{id}` | Deletes a product by its ID. |

### User Management
* **URL Base:** `/api/users`

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/register` | Creates a new user account. |
| `GET` | `/{id}` | Retrieves a user's profile by their ID. |
| `PUT` | `/{id}` | Updates a user's profile information. |

### Shopping Cart & Orders
* **URL Base:** `/api/cart` and `/api/orders`

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/cart/{userId}/add` | Adds a product to a specific user's shopping cart. |
| `GET` | `/cart/{userId}` | Retrieves the contents of a user's shopping cart. |
| `DELETE`| `/cart/{userId}/remove`| Removes a product from a user's shopping cart. |
| `POST` | `/orders/{userId}/checkout`| Converts a user's cart into a permanent order. |
| `GET` | `/orders/{userId}` | Retrieves a list of all past orders for a user. |

## Testing with Postman
You can test all endpoints using a client like Postman. The following is an example for the **Product API**.

### Create a Product
* **Method**: `POST`
* **URL**: `http://localhost:8080/api/products`
* **Body**: Select `raw` and `JSON`.
    ```json
    {
        "name": "Wireless Keyboard",
        "description": "A mechanical keyboard with RGB lighting.",
        "price": 79.99
    }
    ```
* **Response**: `200 OK` with the created product object, including its new `id`.

# Order and Stock Management API (api-estoque)

This project is a RESTful API built with Java and Spring Boot, designed to simulate the backend logic of an e-commerce or ERP system. It serves as a portfolio project to demonstrate key backend concepts, complex business logic, and database transactional integrity.

This API was developed as a practical exercise for a professional transitioning from a 5-year career in Technical Support (with a focus on ERP systems and SQL) to a Backend Developer / SDET role.

---

## 🚀 Key Features

* **Full CRUD Operations:** Endpoints for managing `Products` and `Customers`.
* **Complex Business Logic:** A robust order creation process.
* **Transactional Integrity:** Uses `@Transactional` to ensure that an order is only created if all conditions are met (e.g., stock availability). If any part fails, the entire operation is rolled back, preventing data corruption.
* **Real-Time Stock Management:** Automatically deducts product quantity from stock upon a successful order.
* **Custom Exception Handling:** Graceful handling of business rule violations (`InsufficientStockException` - 400 Bad Request) and missing data (`ResourceNotFoundException` - 404 Not Found).
* **Containerized Environment:** A `docker-compose.yml` file is included to instantly provision a `PostgreSQL` database, ensuring a consistent and isolated development environment.

---

## 🛠️ Technology Stack

* **Java 17**
* **Spring Boot 3.x**
    * Spring Web (RESTful APIs)
    * Spring Data JPA (Database access)
* **PostgreSQL** (Database)
* **Docker & Docker Compose** (For database containerization)
* **Maven** (Dependency Management)
* **Lombok** (Boilerplate code reduction)
* **Postman** (Used for API testing)

---

## 🚦 How to Run Locally

### Prerequisites

* Java 17 (JDK)
* Apache Maven
* Docker Desktop (running)

### Steps

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/lucas-ribeiro-silva/api-estoque.git](https://github.com/lucas-ribeiro-silva/api-estoque.git)
    cd api-estoque
    ```

2.  **Start the Database Container:**
    (This will read the `docker-compose.yml` and start the PostgreSQL database in the background)
    ```bash
    docker-compose up -d
    ```

3.  **Run the Spring Boot Application:**
    (Maven will build the project and start the server on `http://localhost:8080`)
    ```bash
    mvn spring-boot:run
    ```
    The API is now running and connected to the database.

---

## 📦 API Endpoints

You can test these endpoints using Postman.

### Products (`/api/products`)

| Method | Endpoint          | Description                 |
| :----- | :---------------- | :-------------------------- |
| `POST` | `/api/products`   | Creates a new product.      |
| `GET`  | `/api/products`   | Lists all products.         |
| `GET`  | `/api/products/{id}` | Gets a specific product.    |
| `PUT`  | `/api/products/{id}` | Updates a specific product. |
| `DELETE` | `/api/products/{id}` | Deletes a specific product. |

### Customers (`/api/customers`)

| Method | Endpoint            | Description                   |
| :----- | :------------------ | :---------------------------- |
| `POST` | `/api/customers`    | Creates a new customer.       |
| `GET`  | `/api/customers`    | Lists all customers.          |
| `GET`  | `/api/customers/{id}`  | Gets a specific customer.     |
| `PUT`  | `/api/customers/{id}`  | Updates a specific customer.  |
| `DELETE` | `/api/customers/{id}`  | Deletes a specific customer.  |

### Orders (`/api/orders`)

| Method | Endpoint        | Description                                                                 |
| :----- | :-------------- | :-------------------------------------------------------------------------- |
| `POST` | `/api/orders`   | Creates a new order (and validates stock).                                  |
| `GET`  | `/api/orders/{id}` | Gets details of a specific order (including customer and items).          |

#### Example: `POST /api/orders` Request Body

```json
{
    "customerId": 1,
    "items": [
        {
            "productId": 1,
            "quantity": 10
        },
        {
            "productId": 2,
            "quantity": 5
        }
    ]
}
```
## 👨‍💻 Author

* **Lucas Ribeiro Silva**
* [LinkedIn](https://linkedin.com/in/dev-lucasribeirosilva)
* [GitHub](https://github.com/lucas-ribeiro-silva)
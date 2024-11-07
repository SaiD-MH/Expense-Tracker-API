# Expense Tracker API

A simple and efficient **Expense Tracker API** that allows users to manage and track their expenses. This API enables users to create, update, delete, and fetch expenses with powerful features like filtering by date ranges and generating reports in CSV format. The API uses **Spring Boot** and **MongoDB** for backend operations and can be easily extended or integrated with other systems.

## Table of Contents

- [Features](#features)
- [Tech Stack](#tech-stack)
- [Installation](#installation)
- [API Endpoints](#api-endpoints)
- [How to Use](#how-to-use)
- [Contributing](#contributing)

## Features

- **User Authentication:** Secure user management (with login and registration functionality).
- **Create and Manage Expenses:** Add, update, and delete expense entries.
- **Filtering:** Filter expenses by date ranges (last X days, last X months, or custom date range).
- **Generate Reports:** Generate CSV reports for expenses without any filters.
- **MongoDB Storage:** All expenses are stored in a MongoDB database.
- **Auto-increment Supporting**: Supported auto-increment feature to mongodb insertion using collection counter strategy

## Tech Stack

- **Backend Framework:** Spring Boot
- **Database:** MongoDB (NoSQL)
- **CSV Library:** OpenCSV for CSV file generation.
- **Java Version:** Java 11 or later
- **IDE:** Intellij

## Installation

Follow these steps to run the project locally:

1. **Clone the repository:**

   ```bash
   git clone https://github.com/SaiD-MH/Expense-Tracker-API
   ```

2. **Navigate to the project directory:**

   ```bash
   cd Expense-Tracker-API
   ```

3. **Configure MongoDB:**

   Ensure MongoDB is installed and running. You can use a local MongoDB instance or a cloud service like MongoDB Atlas. Set up the application.properties file to configure MongoDB connection:

   ```java
   spring.data.mongodb.uri=mongodb://localhost:27017/expense-tracker
   ```

4. **Build and run the application:**

   ```bash
   ./mvnw spring-boot:run
   ```

5. The application will be running on `http://localhost:8080.`

## API Endpoints

Here are the available endpoints for the Expense Tracker API:

### 1. Create a New Expense

- **URL :** `/api/expenses`
- **Method :** POST
- **Request Body :**

```json
{
  "userId": 1,
  "amount": 150.75,
  "createdAt": "2024-11-06",
  "category": "FOOD",
  "description": "Lunch"
}
```

- **Response :** 201 Created, Expense data.

### 2. Get All Expenses

- **URL**: `/api/expenses/{userId}`
- **Method:** GET
- **Query Parameters:**
  - **filterBy (required):** 'days', 'months', or 'custom' for date filtering.
  - **days (optional):** number of days for filtering.
  - **months (optional):** number of months for filtering.
  - **startDate, endDate (optional):** for custom date range.
- **Example Request:**

  ```bash
  GET /api/expenses/1?filterBy=days&days=7
  ```

* **Response:** JSON array of expenses.

### 3. Generate Expense Report as CSV

- **URL:** /api/expenses/report/{userId}
- **Method:** GET
- **Response:** A downloadable CSV file with all expenses for the user.

### 4. Update an Expense

- **URL:** /api/expenses/{expenseId}
- **Method:** PUT
- **Response:** 200 OK.

### 5. Delete an Expense

- **URL:** /api/expenses/{expenseId}
- **Method:** DELETE
- **Response:** 200 OK.

## How to Use

- **Register and log in:** Create a user and log in (add user authentication and JWT if needed).
- **Create expenses:** Use the /expenses endpoint to add expenses.
- **Filter expenses:** Use the filterBy parameter to filter expenses by date range.
- **Generate Reports:** Use the /expenses/report/{userId} endpoint to get a CSV report.

- Example

  - **Create a new expense:**

  - POST /api/expenses
  - Request body:

  ```json
  {
    "userId": 1,
    "amount": 100.5,
    "createdAt": "2024-11-06T15:30:00",
    "category": "FOOD",
    "description": "Dinner"
  }
  ```

  - Get all expenses of a user:

    `GET /api/expenses/1?filterBy=days&days=7`

  - Generate expense report for a user in CSV format:

    `GET /expenses/report/1`

## Contributing

- Fork the repository.
- Clone your fork to your local machine.
- Create a new branch.
- Make your changes.
- Write tests for your changes.
- Commit your changes and push them to your fork.
- Open a pull request.

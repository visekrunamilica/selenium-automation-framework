# Selenium Automation Framework

A UI test automation framework built with Java, Selenium WebDriver, TestNG and Maven using the Page Object Model (POM).

The project is created as a learning and portfolio project with a focus on clean architecture, maintainability and
reusable test components.

---

## Tech Stack

- Java
- Selenium WebDriver
- TestNG
- Maven
- Page Object Model (POM)
- Logback / SLF4J
- Git & GitHub

---

## Features

- Page Object Model architecture
- Explicit waits
- DriverFactory
- Centralized BasePage
- Data Providers
- Test Data Factories
- Config & Test Data Readers
- Screenshot capture on test failure
- TestNG Listener
- Logging
- Reusable models
- Clean test structure

---

## Test Coverage

### Login

- Successful login
- Invalid credentials
- Locked user
- Missing username

### Products

- Add product to cart
- Verify product information

### Cart

- Verify product in cart
- Remove product
- Empty cart validation

### Checkout

- Successful checkout
- Validation of required fields

---

## Running Tests

```bash
mvn clean test
```

---

## Future Improvements

- Allure Report
- GitHub Actions CI
- Cross-browser execution
- Parallel execution
- API tests with REST Assured

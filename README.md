# Saucedemo UI Tests

## 📌 Overview

This repository contains UI automation tests for [saucedemo.com](https://www.saucedemo.com/) — a demo e-commerce site used for testing practice.

## 🛠 Tech Stack

| Technology     | Version  | Purpose                            |
|----------------|----------|------------------------------------|
| Java           | 21       | Programming language               |
| Gradle         | 9.0      | Build tool & dependency management |
| JUnit 5        | 5.10.0   | Test framework                     |
| Selenium       | 4.33.0   | Browser automation                 |
| Allure         | 2.27.0   | Test reporting                     |
| AspectJ        | 1.9.22.1 | Allure JUnit5 integration          |
| Spotless       | 6.25.0   | Code formatting (Google Java Format)|
| GitHub Actions | latest   | CI pipeline                        |

---

## ✅ Test Coverage

The test suite (`SauceDemoTest`) covers 4 requirements -> each verified as a separate step:

| # | Requirement | Verified by |
|---|-------------|-------------|
| 1 | Ability to login | `login()` — navigates to inventory page |
| 2 | Products page has 6 products | `verifyProductCount()` — asserts count equals 6 |
| 3 | Ability to add the product with the highest price to the cart | `addMostExpensiveToCart()` — finds max price, clicks Add to Cart |
| 4 | Product is successfully added to the cart | `verifyCart()` — opens cart, asserts product name is present |

---

## 📁 Project Structure

```
saucedemo/
├── src/test/java/com/saucedemo/
│   ├── config/        # TestConfig — URL, credentials
│   ├── driver/        # DriverFactory — WebDriver setup
│   ├── pages/         # Page Object Model (Login, Products, Cart)
│   ├── steps/         # UISteps — test logic & assertions
│   └── tests/         # BaseTest, SauceDemoTest
├── .github/workflows/
│   └── ci.yml         # Auto on push — Build + Test + Report
├── build.gradle.kts
└── gradlew
```

---

## 🎯 Run Locally

### Prerequisites

```bash
git clone https://github.com/dimeonsports/saucedemo.git
cd saucedemo
```

### Run tests

```bash
# Chrome (default)
./gradlew testChrome

# Firefox
./gradlew testFirefox

# Headless mode
./gradlew testChrome -Pheadless=true
./gradlew testFirefox -Pheadless=true

# All browsers (Chrome + Firefox)
./gradlew clean testAllBrowsers
```

### View Allure report

```bash
allure serve build/allure-results
```

---

## 🚀 Run on GitHub Actions

One workflow runs automatically on every push or pull request to `master`:

| Workflow | Trigger             | What runs                    |
|----------|---------------------|------------------------------|
| `CI`     | Push / PR to master | Build → Test → Allure Report |

Tests run on **Chrome** and **Firefox** in headless mode.

### View Allure report (GitHub Pages)

After the workflow completes, open the **Report** job in GitHub Actions — the direct link to the Allure report is printed at the bottom of the job summary.

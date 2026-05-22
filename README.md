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
git clone https://github.com/YOUR_USERNAME/saucedemo.git
cd saucedemo
```

### Run tests

```bash
# Chrome (default)
./gradlew test

# Firefox
./gradlew test -Dbrowser=firefox

# Headless mode
./gradlew test -Pheadless=true

# All browsers (Chrome + Firefox)
./gradlew testAllBrowsers
```

### View Allure report

```bash
allure serve build/allure-results
```

---

## 🚀 Run on GitHub Actions

One workflow runs automatically on every push or pull request to `main`:

| Workflow | Trigger           | What runs                     |
|----------|-------------------|-------------------------------|
| `CI`     | Push / PR to main | Build → Test → Allure Report  |

Tests run on **Chrome** and **Firefox** in headless mode.

### View Allure report (GitHub Pages)

After the workflow completes, the Allure report is published to:

**https://YOUR_USERNAME.github.io/saucedemo/**

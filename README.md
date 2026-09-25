# Selenium Cucumber POM

Web UI automation framework in Java 17 combining Selenium WebDriver 4, Cucumber 7 (BDD) and the Page Object Model, with centralised locators, shadow DOM handling and Cucumber HTML reports.

![Java](https://img.shields.io/badge/Java-17-ED8B00?logo=openjdk&logoColor=white)
![Selenium](https://img.shields.io/badge/Selenium-4.29-43B02A?logo=selenium&logoColor=white)
![Cucumber](https://img.shields.io/badge/Cucumber-7-23D96C?logo=cucumber&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-3.x-C71A36?logo=apachemaven&logoColor=white)

## What it tests

Written against the staging environment of a cloud accounting web application built with custom web components (shadow DOM). The target URL and credentials are injected at runtime; nothing environment-specific is committed.

| Feature | Scenario | Status |
|---|---|---|
| `Login.feature` | User logs in with valid credentials and lands on the home page (user name is verified) | Implemented |
| `Register.feature` | From the home page open "Create > Customer" and fill in basic data and contact information; submit the form and check the success message | Partially implemented: the last two steps are `PendingException` placeholders |

## Architecture

- **Page Objects** (`pages/`): `LoginPage`, `HomePage` and `CreateCostumerPage` expose user actions; locators live separately in `locators/` as `@FindBy` fields initialised with `PageFactory`.
- **Shadow DOM**: page objects traverse `getShadowRoot()` on custom elements to reach inputs, dropdowns and buttons that regular selectors cannot see.
- **Driver layer** (`drivers/`): `BasesClassDriver` resolves the environment (`-Denvironment=qa` against `config.properties`, overridable with `BASE_URL`) and the browser (`-Dbrowser=chrome`); `MyChromeDriver` builds the `ChromeOptions` (headless with `HEADLESS=true`).
- **Dependency injection**: Cucumber PicoContainer shares a single `Context` (driver and session) across step definition classes.
- **Test data and utilities**: `LoginData` reads credentials from the environment; `RandomFunctions` generates emails, numbers and text; `Waits` wraps explicit waits.

## How to run

Requires JDK 17, Maven 3.x and Google Chrome (the driver is resolved by Selenium Manager).

Configuration comes from environment variables (see `.env.example`): `BASE_URL`, `APP_USERNAME`, `APP_PASSWORD` and the optional `HEADLESS`. The suite fails fast with a clear message if credentials are missing.

```bash
git clone https://github.com/criguex/selenium-cucumber-pom.git
cd selenium-cucumber-pom

export BASE_URL="https://your-app-under-test.example.com/#/login"
export APP_USERNAME="your.user@example.com"
export APP_PASSWORD="your-password"
export HEADLESS=true                        # optional, default false

mvn clean test                              # both runners
mvn clean test -Dtest=RunnerLogin           # single flow
mvn clean verify                            # tests + aggregated HTML report
```

## Project structure

```
.
├── .env.example
├── config.properties
├── pom.xml
└── src/
    ├── main/java/
    │   ├── drivers/      BasesClassDriver, MyChromeDriver
    │   ├── hooks/        Context (shared through PicoContainer)
    │   ├── locators/     LoginLocators, HomeLocators, CreateCostumerLocators
    │   ├── pages/        LoginPage, HomePage, CreateCostumerPage
    │   ├── test_data/    LoginData
    │   └── utils/        Waits, RandomFunctions
    └── test/
        ├── java/
        │   ├── runners/          RunnerLogin, RunnerRegister
        │   └── stepdefinitions/  login/, register/
        └── resources/features/   Login.feature, Register.feature
```

## Reporting

- Cucumber `pretty` console output, HTML report at `target/cucumber-reports/report.html`, JSON at `target/cucumber.json` and JUnit XML at `target/cucumber-reports/cucumber.xml`.
- `mvn verify` runs the `maven-cucumber-reporting` plugin and writes an aggregated report to `target/cucumber-html-reports/`.

## CI

No workflow is configured: the suite targets a private staging environment that needs credentials and a browser, so it runs locally or from a pipeline that injects `BASE_URL`, `APP_USERNAME` and `APP_PASSWORD` as secrets.

---

Cristian Guerra · Senior SDET · [linkedin.com/in/criguex](https://www.linkedin.com/in/criguex)

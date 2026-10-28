[Read in English](README.md)

<p align="center">
  <img src="docs/assets/banner.svg" alt="selenium-cucumber-pom: framework de interfaz web para una app contable con shadow DOM, con features BDD, Page Objects con localizadores centralizados y reportes de Cucumber" width="100%">
</p>

## Explicado para un niño de 8 años

Imagina un libro de recetas. Cada receta dice en palabras sencillas qué hacer: "abre la página de entrada, escribe tu nombre, revisa que tu nombre aparezca". Un cocinero lee cada paso y lo hace en la cocina. En este proyecto las recetas son los feature files, el cocinero es el código en Java y la cocina es una aplicación web de contabilidad. Algunos botones de esa aplicación están escondidos dentro de cajas cerradas (se llaman shadow DOM), así que el cocinero se sabe un truco especial para abrirlas.

### Un ejemplo real

- **Imagina que:** la receta "iniciar sesión" abre la aplicación, escribe el usuario y la clave, y mira la página de inicio.
- **Qué debería pasar:** después de entrar, la página de inicio muestra el nombre del usuario.
- **Qué hace el proyecto:** `LoginPage` escribe las credenciales y `HomePage` revisa que se vea el panel de bienvenida.
- **Qué ganamos:** si iniciar sesión se dañara, la receta se detendría en ese paso. La segunda receta (crear un cliente) está escrita a medias: sus dos últimos pasos siguen marcados como "pendientes".

<p align="center">
  <img src="docs/assets/explained.es.gif" alt="Historia ilustrada: una receta en palabras sencillas, un cocinero que sigue cada paso, botones escondidos en cajas cerradas que getShadowRoot abre, un cajón con etiqueta para cada botón y una segunda receta con dos pasos por escribir" width="960">
</p>

La misma animación en [MP4](docs/assets/explained.es.mp4).

### Palabras difíciles

| Palabra | Qué quiere decir |
|---|---|
| Selenium | Una herramienta que deja que el código maneje un navegador de verdad |
| Cucumber | La herramienta que lee las recetas en inglés sencillo y corre el código que les corresponde |
| Page Object | Una chuleta de una página: dónde está cada botón, para que los pasos no tengan que acordarse |
| Localizador | La dirección de un botón o de una casilla en la página |
| Shadow DOM | Una caja cerrada dentro de una página que esconde sus propios botones |
| Pendiente (pending) | Un paso que está escrito pero todavía no está programado |

# Selenium Cucumber POM

Framework de automatización de interfaz web en Java 17 que combina Selenium WebDriver 4, Cucumber 7 (BDD) y el Page Object Model, con localizadores centralizados, manejo de shadow DOM y reportes HTML de Cucumber.

![Java](https://img.shields.io/badge/Java-17-ED8B00?logo=openjdk&logoColor=white)
![Selenium](https://img.shields.io/badge/Selenium-4.29-43B02A?logo=selenium&logoColor=white)
![Cucumber](https://img.shields.io/badge/Cucumber-7-23D96C?logo=cucumber&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-3.x-C71A36?logo=apachemaven&logoColor=white)

## Cómo funciona

<p align="center">
  <img src="docs/assets/how-it-works.svg" alt="Los features pasan por los runners de Cucumber a los step definitions, que comparten un contexto de PicoContainer; los steps llaman a los Page Objects, cuyos localizadores entran al shadow DOM de la app de staging, mientras la capa de driver resuelve ambiente y navegador y los runners escriben los reportes de Cucumber" width="100%">
</p>

1. `RunnerLogin` y `RunnerRegister` corren un feature cada uno con Cucumber y los plugins `pretty`, HTML, JSON y JUnit.
2. Los step definitions reciben un `Context` compartido por PicoContainer; `Context` extiende `BasesClassDriver`, que resuelve la URL (`-Denvironment=qa` en `config.properties`, que se puede reemplazar con `BASE_URL`) y abre Chrome.
3. Los steps llaman a los Page Objects (`LoginPage`, `HomePage`, `CreateCostumerPage`); los localizadores viven en `locators/` como campos `@FindBy` inicializados con `PageFactory`.
4. Para los componentes web propios de la app, los Page Objects pasan por `getShadowRoot()` para llegar a campos, listas desplegables y botones.
5. En `Register.feature` los dos últimos pasos todavía lanzan `PendingException`.

<p align="center">
  <img src="docs/assets/how-it-works.gif" alt="Animación: cada paso Gherkin de Login.feature y Register.feature se conecta con el código que llama, y termina con los dos pasos pendientes de Register y el reporte consolidado de Cucumber" width="960">
</p>

La animación recorre paso a paso el camino en el código de los dos features (también en [MP4](docs/assets/how-it-works.mp4)). No es la grabación de una ejecución: no hay workflow de CI, así que en este repositorio no hay resultados de ejecuciones publicados.

## Qué prueba

Escrito contra el ambiente de staging de una aplicación web de contabilidad en la nube construida con componentes web propios (shadow DOM). La URL y las credenciales se inyectan al ejecutar; no hay nada del ambiente subido al repositorio.

| Feature | Escenario | Estado |
|---|---|---|
| `Login.feature` | El usuario inicia sesión con credenciales válidas y llega al inicio (se revisa el panel de bienvenida, no el nombre de usuario) | Implementado |
| `Register.feature` | Desde el inicio se abre "Create > Customer" y se llenan los datos básicos y de contacto; se envía el formulario y se revisa el mensaje de éxito | Parcial: los dos últimos pasos son `PendingException` |

## Arquitectura

- **Page Objects** (`pages/`): `LoginPage`, `HomePage` y `CreateCostumerPage` exponen las acciones del usuario; los localizadores viven aparte en `locators/` como campos `@FindBy` inicializados con `PageFactory`.
- **Shadow DOM**: los Page Objects recorren `getShadowRoot()` en los elementos propios para llegar a campos, listas y botones que los selectores normales no ven.
- **Capa de driver** (`drivers/`): `BasesClassDriver` resuelve el ambiente (`-Denvironment=qa` contra `config.properties`, que se puede reemplazar con `BASE_URL`) y el navegador (`-Dbrowser=chrome`); `MyChromeDriver` arma las `ChromeOptions` (headless con `HEADLESS=true`).
- **Inyección de dependencias**: PicoContainer de Cucumber comparte un solo `Context` (driver y sesión) entre las clases de step definitions.
- **Datos de prueba y utilidades**: `LoginData` lee las credenciales del entorno; `RandomFunctions` genera correos, números y textos; `Waits` envuelve las esperas explícitas.

## Cómo correrlo

Requiere JDK 17, Maven 3.x y Google Chrome (Selenium Manager resuelve el driver).

La configuración sale de variables de entorno (vea `.env.example`): `BASE_URL`, `APP_USERNAME`, `APP_PASSWORD` y la opcional `HEADLESS`. Si faltan las credenciales, la suite se detiene con un mensaje claro antes de abrir Chrome.

```bash
git clone https://github.com/criguex/selenium-cucumber-pom.git
cd selenium-cucumber-pom

export BASE_URL="https://your-app-under-test.example.com/#/login"
export APP_USERNAME="your.user@example.com"
export APP_PASSWORD="your-password"
export HEADLESS=true                        # opcional, por defecto false

mvn clean test                              # los dos runners
mvn clean test -Dtest=RunnerLogin           # un solo flujo
mvn clean verify                            # pruebas + reporte HTML consolidado
```

## Estructura del proyecto

```
.
├── .env.example
├── config.properties
├── docs/
│   ├── assets/        banner.svg, how-it-works.*, explained.* (animación para niños, EN + .es)
│   └── scenes/        how-it-works.html, explained.html + sus scripts de export
├── pom.xml
└── src/
    ├── main/java/
    │   ├── drivers/      BasesClassDriver, MyChromeDriver
    │   ├── hooks/        Context (compartido con PicoContainer)
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

## Reportes

- Salida `pretty` de Cucumber en consola, reporte HTML en `target/cucumber-reports/report.html`, JSON en `target/cucumber.json` y JUnit XML en `target/cucumber-reports/cucumber.xml`.
- `mvn verify` corre el plugin `maven-cucumber-reporting` y deja un reporte consolidado en `target/cucumber-html-reports/`.

## CI

No hay workflow configurado: la suite apunta a un ambiente privado de staging que necesita credenciales y navegador, así que corre en local o desde un pipeline que inyecte `BASE_URL`, `APP_USERNAME` y `APP_PASSWORD` como secretos.

## Límites

- `Register.feature` no está terminado: sus dos últimos pasos son `PendingException`.
- `BasesClassDriver` solo tiene Chrome conectado; cualquier otro valor de `-Dbrowser` lanza `Unsupported browser`.
- Algunos pasos usan pausas fijas (`Waits.waitFor`) además de esperas explícitas.
- La aplicación bajo prueba es privada, así que la suite no corre sin acceso a ese ambiente de staging.

## Regenerar la animación

`docs/scenes/how-it-works.html` dibuja cada cuadro a partir de `?t=<segundos>`; `docs/scenes/export.sh` captura los cuadros con Chrome headless y arma `how-it-works.gif` y `how-it-works.mp4` con ffmpeg. `docs/scenes/explained.html` (`?t=<segundos>&lang=en|es`) y `docs/scenes/export-explained.sh` hacen lo mismo con la animación para niños.

---

Cristian Guerra · Senior SDET · [linkedin.com/in/criguex](https://www.linkedin.com/in/criguex)

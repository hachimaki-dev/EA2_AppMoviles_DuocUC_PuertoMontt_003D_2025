# DigiDex - Aplicación Android de Digimon

## Información del Proyecto

**Nombre del Proyecto:** DigiDex - Enciclopedia Digital de Digimon

**Institución:** DuocUC

**Curso:** Desarrollo de Aplicaciones Móviles

**Período:** 2025

---

## Integrantes del Equipo

- Felipe Angel
- Ismael Oyarzun

---

## Descripción del Proyecto

DigiDex es una aplicación Android nativa desarrollada con **Kotlin** y **Jetpack Compose** que permite a los usuarios explorar el mundo de Digimon. La aplicación consume la API REST pública de [Digi-API](https://digi-api.com/) para mostrar información detallada sobre diferentes Digimon, incluyendo sus niveles, tipos, atributos y más.

### Características Principales

- **Listado de Digimon:** Visualización de todos los Digimon disponibles con sus imágenes
- **Detalle de Digimon:** Información completa de cada Digimon (niveles, tipos, atributos, campos)
- **Navegación Fluida:** Sistema de navegación entre pantallas sin errores
- **Arquitectura MVVM:** Implementación completa del patrón Model-View-ViewModel
- **Consumo de API REST:** Integración con Digi-API usando Retrofit
- **UI Moderna:** Diseño con Material Design 3 y Jetpack Compose
- **Carga de Imágenes:** Uso de Coil para carga eficiente de imágenes
- **Tests Unitarios:** Cobertura con JUnit5, Kotest y MockK

---

## Tecnologías Utilizadas

### Frontend (Android)
- **Kotlin** 2.0.21
- **Jetpack Compose** - UI moderna y declarativa
- **Material Design 3** - Diseño de interfaz
- **Navigation Compose** - Navegación entre pantallas
- **ViewModel** - Gestión de estado
- **StateFlow** - Flujos reactivos

### Networking
- **Retrofit** 2.11.0 - Cliente HTTP
- **OkHttp** 4.12.0 - Logging e interceptores
- **Gson** - Serialización JSON

### Manejo de Imágenes
- **Coil** 2.7.0 - Carga y caché de imágenes

### Testing
- **JUnit5** 5.11.3 - Framework de testing
- **Kotest** 5.9.1 - Testing estilo BDD
- **MockK** 1.13.13 - Mocking para Kotlin
- **Coroutines Test** 1.9.0 - Testing de corrutinas

---

## Arquitectura MVVM

La aplicación sigue el patrón **Model-View-ViewModel** para una separación clara de responsabilidades:

```
app/
├── data/
│   ├── model/           # Modelos de datos (Digimon, DigimonItem, etc.)
│   ├── network/         # Servicio Retrofit y ApiClient
│   └── repository/      # DigimonRepository (capa de abstracción)
├── ui/
│   ├── screens/         # Composables (MainScreen, DetailScreen, etc.)
│   ├── viewmodel/       # ViewModels (DigimonViewModel)
│   ├── navigation/      # Sistema de rutas
│   └── theme/           # Tema de la aplicación
└── MainActivity.kt      # Punto de entrada
```

### Flujo de Datos

```
View (Composable) ←→ ViewModel ←→ Repository ←→ API Service ←→ Digi-API
```

**Justificación de MVVM:**

1. **Separación de Concerns:** La lógica de negocio está separada de la UI
2. **Testeable:** Los ViewModels pueden ser probados sin dependencias de Android
3. **Reactivo:** Uso de StateFlow para actualizaciones reactivas de UI
4. **Lifecycle-Aware:** Los ViewModels sobreviven a cambios de configuración
5. **Mantenible:** Código organizado y fácil de mantener

---

## API Externa: Digi-API

### Base URL
```
https://digi-api.com/
```

### Endpoints Utilizados

#### 1. Listar Digimon
```
GET /api/v1/digimon?page=0&pageSize=20
```

**Respuesta:**
```json
{
  "content": [
    {
      "id": 1,
      "name": "Agumon",
      "href": "https://digi-api.com/api/v1/digimon/1",
      "image": "https://digi-api.com/images/digimon/w/Agumon.png"
    }
  ],
  "pageable": {
    "currentPage": 0,
    "elementsOnPage": 20,
    "totalElements": 1422,
    "totalPages": 72
  }
}
```

#### 2. Detalle de Digimon
```
GET /api/v1/digimon/{id}
```

**Respuesta:**
```json
{
  "id": 1,
  "name": "Agumon",
  "xAntibody": false,
  "images": [
    {
      "href": "https://digi-api.com/images/digimon/w/Agumon.png",
      "transparent": false
    }
  ],
  "levels": [
    {
      "id": 4,
      "level": "Rookie"
    }
  ],
  "types": [
    {
      "id": 3,
      "type": "Reptile"
    }
  ],
  "attributes": [
    {
      "id": 2,
      "attribute": "Vaccine"
    }
  ]
}
```

### Implementación en el Código

**DigimonService.kt (app/src/main/java/com/example/ev3/data/network/DigimonService.kt:10)**
```kotlin
interface DigimonService {
    @GET("api/v1/digimon")
    suspend fun getDigimonList(
        @Query("page") page: Int = 0,
        @Query("pageSize") pageSize: Int = 20
    ): DigimonListResponse

    @GET("api/v1/digimon/{id}")
    suspend fun getDigimonDetail(@Path("id") id: Int): DigimonDetailResponse
}
```

**ApiClient.kt (app/src/main/java/com/example/ev3/data/network/ApiClient.kt:10)**
```kotlin
object ApiClient {
    private const val BASE_URL = "https://digi-api.com/"

    val digimonService: DigimonService = retrofit.create(DigimonService::class.java)
}
```

---

## Estructura de Paquetes

```
com.example.ev3/
├── data/
│   ├── auth/
│   │   └── AuthRepository.kt          # Repositorio de autenticación
│   ├── model/
│   │   └── Digimon.kt                 # Modelos de datos (DigimonItem, DigimonDetailResponse, etc.)
│   ├── network/
│   │   ├── ApiClient.kt               # Configuración de Retrofit
│   │   └── DigimonService.kt          # Definición de endpoints
│   └── repository/
│       └── DigimonRepository.kt       # Capa de abstracción de datos
├── ui/
│   ├── navigation/
│   │   └── Routes.kt                  # Definición de rutas de navegación
│   ├── screens/
│   │   ├── LoginScreen.kt             # Pantalla de login
│   │   ├── SignUpScreen.kt            # Pantalla de registro
│   │   ├── MainScreen.kt              # Pantalla principal con lista de Digimon
│   │   ├── DigimonDetailScreen.kt     # Pantalla de detalle de Digimon
│   │   ├── ContactoScreen.kt          # Pantalla de contacto
│   │   └── NosotrosScreen.kt          # Pantalla sobre nosotros
│   ├── theme/
│   │   ├── Color.kt                   # Colores del tema
│   │   ├── Theme.kt                   # Configuración del tema
│   │   └── Type.kt                    # Tipografía
│   └── viewmodel/
│       └── DigimonViewModel.kt        # ViewModel principal
└── MainActivity.kt                     # Activity principal
```

---

## Instalación y Ejecución

### Prerequisitos

- **Android Studio** Hedgehog | 2023.1.1 o superior
- **JDK** 11 o superior
- **Android SDK** API 24+ (Android 7.0 Nougat o superior)
- **Emulador Android** o dispositivo físico

### Pasos para Ejecutar

1. **Clonar el repositorio:**
   ```bash
   git clone https://github.com/[tu-usuario]/EV3.git
   cd EV3
   ```

2. **Abrir en Android Studio:**
   - File → Open → Seleccionar la carpeta del proyecto
   - Esperar a que Gradle sincronice las dependencias

3. **Configurar el emulador:**
   - Tools → Device Manager → Create Device
   - Seleccionar un dispositivo (recomendado: Pixel 5, API 34)

4. **Ejecutar la aplicación:**
   - Click en el botón "Run" (▶️) o presionar `Shift + F10`
   - Seleccionar el dispositivo/emulador
   - Esperar a que la app se instale y abra

### Ejecutar Tests Unitarios

```bash
# Desde la terminal
./gradlew test

# O desde Android Studio
Right-click en test/ → Run 'All Tests'
```

---

## Tests Unitarios

### Cobertura de Tests

La aplicación incluye tests para las capas críticas:

#### 1. DigimonViewModelTest
**Ubicación:** `app/src/test/java/com/example/ev3/viewmodel/DigimonViewModelTest.kt`

**Casos de prueba:**
- ✅ Carga exitosa de lista de Digimon
- ✅ Manejo de errores en la carga de lista
- ✅ Carga exitosa de detalle de Digimon
- ✅ Manejo de errores en la carga de detalle
- ✅ Funcionalidad de retry

#### 2. DigimonRepositoryTest
**Ubicación:** `app/src/test/java/com/example/ev3/repository/DigimonRepositoryTest.kt`

**Casos de prueba:**
- ✅ fetchDigimonList retorna lista exitosamente
- ✅ fetchDigimonList maneja excepciones
- ✅ fetchDigimonDetail retorna detalle exitosamente
- ✅ fetchDigimonDetail maneja excepciones

### Frameworks de Testing Utilizados

**JUnit5 + Kotest (Estrategia Híbrida):**
- **JUnit5:** Framework base para estructura de tests
- **Kotest:** Sintaxis expresiva estilo BDD (Behavior-Driven Development)
- **MockK:** Mocking específico para Kotlin
- **Coroutines Test:** Testing de funciones suspend

**Ejemplo de Test:**
```kotlin
test("fetchDigimonList debe retornar lista exitosamente") {
    runTest {
        // Given
        val mockResponse = DigimonListResponse(...)
        coEvery { apiService.getDigimonList(0, 20) } returns mockResponse

        // When
        val result = repository.fetchDigimonList()

        // Then
        result.isSuccess shouldBe true
        result.getOrNull()?.size shouldBe 2
    }
}
```

---

## Dependencias Principales

```kotlin
// Jetpack Compose
implementation("androidx.compose.ui:ui")
implementation("androidx.compose.material3:material3")
implementation("androidx.navigation:navigation-compose:2.8.3")
implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.9.4")

// Networking
implementation("com.squareup.retrofit2:retrofit:2.11.0")
implementation("com.squareup.retrofit2:converter-gson:2.11.0")
implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

// Imágenes
implementation("io.coil-kt:coil-compose:2.7.0")

// Testing
testImplementation("org.junit.jupiter:junit-jupiter-api:5.11.3")
testImplementation("io.kotest:kotest-runner-junit5:5.9.1")
testImplementation("io.mockk:mockk:1.13.13")
testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.9.0")
```

---

## Capturas de Pantalla

### Pantalla Principal (MainScreen)
- Lista de Digimon con imágenes
- Navegación a pantallas de Contacto y Nosotros
- Click en cualquier Digimon para ver detalles

### Pantalla de Detalle (DigimonDetailScreen)
- Imagen grande del Digimon
- Información completa: niveles, tipos, atributos, campos
- Indicador de X-Antibody si aplica

---

## Sistema de Navegación

La aplicación utiliza **Navigation Compose** con las siguientes rutas:

```kotlin
// Routes.kt
object Routes {
    const val LOGIN = "login"
    const val SIGN_UP = "sign_up"
    const val MAIN = "main"
    const val DIGIMON_DETAIL = "digimon_detail/{digimonId}"
    const val CONTACTO = "contacto"
    const val NOSOTROS = "nosotros"
}
```

**Flujo de Navegación:**
```
Login → Main Screen → [Digimon Detail | Contacto | Nosotros]
  ↓
Sign Up
```

---

## Gestión de Estado

### StateFlow en ViewModel

```kotlin
data class DigimonListUiState(
    val isLoading: Boolean = false,
    val digimonList: List<DigimonItem> = emptyList(),
    val error: String? = null
)

class DigimonViewModel : ViewModel() {
    private val _listUiState = MutableStateFlow(DigimonListUiState())
    val listUiState: StateFlow<DigimonListUiState> = _listUiState.asStateFlow()
}
```

### Observación en Composables

```kotlin
@Composable
fun MainScreen(viewModel: DigimonViewModel = viewModel()) {
    val uiState by viewModel.listUiState.collectAsState()

    when {
        uiState.isLoading -> ShowLoading()
        uiState.error != null -> ShowError(uiState.error)
        else -> ShowDigimonList(uiState.digimonList)
    }
}
```

---

## Manejo de Errores

La aplicación implementa un manejo robusto de errores:

1. **Capa de Repository:** Captura excepciones y las convierte en `Result<T>`
2. **Capa de ViewModel:** Procesa los resultados y actualiza el estado de UI
3. **Capa de View:** Muestra mensajes de error y opción de reintentar

```kotlin
// En DigimonRepository
suspend fun fetchDigimonList(): Result<List<DigimonItem>> =
    withContext(Dispatchers.IO) {
        try {
            val response = apiService.getDigimonList()
            Result.success(response.content)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
```

---

## Buenas Prácticas Implementadas

### Código
- ✅ Arquitectura MVVM completa
- ✅ Inyección de dependencias manual (preparado para Hilt/Koin)
- ✅ Uso de corrutinas para operaciones asíncronas
- ✅ StateFlow para gestión de estado reactivo
- ✅ Separación de concerns en capas
- ✅ Uso de `testTag` para testing de UI

### Testing
- ✅ Tests unitarios con alta cobertura
- ✅ Mocking de dependencias
- ✅ Testing de corrutinas con TestDispatcher
- ✅ Estrategia híbrida JUnit5 + Kotest

### UI/UX
- ✅ Material Design 3
- ✅ Loading states y error handling
- ✅ Navegación intuitiva
- ✅ Imágenes optimizadas con Coil
- ✅ Responsive design

---

## Planificación y Colaboración

### Trello
El proyecto se gestionó usando Trello con las siguientes columnas:
- **Backlog:** Tareas pendientes
- **En Progreso:** Tareas en desarrollo
- **Testing:** Tareas en fase de prueba
- **Completadas:** Tareas finalizadas

**Link al Trello:** [Insertar link aquí]

### GitHub
- Repositorio público con commits de todos los integrantes
- Ramas por feature
- Pull requests revisados

**Link al Repositorio:** [Insertar link aquí]

---

## Problemas Conocidos y Soluciones

### Problema 1: Imágenes no cargan
**Solución:** Verificar permiso de INTERNET en AndroidManifest.xml

### Problema 2: Tests fallan en CI/CD
**Solución:** Asegurar que `testOptions.unitTests.all { useJUnitPlatform() }` esté configurado

### Problema 3: API rate limiting
**Solución:** La API es pública sin rate limiting significativo

---

## Futuras Mejoras

- [ ] Implementar paginación infinita
- [ ] Agregar búsqueda y filtros
- [ ] Favoritos con persistencia local (Room)
- [ ] Modo oscuro
- [ ] Animaciones entre pantallas
- [ ] Cacheo de datos offline
- [ ] Inyección de dependencias con Hilt

---

## Recursos y Referencias

- [Digi-API Documentation](https://digi-api.com/)
- [Jetpack Compose Docs](https://developer.android.com/jetpack/compose)
- [Retrofit Documentation](https://square.github.io/retrofit/)
- [Kotest Documentation](https://kotest.io/)
- [MockK Documentation](https://mockk.io/)

---

## Licencia

Este proyecto es académico y se desarrolló con fines educativos.

---

## Contacto

Para preguntas o soporte, contactar a:
- [Email del equipo]
- [Link al repositorio GitHub]

---

**Última actualización:** Diciembre 2025

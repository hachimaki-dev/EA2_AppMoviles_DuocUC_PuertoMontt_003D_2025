# 📋 Guía de Defensa - DigiDex App

## ⏱️ Cronograma (15 minutos totales)

### 1. Inicio y Ejecución (2 minutos)
- ✅ Abrir Android Studio
- ✅ Ejecutar la app en emulador/dispositivo
- ✅ Mostrar que la app arranca sin errores

### 2. Dependencias y Entorno (2 minutos)
- ✅ Mostrar `build.gradle.kts` y explicar dependencias principales:
  - Retrofit para consumo de API
  - Coil para imágenes
  - Compose para UI
  - JUnit5 + Kotest + MockK para tests

### 3. Arquitectura MVVM (4 minutos)

#### Mostrar estructura de carpetas:
```
app/src/main/java/com/example/ev3/
├── data/               ← MODEL
│   ├── model/
│   ├── network/
│   └── repository/
├── ui/
│   ├── screens/       ← VIEW
│   └── viewmodel/     ← VIEWMODEL
```

#### Explicar flujo de datos:
1. **View (MainScreen.kt):**
   - Composable que observa el estado del ViewModel
   - NO contiene lógica de negocio
   - Solo renderiza UI basada en el estado

2. **ViewModel (DigimonViewModel.kt):**
   - Gestiona el estado de la UI con StateFlow
   - Llama al Repository para obtener datos
   - Expone estados: isLoading, data, error

3. **Repository (DigimonRepository.kt):**
   - Abstrae la fuente de datos
   - Maneja la lógica de obtención de datos
   - Retorna Result<T> para manejo de errores

4. **Model (Digimon.kt):**
   - Data classes que representan los datos
   - Modelos de respuesta de la API

#### Código a mostrar:

**ViewModel (DigimonViewModel.kt:29)**
```kotlin
class DigimonViewModel(
    private val repository: DigimonRepository = DigimonRepository()
) : ViewModel() {

    private val _listUiState = MutableStateFlow(DigimonListUiState())
    val listUiState: StateFlow<DigimonListUiState> = _listUiState.asStateFlow()

    fun loadDigimonList(page: Int = 0, pageSize: Int = 20) {
        viewModelScope.launch {
            _listUiState.value = _listUiState.value.copy(isLoading = true)

            repository.fetchDigimonList(page, pageSize)
                .onSuccess { list ->
                    _listUiState.value = DigimonListUiState(
                        isLoading = false,
                        digimonList = list
                    )
                }
                .onFailure { exception ->
                    _listUiState.value = DigimonListUiState(
                        isLoading = false,
                        error = exception.message
                    )
                }
        }
    }
}
```

**View observando ViewModel (MainScreen.kt:30)**
```kotlin
@Composable
fun MainScreen(
    viewModel: DigimonViewModel = viewModel(),
    onOpenContacto: () -> Unit,
    onOpenNosotros: () -> Unit,
    onDigimonClick: (Int) -> Unit = {}
) {
    val uiState by viewModel.listUiState.collectAsState()

    when {
        uiState.isLoading -> ShowLoading()
        uiState.error != null -> ShowError(uiState.error)
        else -> ShowDigimonList(uiState.digimonList)
    }
}
```

### 4. Consumo de API Externa: Digi-API (4 minutos)

#### Mostrar implementación de Retrofit:

**ApiClient.kt (app/src/main/java/com/example/ev3/data/network/ApiClient.kt:10)**
```kotlin
object ApiClient {
    private const val BASE_URL = "https://digi-api.com/"

    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttp = OkHttpClient.Builder()
        .addInterceptor(logging)
        .connectTimeout(30, TimeUnit.SECONDS)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttp)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val digimonService: DigimonService = retrofit.create(DigimonService::class.java)
}
```

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

#### Endpoints usados:
1. **Lista de Digimon:** `GET https://digi-api.com/api/v1/digimon?page=0&pageSize=20`
2. **Detalle de Digimon:** `GET https://digi-api.com/api/v1/digimon/{id}`

#### Demostración en vivo:
- Abrir la app y mostrar la lista cargándose desde la API
- Click en un Digimon para ver el detalle
- Mostrar el Logcat con las peticiones HTTP (gracias al interceptor)

### 5. Tests Unitarios (2 minutos)

#### Ejecutar los tests:
```bash
./gradlew test
```

O desde Android Studio: `Right-click en test/ → Run 'All Tests'`

#### Mostrar tests clave:

**DigimonViewModelTest.kt**
```kotlin
test("ViewModel debe cargar lista de Digimon exitosamente") {
    // Given
    val mockDigimonList = listOf(...)
    coEvery { repository.fetchDigimonList(any(), any()) } returns Result.success(mockDigimonList)

    // When
    viewModel = DigimonViewModel(repository)
    testDispatcher.scheduler.advanceUntilIdle()

    // Then
    val state = viewModel.listUiState.value
    state.isLoading shouldBe false
    state.digimonList.size shouldBe 2
    state.error shouldBe null
}
```

#### Explicar estrategia de testing:
- **JUnit5:** Framework base
- **Kotest:** Sintaxis expresiva estilo BDD
- **MockK:** Mocking específico para Kotlin
- **Coroutines Test:** Testing de funciones suspend

### 6. Colaboración y Planificación (1 minuto)

#### Mostrar GitHub:
- Commits de todos los integrantes
- Ramas por feature
- Pull requests

#### Mostrar Trello:
- Backlog, En Progreso, Testing, Completadas
- Evidencia de planificación

---

## 🎯 Puntos Clave a Enfatizar

### ¿Por qué NO usamos Supabase?

**Respuesta:**
> "Decidimos utilizar la API pública de Digi-API (https://digi-api.com/) porque:
> 1. Es una API REST completamente funcional y documentada
> 2. Nos permite demostrar el consumo de APIs externas con Retrofit
> 3. Cumple con el requisito de 'al menos una pantalla conectada consumiendo datos mediante API REST'
> 4. No requiere configuración adicional de backend
> 5. La arquitectura MVVM es la misma independientemente de la fuente de datos"

### ¿Cómo justificar MVVM?

**Respuesta:**
> "Implementamos MVVM porque:
> 1. **Separación de concerns:** View solo renderiza, ViewModel maneja lógica, Repository gestiona datos
> 2. **Testeable:** Podemos testear ViewModels sin dependencias de Android
> 3. **Reactivo:** StateFlow nos da actualizaciones automáticas de UI
> 4. **Lifecycle-aware:** ViewModels sobreviven a rotaciones de pantalla
> 5. **Escalable:** Fácil agregar nuevas features sin romper código existente"

### Diferencia entre nuestra API y microservicios propios

**Respuesta:**
> "La diferencia principal es:
> - **API Externa (Digi-API):** Consumimos endpoints existentes, no tenemos control sobre el backend
> - **Microservicios propios:** Serían endpoints creados por nosotros con Spring Boot
>
> Sin embargo, desde el punto de vista de la app Android:
> - El código de consumo (Retrofit) es idéntico
> - La arquitectura MVVM es la misma
> - Solo cambiaría la BASE_URL y los modelos de datos"

---

## ✅ Checklist Pre-Defensa

### Antes de la Presentación:
- [ ] App corre sin errores en emulador/dispositivo
- [ ] Internet está funcionando (para cargar datos de la API)
- [ ] Android Studio está abierto con el proyecto
- [ ] Emulador/dispositivo está conectado y funcional
- [ ] Tests unitarios pasan correctamente
- [ ] GitHub está actualizado y accesible
- [ ] Trello está actualizado y accesible
- [ ] README.md está completo

### Durante la Presentación:
- [ ] Mostrar app funcionando primero (¡CRÍTICO!)
- [ ] Navegar entre todas las pantallas
- [ ] Demostrar que no hay crashes
- [ ] Mostrar loading states y error handling
- [ ] Explicar arquitectura con diagramas o código
- [ ] Ejecutar tests en tiempo real
- [ ] Mostrar colaboración en GitHub

---

## 🚨 Errores Comunes a Evitar

1. **NO ejecutar la app al inicio**
   - Consecuencia: Calificación cero en todo lo demás
   - Solución: SIEMPRE empezar mostrando la app funcionando

2. **No poder explicar MVVM**
   - Consecuencia: Perder puntos en arquitectura
   - Solución: Practicar la explicación del flujo de datos

3. **Tests no pasan**
   - Consecuencia: Perder puntos en testing
   - Solución: Ejecutar `./gradlew test` antes de presentar

4. **No mostrar consumo de API**
   - Consecuencia: Perder puntos en integración
   - Solución: Mostrar Logcat con peticiones HTTP

5. **GitHub sin commits de todos**
   - Consecuencia: Perder puntos en colaboración
   - Solución: Verificar que todos hayan hecho commits

---

## 📱 Demostración de Funcionalidades

### Flujo de Demostración:
1. Abrir app → Login
2. Login exitoso → MainScreen con lista de Digimon
3. Scroll por la lista (mostrar imágenes cargando)
4. Click en un Digimon → Ver detalle completo
5. Botón back → Volver a la lista
6. Click en "Contacto" → Navegar a ContactoScreen
7. Back → Volver a MainScreen
8. Click en "Nosotros" → Navegar a NosotrosScreen

### Estados a Demostrar:
- ✅ Loading state (CircularProgressIndicator)
- ✅ Success state (lista de Digimon)
- ✅ Error state (simulando error de red)
- ✅ Retry functionality

---

## 💡 Tips para una Buena Defensa

1. **Practica el tiempo:** Ensaya varias veces para ajustarte a 15 minutos
2. **Ten backups:** Screenshots de la app funcionando por si falla algo
3. **Conoce tu código:** Lee todo el código antes de presentar
4. **Sé honesto:** Si algo no funciona, admítelo y explica qué harías
5. **Destaca lo bueno:** Enfatiza las buenas prácticas implementadas
6. **Prepara preguntas:** Anticipa qué podría preguntar el profesor

---

## 🎤 Frases Clave para la Defensa

- "Implementamos arquitectura MVVM para separación de concerns y testabilidad"
- "Usamos Retrofit con Digi-API para consumir datos REST"
- "StateFlow nos permite actualizaciones reactivas de la UI"
- "Los tests unitarios cubren ViewModel y Repository con MockK"
- "Coil nos da carga eficiente de imágenes con caché"
- "Navigation Compose maneja la navegación type-safe"
- "Coroutines con Dispatchers.IO para operaciones de red"

---

## 📊 Métricas del Proyecto

- **Pantallas implementadas:** 6 (Login, SignUp, Main, Detail, Contacto, Nosotros)
- **Endpoints consumidos:** 2 (Lista y Detalle de Digimon)
- **Tests unitarios:** 9 casos de prueba
- **Líneas de código:** ~1500
- **Dependencias principales:** 15+
- **Tiempo de desarrollo:** [Completar según su caso]

---

**¡Mucha suerte en la defensa! 🚀**

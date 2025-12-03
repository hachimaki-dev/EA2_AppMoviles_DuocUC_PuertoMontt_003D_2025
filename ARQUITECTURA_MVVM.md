# 🏗️ Arquitectura MVVM - DigiDex App

## Diagrama de Arquitectura

```
┌─────────────────────────────────────────────────────────────┐
│                         VIEW LAYER                          │
│                     (Jetpack Compose)                       │
├─────────────────────────────────────────────────────────────┤
│  MainScreen.kt          DigimonDetailScreen.kt              │
│  LoginScreen.kt         ContactoScreen.kt                   │
│  SignUpScreen.kt        NosotrosScreen.kt                   │
│                                                             │
│  ↓ observa StateFlow                    ↑ llama funciones  │
└─────────────────────────────────────────────────────────────┘
                            ↕
┌─────────────────────────────────────────────────────────────┐
│                      VIEWMODEL LAYER                        │
│              (Gestión de Estado y Lógica)                   │
├─────────────────────────────────────────────────────────────┤
│  DigimonViewModel.kt                                        │
│  - listUiState: StateFlow<DigimonListUiState>              │
│  - detailUiState: StateFlow<DigimonDetailUiState>          │
│  - loadDigimonList()                                        │
│  - loadDigimonDetail(id)                                    │
│  - retry()                                                  │
│                                                             │
│  ↓ llama métodos                       ↑ retorna Result<T> │
└─────────────────────────────────────────────────────────────┘
                            ↕
┌─────────────────────────────────────────────────────────────┐
│                     REPOSITORY LAYER                        │
│              (Abstracción de Fuente de Datos)              │
├─────────────────────────────────────────────────────────────┤
│  DigimonRepository.kt                                       │
│  - fetchDigimonList(): Result<List<DigimonItem>>           │
│  - fetchDigimonDetail(id): Result<DigimonDetailResponse>   │
│                                                             │
│  ↓ usa                                  ↑ retorna data     │
└─────────────────────────────────────────────────────────────┘
                            ↕
┌─────────────────────────────────────────────────────────────┐
│                    DATA SOURCE LAYER                        │
│                 (Network / API Service)                     │
├─────────────────────────────────────────────────────────────┤
│  ApiClient.kt           DigimonService.kt                   │
│  - Retrofit setup       - getDigimonList()                  │
│  - OkHttpClient         - getDigimonDetail(id)              │
│  - Interceptors                                             │
│                                                             │
│  ↓ hace peticiones HTTP               ↑ recibe JSON        │
└─────────────────────────────────────────────────────────────┘
                            ↕
                    ┌───────────────┐
                    │   DIGI-API    │
                    │ (REST API)    │
                    └───────────────┘
```

---

## Componentes de la Arquitectura

### 1. VIEW (Capa de Presentación)

**Responsabilidades:**
- Renderizar la UI usando Jetpack Compose
- Observar el estado del ViewModel
- Reaccionar a cambios de estado
- Enviar eventos del usuario al ViewModel

**Archivos:**
- `MainScreen.kt` - Lista de Digimon
- `DigimonDetailScreen.kt` - Detalle de un Digimon
- `LoginScreen.kt` - Autenticación
- `SignUpScreen.kt` - Registro
- `ContactoScreen.kt` - Información de contacto
- `NosotrosScreen.kt` - Acerca de

**Ejemplo de código:**
```kotlin
@Composable
fun MainScreen(
    viewModel: DigimonViewModel = viewModel(),
    onDigimonClick: (Int) -> Unit
) {
    // Observa el estado del ViewModel
    val uiState by viewModel.listUiState.collectAsState()

    // Renderiza UI basada en el estado
    when {
        uiState.isLoading -> ShowLoading()
        uiState.error != null -> ShowError(uiState.error)
        else -> ShowDigimonList(uiState.digimonList, onDigimonClick)
    }
}
```

**Características:**
- ✅ Sin lógica de negocio
- ✅ Solo composición de UI
- ✅ Observación reactiva con `collectAsState()`
- ✅ Uso de `testTag` para testing

---

### 2. VIEWMODEL (Capa de Lógica de Presentación)

**Responsabilidades:**
- Gestionar el estado de la UI
- Coordinar llamadas al Repository
- Transformar datos para la UI
- Manejar eventos del usuario
- Sobrevivir a cambios de configuración

**Archivos:**
- `DigimonViewModel.kt`

**Estados definidos:**
```kotlin
data class DigimonListUiState(
    val isLoading: Boolean = false,
    val digimonList: List<DigimonItem> = emptyList(),
    val error: String? = null
)

data class DigimonDetailUiState(
    val isLoading: Boolean = false,
    val digimon: DigimonDetailResponse? = null,
    val error: String? = null
)
```

**Ejemplo de código:**
```kotlin
class DigimonViewModel(
    private val repository: DigimonRepository = DigimonRepository()
) : ViewModel() {

    private val _listUiState = MutableStateFlow(DigimonListUiState())
    val listUiState: StateFlow<DigimonListUiState> = _listUiState.asStateFlow()

    init {
        loadDigimonList()
    }

    fun loadDigimonList(page: Int = 0, pageSize: Int = 20) {
        viewModelScope.launch {
            _listUiState.value = _listUiState.value.copy(isLoading = true, error = null)

            repository.fetchDigimonList(page, pageSize)
                .onSuccess { list ->
                    _listUiState.value = DigimonListUiState(
                        isLoading = false,
                        digimonList = list,
                        error = null
                    )
                }
                .onFailure { exception ->
                    _listUiState.value = DigimonListUiState(
                        isLoading = false,
                        digimonList = emptyList(),
                        error = exception.message ?: "Error desconocido"
                    )
                }
        }
    }
}
```

**Características:**
- ✅ Usa `StateFlow` para estado reactivo
- ✅ Usa `viewModelScope` para corrutinas
- ✅ Manejo de loading/success/error states
- ✅ No depende de Android Framework (testeable)

---

### 3. REPOSITORY (Capa de Datos)

**Responsabilidades:**
- Abstraer la fuente de datos
- Coordinar llamadas a la API
- Manejar excepciones
- Transformar respuestas en modelos de dominio
- Ejecutar operaciones en el dispatcher correcto

**Archivos:**
- `DigimonRepository.kt`

**Ejemplo de código:**
```kotlin
class DigimonRepository(
    private val apiService: DigimonService = ApiClient.digimonService
) {
    suspend fun fetchDigimonList(page: Int = 0, pageSize: Int = 20): Result<List<DigimonItem>> =
        withContext(Dispatchers.IO) {
            try {
                val response = apiService.getDigimonList(page, pageSize)
                Result.success(response.content)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }

    suspend fun fetchDigimonDetail(id: Int): Result<DigimonDetailResponse> =
        withContext(Dispatchers.IO) {
            try {
                val response = apiService.getDigimonDetail(id)
                Result.success(response)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
}
```

**Características:**
- ✅ Usa `withContext(Dispatchers.IO)` para operaciones de red
- ✅ Retorna `Result<T>` para manejo de errores
- ✅ Puede recibir el servicio por constructor (inyección de dependencias)
- ✅ Abstrae la implementación de la fuente de datos

---

### 4. DATA SOURCE (Capa de Red)

**Responsabilidades:**
- Configurar Retrofit
- Definir endpoints
- Configurar interceptores
- Manejar serialización/deserialización JSON

**Archivos:**
- `ApiClient.kt` - Configuración de Retrofit
- `DigimonService.kt` - Definición de endpoints

**ApiClient.kt:**
```kotlin
object ApiClient {
    private const val BASE_URL = "https://digi-api.com/"

    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttp = OkHttpClient.Builder()
        .addInterceptor(logging)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttp)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val digimonService: DigimonService = retrofit.create(DigimonService::class.java)
}
```

**DigimonService.kt:**
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

**Características:**
- ✅ Singleton pattern para ApiClient
- ✅ Logging interceptor para debugging
- ✅ Timeouts configurados
- ✅ Funciones suspend para corrutinas

---

### 5. MODEL (Capa de Modelos)

**Responsabilidades:**
- Definir estructuras de datos
- Mapear respuestas JSON a objetos Kotlin

**Archivos:**
- `Digimon.kt` (contiene todos los modelos)

**Modelos principales:**
```kotlin
// Lista de Digimon
data class DigimonListResponse(
    val content: List<DigimonItem>,
    val pageable: Pageable
)

data class DigimonItem(
    val id: Int,
    val name: String,
    val href: String,
    val image: String
)

// Detalle de Digimon
data class DigimonDetailResponse(
    val id: Int,
    val name: String,
    val xAntibody: Boolean,
    val images: List<DigimonImage>,
    val levels: List<DigimonLevel>,
    val types: List<DigimonType>?,
    val attributes: List<DigimonAttribute>?,
    val fields: List<DigimonField>?
)
```

---

## Flujo de Datos Completo

### Ejemplo: Cargar Lista de Digimon

```
1. Usuario abre MainScreen
   ↓
2. MainScreen observa viewModel.listUiState
   ↓
3. ViewModel.init() llama a loadDigimonList()
   ↓
4. ViewModel actualiza estado a isLoading = true
   ↓
5. ViewModel llama a repository.fetchDigimonList()
   ↓
6. Repository ejecuta en Dispatchers.IO
   ↓
7. Repository llama a apiService.getDigimonList()
   ↓
8. Retrofit hace petición HTTP a Digi-API
   ↓
9. Digi-API responde con JSON
   ↓
10. Gson deserializa JSON a DigimonListResponse
    ↓
11. Repository retorna Result.success(digimonList)
    ↓
12. ViewModel actualiza estado con los datos
    ↓
13. StateFlow emite nuevo estado
    ↓
14. MainScreen recibe nuevo estado vía collectAsState()
    ↓
15. Compose recompone la UI con los nuevos datos
    ↓
16. Usuario ve la lista de Digimon
```

---

## Ventajas de MVVM en este Proyecto

### 1. Separación de Concerns
- **View:** Solo UI, sin lógica
- **ViewModel:** Solo lógica de presentación
- **Repository:** Solo acceso a datos
- **Model:** Solo estructuras de datos

### 2. Testabilidad
```kotlin
// Test del ViewModel sin dependencias de Android
test("ViewModel debe cargar lista exitosamente") {
    val mockRepo = mockk<DigimonRepository>()
    coEvery { mockRepo.fetchDigimonList() } returns Result.success(mockList)

    val viewModel = DigimonViewModel(mockRepo)

    viewModel.listUiState.value.digimonList.size shouldBe 2
}
```

### 3. Reactividad
- UI se actualiza automáticamente cuando cambia el estado
- No hay necesidad de callbacks manuales

### 4. Lifecycle-Aware
- ViewModels sobreviven a rotaciones de pantalla
- Corrutinas se cancelan automáticamente

### 5. Escalabilidad
- Fácil agregar nuevas pantallas
- Fácil cambiar la fuente de datos (API → Base de datos local)

---

## Comparación: Con MVVM vs Sin MVVM

### ❌ Sin MVVM (Código acoplado)
```kotlin
@Composable
fun MainScreen() {
    val digimonList = remember { mutableStateOf<List<Digimon>>(emptyList()) }

    LaunchedEffect(Unit) {
        // Lógica de negocio en la UI
        try {
            val response = Retrofit.Builder()
                .baseUrl("https://digi-api.com/")
                .build()
                .create(DigimonService::class.java)
                .getDigimonList()
            digimonList.value = response.content
        } catch (e: Exception) {
            // Manejo de errores difícil
        }
    }

    // UI mezclada con lógica
}
```

**Problemas:**
- 🚫 No testeable
- 🚫 No sobrevive a rotaciones
- 🚫 Código duplicado en cada pantalla
- 🚫 Difícil mantener

### ✅ Con MVVM (Código desacoplado)
```kotlin
@Composable
fun MainScreen(viewModel: DigimonViewModel = viewModel()) {
    val uiState by viewModel.listUiState.collectAsState()

    // Solo UI, sin lógica
    DigimonList(digimonList = uiState.digimonList)
}
```

**Ventajas:**
- ✅ Testeable
- ✅ Sobrevive a rotaciones
- ✅ Reutilizable
- ✅ Fácil mantener

---

## Conclusión

La arquitectura MVVM implementada en DigiDex nos permite:

1. **Código limpio y mantenible**
2. **Alta testabilidad** (tests unitarios sin Android Framework)
3. **Separación clara de responsabilidades**
4. **UI reactiva** con StateFlow
5. **Manejo robusto de errores**
6. **Escalabilidad** para futuras features

Esta arquitectura es el estándar de la industria para aplicaciones Android modernas y demuestra buenas prácticas de desarrollo profesional.

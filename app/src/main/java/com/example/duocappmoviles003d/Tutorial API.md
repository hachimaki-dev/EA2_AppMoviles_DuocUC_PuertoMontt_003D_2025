# Tutorial: Consumir API REST con Retrofit en Android

## 🎯 ¿Qué vamos a lograr?

Transformar tu catálogo de productos de datos hardcodeados a datos reales desde tu API de Supabase.

**ANTES:** Productos falsos en el código ❌  
**DESPUÉS:** Productos reales desde internet ✅

---

## 📚 PARTE 1: ¿Por qué Retrofit?

### El problema con HTTP manual

Hacer peticiones HTTP "a mano" es tedioso y propenso a errores:

```kotlin
// ❌ Código manual - Más de 20 líneas para una petición simple
val url = URL("https://api.com/products")
val connection = url.openConnection() as HttpURLConnection
connection.requestMethod = "GET"
connection.setRequestProperty("apikey", "tu-key")
connection.connect()

val inputStream = connection.inputStream
val reader = BufferedReader(InputStreamReader(inputStream))
val response = reader.readText()
reader.close()
connection.disconnect()

val gson = Gson()
val products = gson.fromJson(response, Array<Product>::class.java).toList()
```

**Problemas:**
- Mucho código repetitivo
- Fácil cometer errores
- Difícil de mantener y testear
- Tienes que manejar threads manualmente

### La solución: Retrofit

Retrofit convierte tu API en una interfaz de Kotlin:

```kotlin
// ✅ Con Retrofit - Solo 3 líneas
interface ApiService {
    @GET("products")
    suspend fun getProducts(): Response<List<Product>>
}

// Uso
val products = apiService.getProducts()
```

**Ventajas:**
- ✅ Menos código (70% de reducción)
- ✅ Type-safe (errores en compilación, no en runtime)
- ✅ Conversión JSON automática
- ✅ Integración nativa con Coroutines
- ✅ Fácil de testear

---

## 🏗️ PARTE 2: Arquitectura MVVM

Vamos a separar la app en capas (patrón MVVM - Model View ViewModel):

```
┌──────────────────────────────┐
│    CatalogScreen (UI)        │  ← Vista: muestra datos
│    - Loading spinner          │
│    - Grid de productos        │
│    - Pantalla de error        │
└──────────────┬───────────────┘
               │ observa estado
┌──────────────▼───────────────┐
│    CatalogViewModel          │  ← ViewModel: gestiona estado
│    - Loading → Success/Error │
└──────────────┬───────────────┘
               │ pide datos
┌──────────────▼───────────────┐
│    ProductRepository         │  ← Repository: obtiene datos
│    - Llama a la API          │
└──────────────┬───────────────┘
               │ usa
┌──────────────▼───────────────┐
│    ApiService (Retrofit)     │  ← Interface: define endpoints
│    - @GET, @POST, etc.       │
└──────────────┬───────────────┘
               │ configurado por
┌──────────────▼───────────────┐
│    RetrofitClient            │  ← Singleton: configura Retrofit
│    - URL base, conversor JSON│
└──────────────────────────────┘
```

**¿Por qué esta arquitectura?**
- **UI no sabe de dónde vienen los datos** (pueden venir de API, base de datos, caché)
- **Fácil de testear** cada capa por separado
- **Mantenible**: si cambias la API, solo tocas Repository y ApiService
- **Reutilizable**: el Repository puede usarse en otras pantallas

---

## 🚀 PARTE 3: Implementación paso a paso

### Paso 1: Agregar dependencias

**¿Qué agregamos?**
- **Retrofit**: Cliente HTTP type-safe
- **Gson**: Conversión JSON ↔ Kotlin
- **OkHttp Logging**: Ver peticiones en Logcat (debugging)
- **Coil**: Cargar imágenes desde URLs

**¿Dónde?**  
Archivo: `app/build.gradle.kts` (el del módulo app, NO el del proyecto raíz)

**¿Cómo?**
1. Abre `app/build.gradle.kts`
2. Busca el bloque `dependencies { ... }`
3. Agrega al final:

```kotlin
dependencies {
    // ... tus dependencias actuales ...
    
    // Retrofit y Gson
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    
    // Logging (ver peticiones en Logcat)
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
    
    // Coil (cargar imágenes)
    implementation("io.coil-kt:coil-compose:2.5.0")
    
    // ViewModel Compose
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
}
```

4. Click en **"Sync Now"** (barra amarilla arriba)

---

### Paso 2: Permiso de Internet

**¿Por qué?**  
Android bloquea acceso a internet por defecto (seguridad).

**¿Dónde?**  
Archivo: `app/src/main/AndroidManifest.xml`

**¿Cómo?**
Agrega ANTES del tag `<application>`:

```xml
<manifest xmlns:android="http://schemas.android.com/apk/res/android">
    
    <!-- ⬇️ AGREGAR ESTA LÍNEA -->
    <uses-permission android:name="android.permission.INTERNET" />
    
    <application
        ...
```

---

### Paso 3: Crear estructura de paquetes

**¿Por qué?**  
Organizar el código por responsabilidad.

**¿Cómo?**
1. Click derecho en `com.example.duocappmoviles003d/`
2. New → Package
3. Nombre: `network` → Enter
4. Repite para: `repository`

Estructura final:
```
com.example.duocappmoviles003d/
├── catalog/         (ya existe)
├── network/         (nuevo)
├── repository/      (nuevo)
├── login/           (ya existe)
└── signup/          (ya existe)
```

---

### Paso 4: Actualizar modelo Product

**¿Por qué?**  
Tu Product tiene `imageRes: Int` (recurso local).  
La API devuelve `image_url: String` (URL de internet).

**¿Dónde?**  
Archivo: `catalog/Product.kt`

**¿Cómo?**  
Reemplaza TODO el contenido:

```kotlin
package com.example.duocappmoviles003d.catalog

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("id")
    val id: Int,
    
    @SerializedName("name")
    val name: String,
    
    @SerializedName("price")
    val price: Double,
    
    @SerializedName("image_url")  // ← JSON usa snake_case
    val imageUrl: String,          // ← Kotlin usa camelCase
    
    @SerializedName("stock")
    val stock: Int
)
```

**Explicación:**

`@SerializedName` mapea nombres de JSON a Kotlin:

```json
// JSON de Supabase (snake_case)
{
  "image_url": "https://..."
}
```

```kotlin
// Tu clase Kotlin (camelCase)
val imageUrl: String
```

---

### Paso 5: Crear ApiService

**¿Qué resuelve?**  
Define QUÉ endpoints tiene tu API.

**¿Dónde?**  
Crear archivo: `network/ApiService.kt`

**¿Cómo?**
1. Click derecho en `network/`
2. New → Kotlin Class/File
3. Nombre: `ApiService`
4. Tipo: **Interface**

```kotlin
package com.example.duocappmoviles003d.network

import com.example.duocappmoviles003d.catalog.Product
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {
    
    @Headers(
        "apikey: TU_SUPABASE_ANON_KEY",
        "Authorization: Bearer TU_SUPABASE_ANON_KEY"
    )
    @GET("products")
    suspend fun getProducts(): Response<List<Product>>
}
```

**⚠️ IMPORTANTE: Reemplaza `TU_SUPABASE_ANON_KEY`**

Ve a tu Dashboard de Supabase:
1. Settings → API
2. Copia `anon public` key
3. Pégala en AMBOS lugares

**Explicación línea por línea:**

```kotlin
interface ApiService {
```
- `interface` = contrato (Retrofit genera la implementación)

```kotlin
@Headers(
    "apikey: ...",
    "Authorization: Bearer ..."
)
```
- Headers de autenticación de Supabase
- Van en CADA petición

```kotlin
@GET("products")
```
- Método HTTP GET
- Endpoint: `/products`
- Retrofit concatena: `BASE_URL + "products"`

```kotlin
suspend fun getProducts(): Response<List<Product>>
```
- `suspend` = función asíncrona (no bloquea UI)
- `Response<T>` = envuelve la respuesta (código HTTP, body, headers)
- `List<Product>` = lo que esperas recibir

---

### Paso 6: Crear RetrofitClient

**¿Qué resuelve?**  
Configura CÓMO conectarte a la API.

**¿Dónde?**  
Crear archivo: `network/RetrofitClient.kt`

**¿Cómo?**
1. Click derecho en `network/`
2. New → Kotlin Class/File
3. Nombre: `RetrofitClient`
4. Tipo: **Object** (Singleton)

```kotlin
package com.example.duocappmoviles003d.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    
    // ⚠️ REEMPLAZA CON TU URL DE SUPABASE
    private const val BASE_URL = "https://fxmwpkakqzfohuiyuayg.supabase.co/rest/v1/"
    
    // Logger para ver peticiones en Logcat
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    
    // Cliente HTTP con logging
    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()
    
    // Instancia de Retrofit
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    
    // ApiService listo para usar
    val apiService: ApiService = retrofit.create(ApiService::class.java)
}
```

**Explicación:**

**1. `object RetrofitClient`**
- Singleton: una sola instancia en toda la app
- No necesitas `new RetrofitClient()`
- Usas: `RetrofitClient.apiService`

**2. `BASE_URL`**
- ⚠️ DEBE terminar en `/`
- Retrofit concatena: `BASE_URL + endpoint`
- Ejemplo: `https://...supabase.co/rest/v1/` + `products`

**3. `HttpLoggingInterceptor`**
- Muestra peticiones en Logcat
- Nivel `BODY`: muestra request y response completos
- Útil para debugging

**4. `GsonConverterFactory`**
- Convierte JSON → Kotlin automáticamente
- Usa las anotaciones `@SerializedName`

**5. `retrofit.create()`**
- Genera implementación de tu interface
- Tú defines, Retrofit ejecuta

---

### Paso 7: Crear ProductRepository

**¿Qué resuelve?**
- Centraliza la obtención de datos
- Maneja errores de red
- Devuelve `Result<T>` (éxito o error)

**¿Dónde?**  
Crear archivo: `repository/ProductRepository.kt`

**¿Cómo?**
1. Click derecho en `repository/`
2. New → Kotlin Class/File
3. Nombre: `ProductRepository`

```kotlin
package com.example.duocappmoviles003d.repository

import com.example.duocappmoviles003d.catalog.Product
import com.example.duocappmoviles003d.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductRepository {
    
    private val apiService = RetrofitClient.apiService
    
    suspend fun getProducts(): Result<List<Product>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getProducts()
                
                if (response.isSuccessful && response.body() != null) {
                    Result.success(response.body()!!)
                } else {
                    Result.failure(
                        Exception("Error ${response.code()}: ${response.message()}")
                    )
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}
```

**Explicación:**

**1. `suspend fun`**
- Función asíncrona
- Debe llamarse desde coroutine

**2. `withContext(Dispatchers.IO)`**
- Cambia al hilo de red/IO
- NUNCA hagas red en hilo principal (crashea app)

**3. `Result<List<Product>>`**
- Tipo de Kotlin para manejar éxito/error
- `Result.success(data)` o `Result.failure(exception)`

**4. `response.isSuccessful`**
- `true` si código HTTP 200-299
- `false` si 400, 404, 500, etc.

**5. `try-catch`**
- Captura errores de red (sin internet, timeout)
- Devuelve `Result.failure(e)`

---

### Paso 8: Crear CatalogViewModel

**¿Qué resuelve?**
- Llama al Repository
- Gestiona estado de la UI
- Sobrevive a rotaciones de pantalla

**¿Dónde?**  
Crear archivo: `catalog/CatalogViewModel.kt`

**¿Cómo?**
1. Click derecho en `catalog/`
2. New → Kotlin Class/File
3. Nombre: `CatalogViewModel`

```kotlin
package com.example.duocappmoviles003d.catalog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.duocappmoviles003d.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class UiState {
    object Loading : UiState()
    data class Success(val products: List<Product>) : UiState()
    data class Error(val message: String) : UiState()
}

class CatalogViewModel : ViewModel() {
    
    private val repository = ProductRepository()
    
    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()
    
    init {
        loadProducts()
    }
    
    fun loadProducts() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            
            repository.getProducts()
                .onSuccess { products ->
                    _uiState.value = UiState.Success(products)
                }
                .onFailure { exception ->
                    _uiState.value = UiState.Error(
                        exception.message ?: "Error desconocido"
                    )
                }
        }
    }
    
    fun retry() {
        loadProducts()
    }
}
```

**Explicación:**

**1. `sealed class UiState`**
- Como enum pero con datos
- Solo 3 estados posibles: Loading, Success, Error
- Perfecto para `when`

**2. `ViewModel()`**
- Sobrevive a rotaciones
- Se destruye al cerrar pantalla

**3. `MutableStateFlow` vs `StateFlow`**
```kotlin
private val _uiState = MutableStateFlow(...)  // Privado: solo ViewModel modifica
val uiState: StateFlow = _uiState.asStateFlow()  // Público: UI solo lee
```

**4. `viewModelScope.launch`**
- Coroutine que se cancela automáticamente
- Si sales de pantalla, cancela petición

**5. Flujo de estados:**
```
Loading → [petición HTTP] → Success/Error
```

---

### Paso 9: Actualizar CatalogScreen

**¿Qué cambia?**
- Antes: `CatalogScreen(products: List<Product>)`
- Ahora: `CatalogScreen()` carga desde ViewModel

**¿Dónde?**  
Archivo: `catalog/CatalogScreen.kt`

**¿Cómo?**  
Reemplaza TODO el contenido:

```kotlin
package com.example.duocappmoviles003d.catalog

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.duocappmoviles003d.R

@Composable
fun CatalogScreen(viewModel: CatalogViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    
    Box(modifier = Modifier.fillMaxSize()) {
        // Fondo
        Image(
            painter = painterResource(id = R.drawable.fondo_signup),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF0A0118).copy(alpha = 0.75f))
        )
        
        // Renderizar según estado
        when (uiState) {
            is UiState.Loading -> LoadingContent()
            is UiState.Success -> SuccessContent((uiState as UiState.Success).products)
            is UiState.Error -> ErrorContent(
                message = (uiState as UiState.Error).message,
                onRetry = { viewModel.retry() }
            )
        }
    }
}

@Composable
fun LoadingContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgressIndicator(
                color = Color(0xFFFF1EFF),
                strokeWidth = 3.dp
            )
            Spacer(Modifier.height(16.dp))
            Text(
                text = "Cargando productos...",
                color = Color.White,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
fun ErrorContent(message: String, onRetry: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(32.dp)
        ) {
            Text(text = "❌", fontSize = 48.sp)
            Spacer(Modifier.height(16.dp))
            Text(
                text = "Error al cargar productos",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = message,
                color = Color.White.copy(alpha = 0.7f),
                fontSize = 14.sp
            )
            Spacer(Modifier.height(24.dp))
            Button(
                onClick = onRetry,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF1EFF)
                )
            ) {
                Text("Reintentar", color = Color.Black, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun SuccessContent(products: List<Product>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Column(modifier = Modifier.padding(bottom = 20.dp)) {
            Text(
                text = "CATÁLOGO",
                fontSize = 14.sp,
                color = Color(0xFFFF1EFF),
                letterSpacing = 3.sp,
                fontWeight = FontWeight.Black
            )
            Text(
                text = "otakuwear",
                fontSize = 42.sp,
                fontWeight = FontWeight.Black,
                color = Color.White,
                lineHeight = 38.sp
            )
            Text(
                text = "${products.size} productos disponibles",
                fontSize = 13.sp,
                color = Color.White.copy(alpha = 0.5f)
            )
        }
        
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(14.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(products) { product ->
                ProductCard(product)
            }
        }
    }
}

@Composable
fun ProductCard(product: Product) {
    var clicked by remember { mutableStateOf(false) }
    val rotation by animateFloatAsState(
        targetValue = if (clicked) 2f else 0f,
        animationSpec = spring(dampingRatio = 0.5f),
        label = ""
    )
    
    Box(
        modifier = Modifier
            .aspectRatio(0.75f)
            .rotate(rotation)
            .clickable { clicked = !clicked }
    ) {
        // Imagen desde URL
        AsyncImage(
            model = product.imageUrl,
            contentDescription = product.name,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(4.dp)),
            contentScale = ContentScale.Crop
        )
        
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.9f)
                        ),
                        startY = 150f
                    )
                )
        )
        
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(12.dp)
        ) {
            if (product.stock < 5) {
                Text(
                    text = "¡ÚLTIMAS ${product.stock}!",
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Black,
                    color = Color(0xFFFF1EFF),
                    modifier = Modifier
                        .background(Color.Black, RoundedCornerShape(2.dp))
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                )
                Spacer(Modifier.height(6.dp))
            }
            
            Text(
                text = product.name.uppercase(),
                fontSize = 13.sp,
                fontWeight = FontWeight.Black,
                color = Color.White,
                lineHeight = 14.sp
            )
            
            Spacer(Modifier.height(8.dp))
            
            Row(
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "$${product.price.toInt()}",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Black,
                        color = Color.White
                    )
                    Text(
                        text = "CLP",
                        fontSize = 10.sp,
                        color = Color.White.copy(alpha = 0.6f)
                    )
                }
                
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .background(Color(0xFFFF1EFF), RoundedCornerShape(4.dp))
                        .clickable { /* TODO */ },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "+",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Black,
                        color = Color.Black
                    )
                }
            }
        }
        
        Text(
            text = "#${product.id.toString().padStart(3, '0')}",
            fontSize = 11.sp,
            fontWeight = FontWeight.Black,
            color = Color.White.copy(alpha = 0.3f),
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(8.dp)
        )
    }
}
```

**Cambios clave:**

**1. Sin parámetro products:**
```kotlin
// Antes
fun CatalogScreen(products: List<Product>)

// Ahora
fun CatalogScreen(viewModel: CatalogViewModel = viewModel())
```

**2. Observa estado:**
```kotlin
val uiState by viewModel.uiState.collectAsState()
```
- `collectAsState()` = Flow → Compose State
- UI se recompone cuando cambia

**3. Renderizado condicional:**
```kotlin
when (uiState) {
    is UiState.Loading -> LoadingContent()
    is UiState.Success -> SuccessContent(products)
    is UiState.Error -> ErrorContent(msg, retry)
}
```

**4. AsyncImage (Coil):**
```kotlin
// Antes (recurso local)
Image(painter = painterResource(id = R.drawable.naruto))

// Ahora (URL)
AsyncImage(model = product.imageUrl)
```

---

### Paso 10: Actualizar MainActivity

**¿Dónde?**  
Archivo raíz: `MainActivity.kt`

**¿Cómo?**  
Reemplaza TODO el contenido:

```kotlin
package com.example.duocappmoviles003d

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.duocappmoviles003d.catalog.CatalogScreen
import com.example.duocappmoviles003d.ui.theme.DuocAppMoviles003DTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DuocAppMoviles003DTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CatalogScreen()  // ← Sin parámetros
                }
            }
        }
    }
}
```

**¿Qué eliminamos?**
- ❌ Función `CatalogPreview()` con productos hardcodeados
- ❌ Lista `demoProducts`
- ❌ Llamada `CatalogScreen(products = ...)`

---

## ✅ Paso 11: Probar la aplicación

1. **Clean Build**: Build → Clean Project
2. **Rebuild**: Build → Rebuild Project
3. **Run** 🚀

---

## 🔍 Paso 12: Ver logs en Logcat

1. Ejecuta la app
2. Abre Logcat (abajo en Android Studio)
3. Filtra por: `OkHttp`

Deberías ver:

```
D/OkHttp: --> GET https://fxmwpkakqzfohuiyuayg.supabase.co/rest/v1/products
D/OkHttp: apikey: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
D/OkHttp: --> END GET

D/OkHttp: <-- 200 OK (234ms)
D/OkHttp: [
  {
    "id": 1,
    "name": "Polera Anime - Naruto",
    "price": 12990,
    "image_url": "https://picsum.photos/seed/naruto/600/800",
    "stock": 12
  },
  ...
]
```

**Si ves código 200 y JSON, ¡funciona!** 🎉

---

## 🚨 Troubleshooting

### Error 401 Unauthorized

**Síntoma en Logcat:**
```
<-- 401 Unauthorized
{"message":"Invalid API key"}
```

**Solución:**
1. Ve a Supabase Dashboard → Settings → API
2. Copia `anon public` key (NO `service_role`)
3. Reemplázala en `network/ApiService.kt`

---

### Error: Unable to resolve host

**
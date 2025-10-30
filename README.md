# Tomatito — App Móvil (Jetpack Compose + Material 3)

Aplicación Android en Kotlin usando Jetpack Compose y Material 3. Incluye flujo de login con recordatorio de sesión, catálogo con búsqueda y drawer, pantalla de contacto y configuración con cierre de sesión.

## Requisitos
- `Android Studio` (Ladybug o superior recomendado)
- `Gradle Wrapper` incluido en el proyecto
- `JDK 11` (configurado en `app/build.gradle.kts`)
- SDKs:
  - `compileSdk = 36`
  - `targetSdk = 36`
  - `minSdk = 28`

## Instalación
- Clonar el repositorio:
  - `git clone https://github.com/hachimaki-dev/EA2_AppMoviles_DuocUC_PuertoMontt_003D_2025.git`
  - `cd EA2_AppMoviles_DuocUC_PuertoMontt_003D_2025`
- Abrir en Android Studio:
  - File → Open → seleccionar la carpeta del proyecto
  - Esperar sincronización de Gradle
- Ejecutar en un emulador o dispositivo:
  - Click en `Run` (shift+F10) seleccionando la `MainActivity`

### Alternativa por terminal (Windows)
- Desde la carpeta del proyecto:
  - `./gradlew.bat assembleDebug`
  - `./gradlew.bat installDebug`

Si encuentras errores de archivos bloqueados (por ejemplo, no puede borrar `R.jar`), cierra Android Studio y el emulador, borra la carpeta `app\build` manualmente y reintenta. Parar el daemon de Gradle también ayuda: `./gradlew.bat --stop`.

## Funcionalidades
- Login con validación y opción “Recordarme”
  - Muestra `Snackbar` en credenciales incorrectas
  - Guarda preferencia para abrir directamente el catálogo
- Catálogo con búsqueda y feedback
  - `TopAppBar` con campo de búsqueda y acciones
  - `LazyColumn` con items, botón “Agregar al carrito” y “Ver más”
  - `Snackbar` para confirmar acciones
- Drawer de navegación
  - Accesos a `Configuración` y `Contacto`
- Pantalla de Contacto
  - Formulario con validación simple y mensaje de confirmación
- Pantalla de Configuración
  - Botón “Cerrar sesión” que limpia el back stack y vuelve a `Login`
- Tema Material 3
  - Colores dinámicos (Android 12+) y tipografías definidas

## Rutas y Navegación
- Rutas:
  - `LOGIN`, `CATALOG`, `CONTACT`, `SETTINGS`
- Inicio:
  - `NavigationHost` selecciona `startDestination` según `remembered` en `SharedPreferences`
- Flujo:
  - `Login → Catalog`: guarda `recordarme/remembered` y navega con `popUpTo(LOGIN) { inclusive = true }`
  - `Settings → Logout`: pone `remembered = false` y navega a `LOGIN` con `popUpTo(0)`

## Líneas de código clave
- `NavigationHost.kt` — Ruta inicial con preferencias:
```kotlin
val context = LocalContext.current
val prefs = context.getSharedPreferences("auth_prefs", android.content.Context.MODE_PRIVATE)
val remembered = prefs.getBoolean("remembered", false)
val initialRoute = if (remembered) NavigationRoutes.CATALOG else NavigationRoutes.LOGIN

NavHost(
    navController = navController,
    startDestination = initialRoute
) { /* destinos */ }
```

- `LoginScreen.kt` — Validación, navegación y recordatorio:
```kotlin
Scaffold(
    snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
) { /* contenido */ }

if (username == "tomatito" && password == "1234") {
    preferencias.edit()
        .putBoolean("recordarme", rememberMe)
        .putBoolean("remembered", rememberMe)
        .apply()

    navController.navigate(NavigationRoutes.CATALOG) {
        popUpTo(NavigationRoutes.LOGIN) { inclusive = true }
        launchSingleTop = true
    }
} else {
    scope.launch { snackbarHostState.showSnackbar("Usuario o contraseña incorrectos") }
}
```

- `Catalogo.kt` — Búsqueda y feedback:
```kotlin
TopAppBar(
    title = {
        TextField(
            value = searchText,
            onValueChange = { searchText = it },
            singleLine = true,
            placeholder = { Text("Buscar producto") }
        )
    },
    actions = {
        IconButton(onClick = { filterText = searchText }) { /* Search icon */ }
        IconButton(onClick = { navegarHaciaConfiguracion() }) { /* Settings icon */ }
    }
)
```

- `Contacto.kt` — Back AutoMirrored y validación:
```kotlin
Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")

Button(
    onClick = {
        if (nombre.isNotBlank() && correo.isNotBlank() && mensaje.isNotBlank()) {
            enviado = true
        }
    },
    enabled = nombre.isNotBlank() && correo.isNotBlank() && mensaje.isNotBlank()
)
```

- `Theme.kt` — Colores dinámicos y MaterialTheme:
```kotlin
val colorScheme = when {
    dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
        val context = LocalContext.current
        if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
    }
    darkTheme -> DarkColorScheme
    else -> LightColorScheme
}

MaterialTheme(
    colorScheme = colorScheme,
    typography = Typography,
    content = content
)
```

## Estructura del proyecto
- `app/src/main/java/com/example/duocappmoviles003d/`
  - `MainActivity.kt`: punto de entrada de Compose y `NavController`
  - `NavigationHost.kt`: grafo de navegación y rutas
  - `LoginScreen.kt`: login con validaciones y logo
  - `Catalogo.kt`: catálogo con búsqueda y drawer
  - `Contacto.kt`: formulario de contacto
  - `SettingsScreen.kt`: configuración y logout
- `app/src/main/java/com/example/duocappmoviles003d/ui/theme/`
  - `Theme.kt`, `Color.kt`, `Type.kt`: tema Material 3 y tipografías
- `app/src/main/AndroidManifest.xml`: actividad principal y tema de aplicación

## Troubleshooting
- Bloqueo de archivos durante build (`R.jar`, `app/build` no se puede borrar):
  - Cierra Android Studio y el emulador
  - Borra `app\build` manualmente
  - Ejecuta `./gradlew.bat --stop` y reintenta `assembleDebug` / `installDebug`

## Licencia
- Uso académico/educativo (no se incluye licencia explícita en este repo)
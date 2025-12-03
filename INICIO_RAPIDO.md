# 🚀 Inicio Rápido - DigiDex App

## Para Ejecutar la App AHORA (5 minutos)

### Paso 1: Abrir Android Studio
```
1. Abrir Android Studio
2. File → Open
3. Seleccionar carpeta: C:\Users\ISMA\AndroidStudioProjects\EV3
4. Click "Open"
5. Esperar a que Gradle sincronice (barra de progreso abajo)
```

### Paso 2: Configurar Emulador
```
1. Tools → Device Manager
2. Si no tienes emulador:
   - Click "Create Device"
   - Seleccionar "Pixel 5"
   - Seleccionar "API 34" (Android 14)
   - Click "Finish"
3. Click ▶️ (Play) para iniciar el emulador
```

### Paso 3: Ejecutar la App
```
1. Click en el botón verde "Run" (▶️)
   O presiona: Shift + F10
2. Seleccionar el emulador que iniciaste
3. Esperar a que compile e instale (~30 segundos)
4. La app se abrirá automáticamente
```

### Paso 4: Probar la App
```
1. Verás la pantalla de Login
2. Ingresa cualquier email y contraseña (la autenticación es local)
3. Click "Iniciar Sesión"
4. Verás la lista de Digimon cargándose desde la API
5. Click en cualquier Digimon para ver el detalle
6. Navega a "Contacto" y "Nosotros"
```

---

## Para Ejecutar los Tests (2 minutos)

### Opción 1: Desde Android Studio
```
1. Click derecho en la carpeta "test" (app/src/test)
2. Click "Run 'Tests in 'ev3.test''"
3. Ver resultados en la ventana "Run"
```

### Opción 2: Desde Terminal
```bash
# En Android Studio:
1. View → Tool Windows → Terminal
2. Ejecutar:
   ./gradlew test

# Verás:
BUILD SUCCESSFUL in 19s
```

---

## Solución a Problemas Comunes

### ❌ Problema: "Gradle sync failed"
**Solución:**
```
1. File → Invalidate Caches → Invalidate and Restart
2. Esperar a que reinicie
3. Gradle sincronizará automáticamente
```

### ❌ Problema: "SDK not found"
**Solución:**
```
1. File → Project Structure
2. SDK Location → Android SDK Location
3. Seleccionar: C:\Users\ISMA\AppData\Local\Android\Sdk
4. Click "OK"
```

### ❌ Problema: "Emulator no inicia"
**Solución:**
```
1. Tools → Device Manager
2. Click en los 3 puntos del emulador
3. Click "Wipe Data"
4. Click "Cold Boot Now"
```

### ❌ Problema: "App crashes al abrir"
**Solución:**
```
1. Verificar que tienes internet (la app consume una API)
2. View → Tool Windows → Logcat
3. Buscar errores en rojo
4. Verificar que el dispositivo tenga API 24+
```

### ❌ Problema: "No se ven las imágenes de Digimon"
**Solución:**
```
1. Verificar conexión a internet
2. Reiniciar el emulador
3. Verificar en Logcat si hay errores de red
```

---

## Atajos de Teclado Útiles

| Acción | Windows/Linux | Mac |
|--------|---------------|-----|
| Run App | `Shift + F10` | `Ctrl + R` |
| Stop App | `Ctrl + F2` | `Cmd + F2` |
| Build Project | `Ctrl + F9` | `Cmd + F9` |
| Clean Project | - | - |
| Format Code | `Ctrl + Alt + L` | `Cmd + Opt + L` |
| Find File | `Ctrl + Shift + N` | `Cmd + Shift + O` |

---

## Verificación Rápida (Checklist)

Antes de la defensa, verifica:

- [ ] App se ejecuta sin crashes
- [ ] Se ve la lista de Digimon con imágenes
- [ ] Al hacer click en un Digimon se ve el detalle
- [ ] Botones "Contacto" y "Nosotros" funcionan
- [ ] Tests pasan correctamente
- [ ] Emulador tiene internet

---

## Comandos de Terminal

### Compilar
```bash
./gradlew assembleDebug
```

### Ejecutar Tests
```bash
./gradlew test
```

### Limpiar Build
```bash
./gradlew clean
```

### Ver todas las tareas disponibles
```bash
./gradlew tasks
```

---

## Archivos Importantes para Revisar

### Para entender la arquitectura:
1. `app/src/main/java/com/example/ev3/ui/viewmodel/DigimonViewModel.kt`
2. `app/src/main/java/com/example/ev3/data/repository/DigimonRepository.kt`
3. `app/src/main/java/com/example/ev3/data/network/DigimonService.kt`
4. `app/src/main/java/com/example/ev3/ui/screens/MainScreen.kt`

### Para la defensa:
1. `README.md` - Documentación completa
2. `GUIA_DEFENSA.md` - Guía de 15 minutos
3. `ARQUITECTURA_MVVM.md` - Explicación de MVVM
4. `RESUMEN_PROYECTO.md` - Resumen ejecutivo

### Para entender los tests:
1. `app/src/test/java/com/example/ev3/viewmodel/DigimonViewModelTest.kt`
2. `app/src/test/java/com/example/ev3/repository/DigimonRepositoryTest.kt`

---

## URLs Importantes

### API utilizada:
```
https://digi-api.com/
```

### Endpoints:
```
Lista: GET https://digi-api.com/api/v1/digimon?page=0&pageSize=20
Detalle: GET https://digi-api.com/api/v1/digimon/{id}
```

### Puedes probar la API en el navegador:
```
https://digi-api.com/api/v1/digimon
https://digi-api.com/api/v1/digimon/1
```

---

## Flujo de Demostración Sugerido

### Para la defensa (3 minutos):
```
1. Abrir Android Studio ✅
2. Click en Run (▶️) ✅
3. Mostrar pantalla de Login ✅
4. Login → Ver lista de Digimon cargando ✅
5. Click en "Agumon" → Ver detalle completo ✅
6. Back → Volver a la lista ✅
7. Click "Contacto" → Mostrar navegación ✅
8. Back → Volver a la lista ✅
9. Click "Nosotros" → Mostrar navegación ✅
```

### Mientras carga, explicar:
- "La app está consumiendo la API de Digi-API"
- "Usamos Retrofit para las peticiones HTTP"
- "Las imágenes se cargan con Coil"
- "La arquitectura es MVVM con StateFlow"

---

## Información de Contacto del Proyecto

**Proyecto:** DigiDex - Enciclopedia Digital de Digimon
**Tecnología:** Kotlin + Jetpack Compose + MVVM
**API:** https://digi-api.com/
**Estado:** ✅ FUNCIONAL

---

¡Listo para la defensa! 🎉

Si algo no funciona, revisa:
1. RESUMEN_PROYECTO.md - Para entender el proyecto completo
2. GUIA_DEFENSA.md - Para preparar tu presentación
3. ARQUITECTURA_MVVM.md - Para explicar la arquitectura

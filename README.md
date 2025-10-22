# CITT Connect 

**CITT Connect** es una aplicación móvil nativa para Android desarrollada con Jetpack Compose y Kotlin. La plataforma permite a estudiantes gestionar proyectos, tracks y eventos del Centro de Innovación y Transferencia Tecnológica (CITT) de DuocUC.

##  Características Principales

### Gestión de Proyectos
- **Visualización de proyectos asignados** con información detallada
- **Seguimiento de progreso** con indicador visual circular
- **Listado de proyectos del track** con búsqueda integrada
- **Solicitud de creación de proyectos** con formulario completo

### Sistema de Tracks
- 6 tracks disponibles:
  - Ciberseguridad
  - Desarrollo de videojuegos
  - Inteligencia Artificial
  - Robótica
  - Impresión 3D
  - Desarrollo de Software

### Información del Equipo
- **Visualización de integrantes** del proyecto
- **Información del profesor guía** asignado
- **Estadísticas del track** (número de estudiantes)

### Navegación Intuitiva
- **Menú lateral (Menu expandible)** con acceso rápido a todas las secciones
- **Sistema de autenticación** con pantalla de login
- **Navegación fluida** entre pantallas con Navigation Component

##  Tecnologías Utilizadas

- **Kotlin** - Lenguaje principal
- **Jetpack Compose** - UI moderna y declarativa
- **Material Design 3** - Componentes y diseño
- **Navigation Compose** - Gestión de navegación
- **Moqups** - Diseño de UI/UX
- **Discord** - Comunicacion del equipo

## Requisitos Previos

- Android Studio Narwhal | 2025.1.4 o superior
- JDK 24 o superior

## Instalación

1. **Clona el repositorio**
```bash
git clone https://github.com/hachimaki-dev/EA2_AppMoviles_DuocUC_PuertoMontt_003D_2025/tree/ev2/cittconnect.git
cd EA2_AppMoviles_DuocUC_PuertoMontt_003D_2025
```

2. **Abre el proyecto en Android Studio**
   - File → Open → Selecciona la carpeta del proyecto

3. **Sincroniza Gradle**
   - Haz clic en "Sync Now" cuando aparezca el banner

4. **Ejecuta la aplicación**
   - Selecciona un emulador o dispositivo físico
   - Presiona el botón Run 

## Estructura de Navegación

```
Login Screen
    ↓
Main Screen (Menú Principal)
    ├── Projects Screen (Proyectos y Tracks)
    │   └── Create Project Screen (Solicitar Proyecto)
    ├── Events Screen (Próximos Eventos) [En desarrollo]
    └── Settings Screen (Configuración) [En desarrollo]
```

##  Pantallas de la Aplicación

### 1. Pantalla de Login
- Campos de entrada para RUT o Email
- Campo de contraseña con visualización oculta
- Diseño con card elevado y encabezado azul
- Opción de "¿Olvidaste tu Contraseña?" (en desarrollo)

### 2. Menú Principal
- **Proyecto asignado** con nombre destacado
- **Track asignado** con icono representativo
- **Progreso del proyecto** con indicador circular (64%)
- **Profesor guía** con información de contacto
- **Próximos eventos** (sección en desarrollo)
- **Integrantes del equipo** con avatares y nombres

### 3. Pantalla de Proyectos
- Barra de búsqueda con ícono
- Proyecto personal destacado en la parte superior
- Lista de proyectos del track
- Botón flotante (+) para crear nuevos proyectos

### 4. Pantalla de Creación de Proyecto
- **Selector de track** con dropdown menu
- **Campos de entrada:**
  - Nombre del proyecto
  - Descripción (objetivos/propósito)
  - Integrantes (con formato de lista)
- **Validación de formulario** completo
- **Diálogo de confirmación** al enviar

##  Paleta de Colores

```kotlin
AzulOscuroCITT = #0151A1      // Azul institucional
TurquesaCITT = #25EFD2        // Turquesa vibrante
FondoCITT = #F5F7FA            // Fondo claro
TextoPistaCITT = #B4B4B4      // Gris para hints
VerdeProgreso = #4CAF50        // Verde para progreso
AmarilloTarjeta = #FFF9C4     // Amarillo para estadísticas
GrisClaroTexto = #9E9E9E      // Gris para texto secundario
NegroClaroTexto = #424242     // Negro suave para texto principal
RojoSalir = #D32F2F           // Rojo para botón de salir
```

##  Estructura del Proyecto

```
app/src/main/java/com/example/duocappmoviles003d/
├── MainActivity.kt                 # Actividad principal
├── AppNavigation.kt               # Sistema de navegación
├── PantallaLogin.kt               # Pantalla de inicio de sesión
├── PantallaMain.kt                # Menú principal
├── PantallaProyectos.kt           # Lista de proyectos
├── PantallaCrearProyecto.kt       # Formulario de creación
└── ui/theme/
    ├── Color.kt                   # Definición de colores
    ├── Theme.kt                   # Tema de la aplicación
    └── Type.kt                    # Tipografía
```

##  Componentes Reutilizables

### En PantallaMain.kt
- `MenuDrawerContent()` - Menú lateral navegable
- `BotonMenu()` - Botones del drawer personalizados
- `SeccionTitulo()` - Títulos de sección con ícono y divisor
- `InfoItem()` - Tarjetas de información (Track, Profesor)
- `InfoProgreso()` - Indicador circular de progreso
- `IntegranteItem()` - Item de lista de integrantes

### En PantallaProyectos.kt
- `ProyectoItem()` - Item de lista de proyectos con divisor

##  Autenticación

Actualmente, la autenticación es simulada. Los campos aceptan cualquier valor y navegan directamente al menú principal. En una implementación real, aquí se integraría:

- Validación de credenciales
- Integración con API backend
- Almacenamiento seguro de tokens
- Manejo de sesiones

##  Validaciones Implementadas

### Formulario de Creación de Proyecto
- ✅ Todos los campos son obligatorios
- ✅ Validación antes de enviar
- ✅ Mensajes de error claros
- ✅ Confirmación de envío exitoso
- ✅ Retorno automático tras éxito

##  Funcionalidades Pendientes (TODOs)

- [ ] Implementar lógica de recuperación de contraseña
- [ ] Conectar con backend real
- [ ] Implementar pantalla de Eventos Próximos
- [ ] Implementar pantalla de Configuración
- [ ] Agregar persistencia local (DataStore/Room)
- [ ] Implementar ViewModels para manejo de estado
- [ ] Agregar tests unitarios e instrumentados
- [ ] Implementar manejo de estados de carga y error
- [ ] Agregar animaciones de transición
- [ ] Implementar notificaciones push

##  Dependencias Principales

```kotlin
// Jetpack Compose
implementation("androidx.compose.ui:ui")
implementation("androidx.compose.material3:material3")
implementation("androidx.compose.ui.tooling.preview")

// Navigation
implementation("androidx.navigation:navigation-compose")

// Material Icons Extended
implementation("androidx.compose.material:material-icons-extended")

// Lifecycle
implementation("androidx.lifecycle:lifecycle-runtime-ktx")
implementation("androidx.activity:activity-compose")
```

##  Contexto Académico

Este proyecto fue desarrollado como parte del curso de **Aplicaciones Móviles (003D)** en DuocUC. La aplicación simula un sistema real de gestión de proyectos para el CITT (Centro de Innovación y Transferencia Tecnológica).

##  Desarrolladores

- **Estudiantes:** Gustavo Santana Fuentes, Cristobal Valdebenito Paredes y Benjamin Martinez Oyarzo
- **Sección:** 003D
- **Institución:** DuocUC

##  Próximos Pasos

Para continuar el desarrollo:

1. **Backend Integration**
   - Implementar Retrofit para llamadas API
   - Crear repositorios para manejo de datos
   - Implementar autenticación JWT

2. **Arquitectura MVVM**
   - Crear ViewModels para cada pantalla
   - Implementar StateFlow para estados de UI
   - Separar lógica de negocio de la UI

3. **Persistencia Local**
   - Implementar Room para base de datos local
   - Usar DataStore para preferencias de usuario
   - Caché de datos para modo offline

4. **Mejoras de UX**
   - Agregar skeleton screens para carga
   - Implementar pull-to-refresh
   - Añadir animaciones Lottie
   - Mejorar accesibilidad

**Nota:** Este es un proyecto en desarrollo activo. Algunas funcionalidades están en fase de implementación.

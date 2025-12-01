# CITT Connect

**CITT Connect** es una aplicación móvil nativa para Android desarrollada con Jetpack Compose y Kotlin. La plataforma permite a estudiantes gestionar proyectos, tracks y eventos del Centro de Innovación y Transferencia Tecnológica (CITT) de DuocUC.

## Características Principales

### Gestión de Proyectos
- **Visualización de proyectos asignados** con información detallada.
- **Seguimiento de progreso** con indicador visual circular.
- **Listado de proyectos del track** con búsqueda integrada.
- **Solicitud de creación de proyectos** a través de un formulario completo.

### Sistema de Tracks
- 6 tracks disponibles: Ciberseguridad, Desarrollo de videojuegos, Inteligencia Artificial, Robótica, Impresión 3D, y Desarrollo de Software.

### Información del Equipo
- Visualización de integrantes del proyecto.
- Información del profesor guía asignado.
- Estadísticas del track.

### Navegación y Autenticación
- Menú lateral (Drawer) para un acceso rápido a todas las secciones.
- Sistema de autenticación conectado a un backend real.
- Navegación fluida entre pantallas con Navigation Component.

## Tecnologías Utilizadas

- **Kotlin** como lenguaje principal.
- **Jetpack Compose** para una UI moderna y declarativa.
- **Material Design 3** para los componentes y el diseño.
- **Navigation Compose** para la gestión de la navegación.
- **Retrofit** para el consumo de la API REST.
- **MVVM (Model-View-ViewModel)** como patrón de arquitectura.
- **StateFlow** para un manejo de estado reactivo.
- **Supabase** como backend (Base de datos, API REST y Edge Functions).
- **Resend** como servicio SMTP (envio de correos)

## Arquitectura

La aplicación sigue una arquitectura MVVM (Model-View-ViewModel):
- **Vistas (Compose)**: Observan los cambios de estado en los ViewModels y actualizan la UI.
- **ViewModels**: Contienen la lógica de negocio y exponen el estado a través de `StateFlow`.
- **Repositorios**: Abstraen el origen de los datos (en este caso, la API de Supabase).

## Backend

El backend está implementado en **Supabase**, proveyendo:
- Una **base de datos PostgreSQL**.
- Una **API REST** autogenerada para las operaciones CRUD sobre los datos.
- **Edge Functions** (serverless) para lógica de negocio adicional, como el envío de correos electrónicos.

**⚠️ Advertencia de Seguridad:** El proyecto contiene claves de API y tokens de autenticación hardcodeados en el código fuente. Esto es una mala práctica de seguridad y debe ser solucionado antes de pasar a un entorno de producción, por ejemplo, moviendo las claves a un archivo `local.properties` no versionado.

## Requisitos Previos

- Android Studio (versión recomendada: Otter | 2025.2.1 o superior).
- JDK 17 o superior.

## Instalación

1.  **Clona el repositorio:**
    ```bash
    git clone <URL-DEL-REPOSITORIO>
    cd <NOMBRE-DEL-PROYECTO>
    ```
2.  **Abre el proyecto en Android Studio.**
3.  **Sincroniza Gradle** para descargar todas las dependencias.
4.  **Ejecuta la aplicación** en un emulador o dispositivo físico.

## Contexto Académico

Este proyecto fue desarrollado como parte del curso de **Aplicaciones Móviles (003D)** en DuocUC. La aplicación simula un sistema real de gestión de proyectos para el CITT (Centro de Innovación y Transferencia Tecnológica).

## Desarrolladores

- **Estudiantes:** Gustavo Santana Fuentes, Cristobal Valdebenito Paredes y Benjamin Martinez Oyarzo
- **Sección:** 003D
- **Institución:** DuocUC

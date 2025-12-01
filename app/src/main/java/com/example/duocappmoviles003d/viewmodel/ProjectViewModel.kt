package com.example.duocappmoviles003d.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.duocappmoviles003d.model.Proyecto
import com.example.duocappmoviles003d.model.Usuario
import com.example.duocappmoviles003d.model.EmailRequest
import com.example.duocappmoviles003d.repository.ProjectRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProjectViewModel : ViewModel() {

    private val repository = ProjectRepository()

    // Estado para pantallamain
    private val _misProyectos = MutableStateFlow<List<Proyecto>>(emptyList())
    val misProyectos: StateFlow<List<Proyecto>> = _misProyectos.asStateFlow()

    private val _proyectoSeleccionado = MutableStateFlow<Proyecto?>(null)
    val proyectoSeleccionado: StateFlow<Proyecto?> = _proyectoSeleccionado.asStateFlow()

    // Lista de integrantes del proyecto seleccionado
    private val _integrantes = MutableStateFlow<List<Usuario>>(emptyList())
    val integrantes: StateFlow<List<Usuario>> = _integrantes.asStateFlow()

    // Estado para pantallaproyectos
    private val _todosLosProyectos = MutableStateFlow<Map<String, List<Proyecto>>>(emptyMap())
    val todosLosProyectos: StateFlow<Map<String, List<Proyecto>>> = _todosLosProyectos.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    // Carga los proyectos del usuario
    fun cargarProyectosDelUsuario(email: String) {
        viewModelScope.launch {
            _isLoading.value = true

            val resultado = repository.getProyectosPorUsuario(email)

            resultado.onSuccess { lista ->
                _misProyectos.value = lista
                if (lista.isNotEmpty()) {
                    seleccionarProyecto(lista[0])
                }
            }.onFailure {
                println("Error: ${it.message}")
            }

            _isLoading.value = false
        }
    }

    // Seleccion de proyecto
    fun seleccionarProyecto(proyecto: Proyecto) {
        _proyectoSeleccionado.value = proyecto
        cargarMiembros(proyecto.id)
    }

    // Cargar los integrantes del proyecto
    private fun cargarMiembros(projectId: Long) {
        viewModelScope.launch {
            val resultado = repository.getIntegrantesProyecto(projectId)

            resultado.onSuccess { lista ->
                _integrantes.value = lista
            }.onFailure {
                _integrantes.value = emptyList()
            }
        }
    }

    // Cargar todos los proyectos de todos los tracks
    fun cargarTodosLosProyectos() {
        viewModelScope.launch {
            _isLoading.value = true

            val resultado = repository.getAllProyectos()

            resultado.onSuccess { lista ->
                val agrupados = lista.groupBy {
                    it.trackDetails?.nombre ?: "Sin Categoría"
                }
                _todosLosProyectos.value = agrupados
            }

            _isLoading.value = false
        }
    }

    // Enviar solicitud al correo del coordinador
    fun enviarSolicitudCorreo(
        nombre: String, track: String, solicitante: String,
        descripcion: String, integrantes: String,
        onResult: (Boolean) -> Unit
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            val request = EmailRequest(nombre, track, solicitante, descripcion, integrantes)

            val resultado = repository.enviarCorreo(request)

            if (resultado.isSuccess) {
                onResult(true)
            } else {
                onResult(false)
            }

            _isLoading.value = false
        }
    }
}
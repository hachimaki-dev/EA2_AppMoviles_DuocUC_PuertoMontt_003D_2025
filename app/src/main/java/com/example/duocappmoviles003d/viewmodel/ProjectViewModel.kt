package com.example.duocappmoviles003d.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.duocappmoviles003d.model.Proyecto
import com.example.duocappmoviles003d.model.Usuario
import com.example.duocappmoviles003d.network.RetrofitClient
import com.example.duocappmoviles003d.model.EmailRequest
import com.example.duocappmoviles003d.network.FuncionesRetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProjectViewModel : ViewModel() {

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

    // Carga mis proyectos
    fun cargarProyectosDelUsuario(email: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = withContext(Dispatchers.IO) {
                    RetrofitClient.apiService.getMyProjects("eq.$email")
                }
                if (response.isSuccessful && response.body() != null) {
                    val lista = response.body()!!.map { it.proyecto }
                    _misProyectos.value = lista
                    // Selecciona el primero por defecto y carga sus integrantes
                    if (lista.isNotEmpty()) {
                        seleccionarProyecto(lista[0])
                    }
                }
            } catch (e: Exception) { e.printStackTrace() }
            finally { _isLoading.value = false }
        }
    }

    // Selecciona un Proyecto y carga sus integrantes
    fun seleccionarProyecto(proyecto: Proyecto) {
        _proyectoSeleccionado.value = proyecto
        cargarMiembros(proyecto.id)
    }

    private fun cargarMiembros(projectId: Long) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    RetrofitClient.apiService.getProjectMembers("eq.$projectId")
                }
                if (response.isSuccessful && response.body() != null) {
                    // Mapea la respuesta para sacar solo el objeto Usuario
                    _integrantes.value = response.body()!!.map { it.usuario }
                } else {
                    _integrantes.value = emptyList()
                }
            } catch (e: Exception) { e.printStackTrace() }
        }
    }

    // Carga todos los proyectos agrupados por track
    fun cargarTodosLosProyectos() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = withContext(Dispatchers.IO) {
                    RetrofitClient.apiService.getAllProjects()
                }
                if (response.isSuccessful && response.body() != null) {
                    val listaCompleta = response.body()!!

                    // Agrupa la lista por el nombre del track
                    val agrupados = listaCompleta.groupBy {
                        it.trackDetails?.nombre ?: "Sin Categoría"
                    }
                    _todosLosProyectos.value = agrupados
                }
            } catch (e: Exception) { e.printStackTrace() }
            finally { _isLoading.value = false }
        }
    }
    //Envia un correo con la info del proyecto que se quiere crear
    fun enviarSolicitudCorreo(
        nombre: String,
        track: String,
        solicitante: String,
        descripcion: String,
        integrantes: String,
        onResult: (Boolean) -> Unit // Callback para avisar a la UI
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val request = EmailRequest(nombre, track, solicitante, descripcion, integrantes)

                val response = withContext(Dispatchers.IO) {
                    FuncionesRetrofitClient.apiService.sendEmail(request)
                }

                if (response.isSuccessful) {
                    onResult(true)
                } else {
                    println("Error envío: ${response.errorBody()?.string()}")
                    onResult(false)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                onResult(false)
            } finally {
                _isLoading.value = false
            }
        }
    }
}
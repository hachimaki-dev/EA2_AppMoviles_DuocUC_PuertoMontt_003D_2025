package com.example.duocappmoviles003d.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.duocappmoviles003d.model.Proyecto
import com.example.duocappmoviles003d.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProjectViewModel : ViewModel() {

    private val _misProyectos = MutableStateFlow<List<Proyecto>>(emptyList())
    val misProyectos: StateFlow<List<Proyecto>> = _misProyectos.asStateFlow()

    private val _proyectoSeleccionado = MutableStateFlow<Proyecto?>(null)
    val proyectoSeleccionado: StateFlow<Proyecto?> = _proyectoSeleccionado.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    // Agregamos el parámetro 'email' a la función
    fun cargarProyectosDelUsuario(email: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                // Usamos el email que recibimos como parámetro
                val response = withContext(Dispatchers.IO) {
                    RetrofitClient.apiService.getMyProjects("eq.$email")
                }

                if (response.isSuccessful && response.body() != null) {
                    val listaLimpia = response.body()!!.map { it.proyecto }
                    _misProyectos.value = listaLimpia

                    if (listaLimpia.isNotEmpty()) {
                        _proyectoSeleccionado.value = listaLimpia[0]
                    } else {
                        _proyectoSeleccionado.value = null // Limpiar si no hay proyectos
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun seleccionarProyecto(proyecto: Proyecto) {
        _proyectoSeleccionado.value = proyecto
    }
}
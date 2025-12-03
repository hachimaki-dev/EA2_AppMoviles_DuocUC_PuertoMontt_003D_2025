package com.example.ev3.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ev3.data.model.DigimonDetailResponse
import com.example.ev3.data.model.DigimonItem
import com.example.ev3.data.repository.DigimonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class DigimonListUiState(
    val isLoading: Boolean = false,
    val digimonList: List<DigimonItem> = emptyList(),
    val filteredList: List<DigimonItem> = emptyList(),
    val error: String? = null,
    val searchQuery: String = "",
    val currentPage: Int = 0,
    val hasMorePages: Boolean = true
)

data class DigimonDetailUiState(
    val isLoading: Boolean = false,
    val digimon: DigimonDetailResponse? = null,
    val error: String? = null
)

class DigimonViewModel(
    private val repository: DigimonRepository = DigimonRepository()
) : ViewModel() {

    private val _listUiState = MutableStateFlow(DigimonListUiState())
    val listUiState: StateFlow<DigimonListUiState> = _listUiState.asStateFlow()

    private val _detailUiState = MutableStateFlow(DigimonDetailUiState())
    val detailUiState: StateFlow<DigimonDetailUiState> = _detailUiState.asStateFlow()

    private val allDigimon = mutableListOf<DigimonItem>()

    init {
        loadDigimonList()
    }

    fun onSearchQueryChange(query: String) {
        val currentState = _listUiState.value
        val filtered = if (query.isBlank()) {
            currentState.digimonList
        } else {
            currentState.digimonList.filter {
                it.name.contains(query, ignoreCase = true)
            }
        }
        _listUiState.value = currentState.copy(
            searchQuery = query,
            filteredList = filtered
        )
    }

    fun loadDigimonList(page: Int = 0, pageSize: Int = 100) {
        viewModelScope.launch {
            _listUiState.value = _listUiState.value.copy(isLoading = true, error = null)

            repository.fetchDigimonList(page, pageSize)
                .onSuccess { list ->
                    if (page == 0) {
                        allDigimon.clear()
                    }
                    allDigimon.addAll(list)

                    val currentQuery = _listUiState.value.searchQuery
                    val filtered = if (currentQuery.isBlank()) {
                        allDigimon.toList()
                    } else {
                        allDigimon.filter { it.name.contains(currentQuery, ignoreCase = true) }
                    }

                    _listUiState.value = DigimonListUiState(
                        isLoading = false,
                        digimonList = allDigimon.toList(),
                        filteredList = filtered,
                        error = null,
                        searchQuery = currentQuery,
                        currentPage = page,
                        hasMorePages = list.size >= pageSize
                    )
                }
                .onFailure { exception ->
                    _listUiState.value = DigimonListUiState(
                        isLoading = false,
                        digimonList = emptyList(),
                        filteredList = emptyList(),
                        error = exception.message ?: "Error desconocido"
                    )
                }
        }
    }

    fun loadMoreDigimon() {
        val currentState = _listUiState.value
        if (!currentState.isLoading && currentState.hasMorePages) {
            loadDigimonList(currentState.currentPage + 1)
        }
    }

    fun loadDigimonDetail(id: Int) {
        viewModelScope.launch {
            _detailUiState.value = _detailUiState.value.copy(isLoading = true, error = null)

            repository.fetchDigimonDetail(id)
                .onSuccess { detail ->
                    _detailUiState.value = DigimonDetailUiState(
                        isLoading = false,
                        digimon = detail,
                        error = null
                    )
                }
                .onFailure { exception ->
                    _detailUiState.value = DigimonDetailUiState(
                        isLoading = false,
                        digimon = null,
                        error = exception.message ?: "Error desconocido"
                    )
                }
        }
    }

    fun retry() {
        loadDigimonList(0)
    }
}

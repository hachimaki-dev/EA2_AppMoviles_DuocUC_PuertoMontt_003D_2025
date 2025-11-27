package com.example.duocappmoviles003d.ProductCatalogue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.duocappmoviles003d.SupabaseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CatalogueViewModel : ViewModel() {
    private val repository = SupabaseRepository()
    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        loadProducts()
    }

    fun loadProducts() {
        viewModelScope.launch {
            _isLoading.value = true
            _products.value = repository.getProducts()
            _isLoading.value = false
        }
    }
}
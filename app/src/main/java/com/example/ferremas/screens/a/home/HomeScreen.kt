package com.example.ferremas.screens.a.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.ferremas.model.Category
import com.example.ferremas.viewmodels.HomeViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController: NavHostController,
    onProfileClick: () -> Unit,
    onCartClick: () -> Unit,
    viewModel: HomeViewModel = viewModel()
) {

    val products by viewModel.products.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    Scaffold(
        topBar = { MyTopAppBar(onProfileClick, onCartClick) },
        bottomBar = { BottomNavigationBar() }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            // -------------------- SEARCH --------------------
            val searchQuery = remember { mutableStateOf("") }
            val focusManager = LocalFocusManager.current

            SearchBar(
                query = searchQuery.value,
                onQueryChange = { searchQuery.value = it },
                onSearch = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            // -------------------- CATEGORIES --------------------
            SectionTitle(title = "Categorias", "Ver Todo") {
                navController.navigate("Categories")
            }

            val categories: List<Category> = listOf(
                Category(1,"Herramientas", "icon"),
                Category(2, "Dormitorio", "icon")
            )

            val selectedCategory = remember { mutableStateOf(0) }

            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(categories.size) {
                    CategoryChip(
                        icon = categories[it].iconUrl,
                        text = categories[it].name,
                        isSelected = selectedCategory.value == it,
                        onClick = { selectedCategory.value = it }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // -------------------- FEATURED PRODUCTS --------------------
            SectionTitle(title = "Promociones", "Ver Todo") {}

            when {
                isLoading -> {
                    Text("Cargando productos...", modifier = Modifier.padding(16.dp))
                }
                errorMessage != null -> {
                    Text("Error: $errorMessage", modifier = Modifier.padding(16.dp))
                }
                else -> {
                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(products) { product ->
                            FeaturedProductCard(product) { /* on click */ }
                        }
                    }
                }
            }
        }
    }
}

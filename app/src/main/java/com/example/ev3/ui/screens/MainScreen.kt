package com.example.ev3.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.ev3.data.model.DigimonItem
import com.example.ev3.ui.viewmodel.DigimonViewModel

@Composable
fun MainScreen(
    viewModel: DigimonViewModel = viewModel(),
    onOpenContacto: () -> Unit,
    onOpenNosotros: () -> Unit,
    onDigimonClick: (Int) -> Unit = {}
) {
    val uiState by viewModel.listUiState.collectAsState()
    val listState = rememberLazyListState()

    // Detectar scroll para paginación infinita
    val shouldLoadMore = remember {
        derivedStateOf {
            val lastVisibleItem = listState.layoutInfo.visibleItemsInfo.lastOrNull()
            lastVisibleItem != null && lastVisibleItem.index >= uiState.filteredList.size - 5
        }
    }

    LaunchedEffect(shouldLoadMore.value) {
        if (shouldLoadMore.value && uiState.hasMorePages && !uiState.isLoading) {
            viewModel.loadMoreDigimon()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .testTag("MainScreen")
    ) {
        Text(
            text = "DigiDex",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.testTag("MainScreenTitle")
        )

        Spacer(Modifier.height(8.dp))

        // Barra de búsqueda
        OutlinedTextField(
            value = uiState.searchQuery,
            onValueChange = { viewModel.onSearchQueryChange(it) },
            modifier = Modifier
                .fillMaxWidth()
                .testTag("SearchBar"),
            placeholder = { Text("Buscar Digimon...") },
            leadingIcon = {
                Icon(Icons.Default.Search, contentDescription = "Buscar")
            },
            trailingIcon = {
                if (uiState.searchQuery.isNotEmpty()) {
                    IconButton(onClick = { viewModel.onSearchQueryChange("") }) {
                        Icon(Icons.Default.Clear, contentDescription = "Limpiar")
                    }
                }
            },
            singleLine = true
        )

        Spacer(Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = onOpenContacto,
                modifier = Modifier.testTag("ContactoButton")
            ) {
                Text("Contacto")
            }
            Button(
                onClick = onOpenNosotros,
                modifier = Modifier.testTag("NosotrosButton")
            ) {
                Text("Nosotros")
            }
        }

        Spacer(Modifier.height(16.dp))

        // Mostrar contador de resultados
        if (uiState.searchQuery.isNotEmpty()) {
            Text(
                text = "${uiState.filteredList.size} resultado(s) para '${uiState.searchQuery}'",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary
            )
            Spacer(Modifier.height(8.dp))
        } else {
            Text(
                text = "${uiState.digimonList.size} Digimon cargados",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary
            )
            Spacer(Modifier.height(8.dp))
        }

        when {
            uiState.isLoading && uiState.digimonList.isEmpty() -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(modifier = Modifier.testTag("LoadingIndicator"))
                }
            }

            uiState.error != null && uiState.digimonList.isEmpty() -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Error: ${uiState.error}",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.testTag("ErrorText")
                    )
                    Spacer(Modifier.height(16.dp))
                    Button(
                        onClick = { viewModel.retry() },
                        modifier = Modifier.testTag("RetryButton")
                    ) {
                        Text("Reintentar")
                    }
                }
            }

            uiState.filteredList.isEmpty() && uiState.searchQuery.isNotEmpty() -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No se encontraron Digimon con '${uiState.searchQuery}'",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }

            else -> {
                LazyColumn(
                    state = listState,
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.testTag("DigimonList")
                ) {
                    items(uiState.filteredList) { digimon ->
                        DigimonCard(
                            digimon = digimon,
                            onClick = { onDigimonClick(digimon.id) }
                        )
                    }

                    // Indicador de carga al final
                    if (uiState.isLoading) {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DigimonCard(digimon: DigimonItem, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .testTag("DigimonCard_${digimon.id}"),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = digimon.image,
                contentDescription = digimon.name,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .testTag("DigimonImage_${digimon.id}"),
                contentScale = ContentScale.Crop
            )

            Column {
                Text(
                    text = digimon.name,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.testTag("DigimonName_${digimon.id}")
                )
                Text(
                    text = "ID: ${digimon.id}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}

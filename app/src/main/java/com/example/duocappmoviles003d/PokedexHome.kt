package com.example.duocappmoviles003d

import com.example.duocappmoviles003d.viewmodel.PokedexViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.duocappmoviles003d.model.Pokemon
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

// Colores oficiales de tipos Pokémon >:)
val typeColors = mapOf(
    "normal" to Color(0xFFA8A77A),
    "fire" to Color(0xFFEE8130),
    "water" to Color(0xFF6390F0),
    "electric" to Color(0xFFF7D02C),
    "grass" to Color(0xFF7AC74C),
    "ice" to Color(0xFF96D9D6),
    "fighting" to Color(0xFFC22E28),
    "poison" to Color(0xFFA33EA1),
    "ground" to Color(0xFFE2BF65),
    "flying" to Color(0xFFA98FF3),
    "psychic" to Color(0xFFF95587),
    "bug" to Color(0xFFA6B91A),
    "rock" to Color(0xFFB6A136),
    "ghost" to Color(0xFF735797),
    "dragon" to Color(0xFF6F35FC),
    "dark" to Color(0xFF705746),
    "steel" to Color(0xFFB7B7CE),
    "fairy" to Color(0xFFD685AD)
)

@Composable
fun PokedexHomeScreen(
    navController: NavHostController,
    viewModel: PokedexViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        // Fondo gengar :D
        Image(
            painter = painterResource(R.drawable.gengar),
            contentDescription = "Fondo Gengar",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Overlay oscuro
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color(0x88000000), Color(0x55000000))
                    )
                )
        )

        // Contenido principal
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "¡Bienvenido a la Pokédex!",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(16.dp)
            )

            // ---------- Búsqueda ----------
            OutlinedTextField(
                value = state.searchQuery,
                onValueChange = { viewModel.onSearchQueryChange(it) },
                label = { Text("Buscar por nombre o número") },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )

            // ---------- Filtro por Tipo ----------
            if (state.availableTypes.isNotEmpty()) {

                Text(
                    text = "Filtrar por tipo",
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, top = 4.dp, bottom = 2.dp)
                )

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 4.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Chip "Todos"
                    item {
                        val bg = if (state.selectedType == null) Color.White else Color.Transparent
                        val textColor = if (state.selectedType == null) Color.Black else Color.White

                        FilterChip(
                            selected = state.selectedType == null,
                            onClick = { viewModel.onTypeSelected(null) },
                            label = { Text("Todos", color = textColor) },
                            leadingIcon = if (state.selectedType == null) {
                                { Icon(Icons.Default.Done, contentDescription = null, tint = Color.Black) }
                            } else null,
                            colors = FilterChipDefaults.filterChipColors(
                                containerColor = bg,
                                selectedContainerColor = Color.White,
                                labelColor = textColor,
                                selectedLabelColor = Color.Black
                            )
                        )
                    }

                    // Chips dinámicos por tipo
                    items(state.availableTypes) { type ->

                        val color = typeColors[type.lowercase()] ?: Color.Gray
                        val isSelected = state.selectedType == type

                        val bg = if (isSelected) color else Color.Transparent
                        val textColor = if (isSelected) Color.Black else Color.White

                        FilterChip(
                            selected = isSelected,
                            onClick = { viewModel.onTypeSelected(type) },
                            label = {
                                Text(
                                    text = type.replaceFirstChar { it.uppercase() },
                                    color = textColor
                                )
                            },
                            leadingIcon = if (isSelected) {
                                { Icon(Icons.Default.Done, contentDescription = null, tint = Color.Black) }
                            } else null,
                            colors = FilterChipDefaults.filterChipColors(
                                containerColor = bg,
                                selectedContainerColor = bg,
                                labelColor = textColor,
                                selectedLabelColor = textColor
                            ),
                            shape = RoundedCornerShape(20.dp)
                        )
                    }
                }
            }

            // ---------- Paginación ----------
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (state.totalPages > 0)
                        "Página ${state.currentPage + 1} de ${state.totalPages}"
                    else
                        "Página 0 de 0",
                    color = Color.White
                )

                Row {
                    Button(
                        onClick = { viewModel.previousPage() },
                        enabled = state.currentPage > 0
                    ) {
                        Text("Anterior")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = { viewModel.nextPage() },
                        enabled = state.currentPage < state.totalPages - 1
                    ) {
                        Text("Siguiente")
                    }
                }
            }

            // ---------- Contenido principal ----------
            when {
                state.isLoading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = Color(0xFF00FFCC))
                    }
                }

                state.errorMessage != null -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = state.errorMessage ?: "Error desconocido",
                            color = Color.Red
                        )
                    }
                }

                else -> {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(3),
                        contentPadding = PaddingValues(8.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        items(state.visiblePokemon) { pokemon ->
                            PokemonCard(pokemon = pokemon, navController = navController)
                        }
                    }
                }
            }

            // Footer
            Text(
                text = "© 2025 UltiDex",
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun PokemonCard(
    navController: NavHostController,
    pokemon: Pokemon
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable {
                navController.navigate(NavigationRoutes.pokemonDetailRoute(pokemon.name))
            },
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E2F)),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(
                        brush = Brush.radialGradient(
                            colors = listOf(Color(0xFF6C63FF), Color(0xFF1E1E2F))
                        ),
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = pokemon.imageUrl,
                    contentDescription = pokemon.name,
                    modifier = Modifier.size(64.dp),
                    placeholder = painterResource(R.drawable.sustituto),
                    error = painterResource(R.drawable.missingno),
                    contentScale = ContentScale.Fit
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = pokemon.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF00FFCC)
            )
        }
    }
}

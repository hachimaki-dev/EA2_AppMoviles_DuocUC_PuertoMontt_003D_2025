package com.example.ev3.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.ev3.data.model.DigimonDetailResponse
import com.example.ev3.ui.viewmodel.DigimonViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DigimonDetailScreen(
    digimonId: Int,
    viewModel: DigimonViewModel = viewModel(),
    onBackClick: () -> Unit
) {
    val uiState by viewModel.detailUiState.collectAsState()

    LaunchedEffect(digimonId) {
        viewModel.loadDigimonDetail(digimonId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle del Digimon") },
                navigationIcon = {
                    IconButton(
                        onClick = onBackClick,
                        modifier = Modifier.testTag("BackButton")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                }
            )
        }
    ) { padding ->
        when {
            uiState.isLoading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(modifier = Modifier.testTag("DetailLoadingIndicator"))
                }
            }

            uiState.error != null -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Error: ${uiState.error}",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.testTag("DetailErrorText")
                    )
                }
            }

            uiState.digimon != null -> {
                DigimonDetailContent(
                    digimon = uiState.digimon!!,
                    modifier = Modifier.padding(padding)
                )
            }
        }
    }
}

@Composable
fun DigimonDetailContent(digimon: DigimonDetailResponse, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
            .testTag("DigimonDetailContent"),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        if (digimon.images.isNotEmpty()) {
            AsyncImage(
                model = digimon.images.first().href,
                contentDescription = digimon.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .testTag("DigimonDetailImage"),
                contentScale = ContentScale.Crop
            )
        }

        Text(
            text = digimon.name,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.testTag("DigimonDetailName")
        )

        if (digimon.levels.isNotEmpty()) {
            DetailSection(title = "Niveles") {
                digimon.levels.forEach { level ->
                    Text(
                        text = "• ${level.level}",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }

        digimon.types?.let { types ->
            if (types.isNotEmpty()) {
                DetailSection(title = "Tipos") {
                    types.forEach { type ->
                        Text(
                            text = "• ${type.type}",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }

        digimon.attributes?.let { attributes ->
            if (attributes.isNotEmpty()) {
                DetailSection(title = "Atributos") {
                    attributes.forEach { attr ->
                        Text(
                            text = "• ${attr.attribute}",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }

        digimon.fields?.let { fields ->
            if (fields.isNotEmpty()) {
                DetailSection(title = "Campos") {
                    fields.forEach { field ->
                        Text(
                            text = "• ${field.field}",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }

        if (digimon.xAntibody) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Text(
                    text = "✓ Posee X-Antibody",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

@Composable
fun DetailSection(title: String, content: @Composable ColumnScope.() -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(Modifier.height(8.dp))
            content()
        }
    }
}

package com.example.ferremas.screens.home.categories

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ferremas.model.Category
import androidx.compose.foundation.lazy.grid.items

@Composable
fun CategoryScreen(
    navController: NavController,
){
    val categories: List<Category> = listOf(
        Category(1, "Herramientas", "data:image/svg+xml;base64,PHN2Zy"),
        Category(2, "Dormitorio", "asdasd")

    )

    Column {
        if (categories.isEmpty()){
            Box(modifier = Modifier.fillMaxSize().padding(16.dp),
                contentAlignment = Alignment.Center){

                Text(
                    text = "no hay categorias encontradas",
                    style = MaterialTheme.typography.bodyLarge

                )
            }
        }else{
            // titulos categorias
            Text(
                text = "Categories",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(
                    horizontal = 16.dp, vertical = 8.dp
                )
            )

            // grid categorias
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)
            ) {
                items(categories){ category ->
                    CategoryItem(
                        category = category,
                        onClick = {
                            navController.navigate(
                                // manejar el click event en las categorias
                                ""
                            )
                        }
                    )
                }
            }

        }
    }
}
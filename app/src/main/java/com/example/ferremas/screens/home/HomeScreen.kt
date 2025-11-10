package com.example.ferremas.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import com.example.ferremas.R

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.ferremas.model.Category
import com.example.ferremas.model.Product


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavHostController,
               onProfileClick: () -> Unit,
               onCartClick: () -> Unit
){

    Scaffold (
        topBar = {MyTopAppBar(onProfileClick, onCartClick)},
        bottomBar = {BottomNavigationBar()}

    ){
        paddingValues ->

          Column(modifier = Modifier.fillMaxSize().padding(paddingValues)) {

            //search seccion
            val searchQuery = remember { mutableStateOf("") }
            val focusManager = LocalFocusManager.current
            SearchBar(
                query = searchQuery.value,
                onQueryChange = {searchQuery.value = it},
                onSearch = {
                     /**logica**/
                },
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            )



            //search ressults





            //categories
            SectionTitle(title = "Categorias", "Ver Todo") {
                navController.navigate("Categories")
            }

            // CATEGORIAs
            val categories: List <Category> = listOf(
                Category(1,"Herramientas", "data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSIyNCIgaGVpZ2h0PSIyNCIgdmlld0JveD0iMCAwIDI0IDI0Ij48cGF0aCBmaWxsPSJjdXJyZW50Q29sb3IiIGQ9Im0xOCAzbC0zIDNWM0g5QzYuMjQgMyA0IDUuMjQgNCA4aDV2M2g2VjhsMyAzaDJWM3pNOSAxM3Y3YzAgLjU1LjQ1IDEgMSAxaDRjLjU1IDAgMS0uNDUgMS0xdi03eiIvPjwvc3ZnPg=="),
                Category(2, "Dormitorio", "asdasd"),
            )

            // categoria seleccionada
            val selectedCategory = remember { mutableStateOf(0) }

            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ){
                items(categories.size){
                    CategoryChip(
                        icon = categories[it].iconUrl,
                        text = categories[it].name,
                        isSelected = selectedCategory.value == it,
                        onClick = {
                            selectedCategory.value = it
                            /**LOGICA**/
                        }
                    )
                }
            }
              Spacer(modifier = Modifier.height(16.dp))





            //features products
              Spacer(modifier = Modifier.height(16.dp))
              SectionTitle(title = "Promociones", "Ver Todo") {
                  /**todo add navigation**/
              }

              val productList = listOf(
                  Product("1","Taladro", 999.9, imageUrl = R.drawable.traladro),
                  Product("2","Martillo", 13123.9, imageUrl = R.drawable.martillo)

              )

              LazyRow(
                  contentPadding = PaddingValues(horizontal = 16.dp),
                  horizontalArrangement = Arrangement.spacedBy(16.dp)
              ) {
                  items(productList){ product ->

                      FeaturedProductCard(product) { }
                        //** evento de click
                  }
              }


          }

    }
}

@Composable
fun MutableStateOf(x0: String) {
    TODO("Not yet implemented")
}
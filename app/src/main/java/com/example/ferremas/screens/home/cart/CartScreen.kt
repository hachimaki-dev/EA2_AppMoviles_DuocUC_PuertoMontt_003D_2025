package com.example.ferremas.screens.home.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ferremas.R
import com.example.ferremas.model.Product
@Composable
fun CartScreen(){
    var cartItems = listOf(
        Product("3", "Sierra", 999.9, imageUrl = R.drawable.traladro),
    )

    Column(modifier = Modifier.fillMaxSize().padding(16.dp) ) {
        Text(text = "Carrito de compras", style = MaterialTheme.
            typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp))

        if (cartItems.isEmpty()){
            Column(modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {  }

                Text(
                    text = "El carrito está vacío",
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {/**el boton de clcik**/}
                ){
                    Text(text = "Continuar comprando")
                }
        }else{

            LazyColumn (modifier = Modifier.weight(14f)){

                items( cartItems){item ->
                    CardItemCard(
                        item = item,
                        onRemoveItem = {/**aqui se controla el remover item**/}
                    )
                }

            }

            // total y checkout

        }
    }

}

@Composable
fun CardItemCard(onRemoveItem: () -> Unit, item: Product) {
    TODO("Not yet implemented")
}
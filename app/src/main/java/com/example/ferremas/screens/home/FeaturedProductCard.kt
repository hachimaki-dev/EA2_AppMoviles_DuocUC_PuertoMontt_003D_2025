package com.example.ferremas.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.TopStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.ferremas.model.Product

@Composable
fun FeaturedProductCard(product: Product,
                        onProductClick: ()-> Unit){

    Card (
        onClick = onProductClick,
        modifier = Modifier.width(280.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ){
        Box{
            DiscountBadge(
                discountPercentage = 5,
                modifier = Modifier.align(Alignment.TopStart)
                    .padding(8.dp)
                    .zIndex(2f)
                )
            Column() {  }

        }


    }

}
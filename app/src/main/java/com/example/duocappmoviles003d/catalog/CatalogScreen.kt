package com.example.duocappmoviles003d.catalog

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.duocappmoviles003d.R
import kotlin.random.Random

data class Product(
    val id: Int,
    val name: String,
    val price: Double,
    val imageRes: Int,
    val stock: Int = Random.nextInt(3, 25)
)

@Composable
fun CatalogScreen(products: List<Product>) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.fondo_signup),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF0A0118).copy(alpha = 0.75f))
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            //Header
            Column(modifier = Modifier.padding(bottom = 20.dp)) {
                Text(
                    text = "CATÁLOGO",
                    fontSize = 14.sp,
                    color = Color(0xFFFF1EFF),
                    letterSpacing = 3.sp,
                    fontWeight = FontWeight.Black
                )
                Text(
                    text = "otakuwear",
                    fontSize = 42.sp,
                    fontWeight = FontWeight.Black,
                    color = Color.White,
                    lineHeight = 38.sp
                )
                Text(
                    text = "${products.size} productos disponibles",
                    fontSize = 13.sp,
                    color = Color.White.copy(alpha = 0.5f)
                )
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(14.dp),
                horizontalArrangement = Arrangement.spacedBy(14.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(products) { product ->
                    ProductCard(product)
                }
            }
        }
    }
}

@Composable
fun ProductCard(product: Product) {
    var clicked by remember { mutableStateOf(false) }
    val rotation by animateFloatAsState(
        targetValue = if (clicked) 2f else 0f,
        animationSpec = spring(dampingRatio = 0.5f),
        label = ""
    )

    Box(
        modifier = Modifier
            .aspectRatio(0.75f)
            .rotate(rotation)
            .clickable { clicked = !clicked }
    ) {
        // Imagen de fondo
        Image(
            painter = painterResource(id = product.imageRes),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(4.dp)),
            contentScale = ContentScale.Crop
        )

        // Overlay oscuro
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.9f)
                        ),
                        startY = 150f
                    )
                )
        )

        // Info del producto
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(12.dp)
        ) {
            // Stock badge
            if (product.stock < 5) {
                Text(
                    text = "¡ÚLTIMAS ${product.stock}!",
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Black,
                    color = Color(0xFFFF1EFF),
                    modifier = Modifier
                        .background(Color.Black, RoundedCornerShape(2.dp))
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                )
                Spacer(Modifier.height(6.dp))
            }

            Text(
                text = product.name.uppercase(),
                fontSize = 13.sp,
                fontWeight = FontWeight.Black,
                color = Color.White,
                lineHeight = 14.sp
            )

            Spacer(Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "$${product.price}",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Black,
                        color = Color.White
                    )
                    Text(
                        text = "CLP",
                        fontSize = 10.sp,
                        color = Color.White.copy(alpha = 0.6f)
                    )
                }

                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .background(Color(0xFFFF1EFF), RoundedCornerShape(4.dp))
                        .clickable { /* comprar */ },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "+",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Black,
                        color = Color.Black
                    )
                }
            }
        }

        // Número de producto esquina superior
        Text(
            text = "#${product.id.toString().padStart(3, '0')}",
            fontSize = 11.sp,
            fontWeight = FontWeight.Black,
            color = Color.White.copy(alpha = 0.3f),
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(8.dp)
        )
    }
}
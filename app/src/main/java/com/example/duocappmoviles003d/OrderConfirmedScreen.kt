package com.example.duocappmoviles003d



import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.duocappmoviles003d.Login.DarkBackground
import com.example.duocappmoviles003d.Login.PrimaryRed

@Composable
fun OrderConfirmedScreen(
    onNavigateToCatalogue: () -> Unit // Para volver al catálogo
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            Icons.Filled.CheckCircle,
            contentDescription = "Pago Exitoso",
            tint = Color(0xFF4CAF50), // Verde de éxito
            modifier = Modifier.size(96.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "¡Pago Exitoso!",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.ExtraBold,
            color = DarkBackground
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Tu pedido ha sido confirmado. Recibirás tu pedido en la dirección registrada en un plazo de **3 días hábiles**.",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(40.dp))
        Button(
            onClick = onNavigateToCatalogue,
            modifier = Modifier.fillMaxWidth().height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryRed)
        ) {
            Text("Volver al Catálogo", fontWeight = FontWeight.SemiBold)
        }
    }
}
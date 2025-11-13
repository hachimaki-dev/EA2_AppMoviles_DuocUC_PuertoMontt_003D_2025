package com.example.duocappmoviles003d

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.duocappmoviles003d.auth.LoginScreen
import com.example.duocappmoviles003d.catalog.CatalogScreen
import com.example.duocappmoviles003d.catalog.Product
import com.example.duocappmoviles003d.signup.RegisterScreen
import com.example.duocappmoviles003d.signup.SignUpUiState
import com.example.duocappmoviles003d.signup.SignUpViewModel
import com.example.duocappmoviles003d.ui.theme.DuocAppMoviles003DTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
           // RegisterScreen(viewModel = SignUpViewModel() )
            //CatalogPreview()
            LoginScreenPreview()
        }
    }
}


@Composable
@Preview(showBackground = true)
fun CatalogPreview() {
    val demoProducts = listOf(
        Product(1, "Polera Jujutsu Kaisen", 15990.0, R.drawable.polera_jujutsu),
        Product(2, "Polera Demon Slayer", 13990.0, R.drawable.polera_demonslayer),
        Product(3, "Polera Naruto", 14990.0, R.drawable.polera_naruto),
        Product(4, "Polera One Piece", 16990.0, R.drawable.polera_onepiece),
        Product(5, "Polera Attack on Titan", 15990.0, R.drawable.polera_aot),
        Product(6, "Polera Tokyo Ghoul", 14990.0, R.drawable.polera_tokyoghoul),
        Product(7, "Polera My Hero Academia", 13990.0, R.drawable.polera_mha),
        Product(8, "Polera Chainsaw Man", 16990.0, R.drawable.polera_chainsawman)
    )
    CatalogScreen(products = demoProducts)
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(
        onLoginClick = { email, password -> },
        onSignUpClick = { }
    )
}
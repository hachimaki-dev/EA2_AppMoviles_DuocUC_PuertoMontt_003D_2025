package com.example.ferremas.screens.home.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.TextButton
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation

@Composable
fun SignUpScreen(
    onNavigateToLogin: () -> Unit,
    onSignUpSucess: () -> Unit
) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("")}
    var confirmPassword by remember { mutableStateOf("")}

    val authState = true

    var passwordError by remember { mutableStateOf<String?>(null) }


    if (authState){
        onSignUpSucess()
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
    ){
        Text(
            text = "Crear cuenta",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 32.dp)
        )
        OutlinedTextField(
            value = email,
            onValueChange = {email = it},
            label = { Text("Email")},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            singleLine = true
        )
        OutlinedTextField(
            value = password,
            onValueChange = {password = it},
            label = { Text("Contraseña")},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            singleLine = true
        )

        OutlinedTextField(
            value = confirmPassword,
            onValueChange = {confirmPassword = it},
            label = { Text("Confirmar contraseña")},
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            singleLine = true,
            isError = passwordError != null,
            supportingText = {
                passwordError?.let {
                    Text(text = it,
                        color = MaterialTheme.colorScheme.error)
                }
            }
        )

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = {
                if (password != confirmPassword) {
                    passwordError = "Las contraseñas no coinciden"
                } else if (password.length < 6) {
                    passwordError = "La contraseña debe tener al menos 6 caracteres"
                } else {
                    passwordError = null
                    onNavigateToLogin()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            // enabled = email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()
            //     && authState !is AuthState.Loading
        ) {
            Text("Crear cuenta")

            Spacer(Modifier.height(16.dp))

            TextButton(onClick = onNavigateToLogin) {
                Text("Ya tienes una cuenta? Inicia sesion")
            }
        }
    }

}


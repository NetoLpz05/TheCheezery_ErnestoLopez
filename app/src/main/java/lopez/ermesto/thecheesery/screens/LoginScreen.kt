package lopez.ermesto.thecheesery.screens

import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import lopez.ermesto.thecheesery.R
import lopez.ermesto.thecheesery.ui.theme.Brighter_Pink

@Composable
fun LoginScreen(
    innerPadding: PaddingValues = PaddingValues(0.dp),
    onLoginSuccess: () -> Unit = {}
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(innerPadding)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.grupo1),
            contentDescription = "The Cheezery",
            modifier = Modifier
                .fillMaxWidth()
                .height(110.dp)
        )

        Spacer(modifier = Modifier.height(30.dp))

        TextField(
            value = email,
            onValueChange = {
                email = it
                errorMessage = null
            },
            label = { Text("Correo electrónico") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            singleLine = true,
            modifier = Modifier.fillMaxWidth(0.85f)
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = password,
            onValueChange = {
                password = it
                errorMessage = null
            },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true,
            modifier = Modifier.fillMaxWidth(0.85f)
        )

        errorMessage?.let { error ->
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = error,
                color = MaterialTheme.colorScheme.error,
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {
                when {
                    email.isBlank() -> errorMessage = "El correo electrónico no puede estar vacío."
                    !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> errorMessage = "El formato del correo electrónico es incorrecto."
                    password.isBlank() -> errorMessage = "La contraseña no puede estar vacía."
                    else -> onLoginSuccess()
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Brighter_Pink,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(50.dp),
            modifier = Modifier
                .width(140.dp)
                .height(44.dp)
        ) {
            Text("Entrar", fontSize = 16.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    LoginScreen(innerPadding = PaddingValues(10.dp))
}

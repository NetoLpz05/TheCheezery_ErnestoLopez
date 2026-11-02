package lopez.ermesto.thecheesery.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.TextObfuscationMode
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.SecureTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import lopez.ermesto.thecheesery.R
import lopez.ermesto.thecheesery.ui.theme.Brighter_Pink
import org.intellij.lang.annotations.JdkConstants

@Composable
fun LoginScreen(innerPadding: PaddingValues) {
    var user by remember { mutableStateOf("") }
    var password = remember { TextFieldState("")}

    Column(Modifier.fillMaxWidth().fillMaxHeight().padding(innerPadding), horizontalAlignment = Alignment.CenterHorizontally) {

        Image(painter = painterResource(id = R.drawable.grupo1),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth().height(100.dp))

        Spacer(Modifier.size(40.dp))

            TextField(value = user, onValueChange = {it -> user = it},
                label = {
                    Text("Correo Electrónico") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text))

        Spacer(Modifier.size(10.dp))

            SecureTextField(state = password,
                label = {
                    Text("Contraseña")
                }, textObfuscationMode = TextObfuscationMode.RevealLastTyped,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password))

        Spacer(Modifier.size(10.dp))

            Button(onClick = {}, colors = ButtonDefaults.buttonColors(
                containerColor = Brighter_Pink,
                contentColor = Color.White
            ))  {
                Text("Iniciar Sesión")
            }

    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview(){
    LoginScreen(innerPadding = PaddingValues(10.dp))
}
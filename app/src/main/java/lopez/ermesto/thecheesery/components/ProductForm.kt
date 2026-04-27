package lopez.ermesto.thecheesery.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import lopez.ermesto.thecheesery.R

@Composable
fun ProductForm(onSaveProduct: (name:String, price: Float, image: String, description: String, category: String) -> Unit){

    var name by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("")}
    var image by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    val categories = listOf("hot", "cold", "salties", "sweets")
    var expanded by remember { mutableStateOf(false) }
    var selectedCategory by remember { mutableStateOf(categories[0]) }


    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(Modifier.height(30.dp))

        Text("Add a new product", fontSize = 30.sp)

        Spacer(Modifier.height(30.dp))

        OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Name") })

        OutlinedTextField(value = price, onValueChange = { price = it }, label = { Text("Price") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            trailingIcon = {
                Image(painter = painterResource(id = R.drawable.dollar_24), contentDescription = "Dollar")
            })

        OutlinedTextField(value = image, onValueChange = { image = it }, label = { Text("Image") })

        OutlinedTextField(value = description, onValueChange = { description = it }, label = { Text("Description") })

        Spacer(Modifier.height(10.dp))

        Box(modifier = Modifier.width(280.dp)) {
            OutlinedTextField(value = selectedCategory, onValueChange = {}, readOnly = true,
                label = { Text("Category") },
                modifier = Modifier.fillMaxWidth().clickable { expanded = true }, enabled = false)

            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                categories.forEach {
                    DropdownMenuItem(text = { Text(it) },
                        onClick = { selectedCategory = it
                            expanded = false })
                }
            }
        }

        Spacer(Modifier.height(10.dp))
        Button(onClick = {onSaveProduct(name, price.toFloatOrNull() ?: 0f, image, description, selectedCategory)
            name = ""
            price = ""
            image = ""
            description = ""}
        ) {
            Text("Save product")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductFormPreview(){
    ProductForm(onSaveProduct = {name,price,image,description, category -> {}})
}
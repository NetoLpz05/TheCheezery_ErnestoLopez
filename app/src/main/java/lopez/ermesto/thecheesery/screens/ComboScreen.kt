package lopez.ermesto.thecheesery.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import lopez.ermesto.thecheesery.R
import lopez.ermesto.thecheesery.database.relation.ComboWithProducts
import lopez.ermesto.thecheesery.domain.Producto
import lopez.ermesto.thecheesery.ui.theme.*
import lopez.ermesto.thecheesery.viewmodel.CartViewModel
import lopez.ermesto.thecheesery.viewmodel.CheezeryViewModel

@Composable
fun ComboScreen(
    cheezeryViewModel: CheezeryViewModel,
    cartViewModel: CartViewModel,
    onBack: () -> Unit = {},
    onNavigateToCart: () -> Unit = {}
) {
    val combosWithProducts by cheezeryViewModel.combosWithProducts.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Dusty_white)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Text(
                    text = "←",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Less_Purple
                )
            }
            IconButton(
                onClick = onNavigateToCart,
                modifier = Modifier
                    .size(44.dp)
                    .background(Less_Purple, CircleShape)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.cart),
                    contentDescription = "Ver carrito",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        Text(
            text = "Combos",
            fontFamily = HelloBunnyFontFamily,
            fontSize = 88.sp,
            color = Pinky,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        if (combosWithProducts.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No combos available yet.\nAdd some combos!",
                    fontSize = 16.sp,
                    color = Color.Gray,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
            }
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                items(combosWithProducts) { comboWithProducts ->
                    ComboItem(
                        comboWithProducts = comboWithProducts,
                        onAddToCart = {
                            val product = Producto(
                                name = comboWithProducts.combo.name,
                                price = comboWithProducts.combo.price,
                                image = R.drawable.grupo1,
                                description = comboWithProducts.combo.description ?: comboWithProducts.products.joinToString(", ") { it.name }
                            )
                            cartViewModel.addProduct(product)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun ComboItem(
    comboWithProducts: ComboWithProducts,
    onAddToCart: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = comboWithProducts.combo.name,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Less_Purple
                )
                Text(
                    text = "$%.1f".format(comboWithProducts.combo.price),
                    fontWeight = FontWeight.Bold,
                    color = Very_purple,
                    fontSize = 18.sp
                )
            }

            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Includes: " + comboWithProducts.products.joinToString(", ") { it.name },
                fontSize = 12.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = onAddToCart,
                colors = ButtonDefaults.buttonColors(containerColor = Very_purple),
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(text = "Add Combo to Cart", color = Color.White)
            }
        }
    }
}

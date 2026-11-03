package lopez.ermesto.thecheesery.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import lopez.ermesto.thecheesery.R
import lopez.ermesto.thecheesery.ui.theme.*

@Composable
fun MenuScreen(
    onNavigateToCategory: (String) -> Unit = {},
    onNavigateToCart: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Dusty_white)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onNavigateToCart,
                modifier = Modifier
                    .size(50.dp)
                    .background(Less_Purple, CircleShape)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.cart),
                    contentDescription = "Carrito de compras",
                    tint = Color.White,
                    modifier = Modifier.size(28.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Image(
            painter = painterResource(id = R.drawable.grupo2),
            contentDescription = "The Cheezery Logo",
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
        )

        Spacer(modifier = Modifier.height(30.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                MenuButton("Hot drinks", firstGradient) { onNavigateToCategory("hot") }
                MenuButton("Cold drinks", firstGradient) { onNavigateToCategory("cold") }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                MenuButton("Salties", secondGradient) { onNavigateToCategory("salties") }
                MenuButton("Sweets", secondGradient) { onNavigateToCategory("sweets") }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                MenuButton("Combos", thirdGradient) { onNavigateToCategory("combos") }
                MenuButton("Add new product", thirdGradient) { onNavigateToCategory("add") }
            }
        }
    }
}

@Composable
fun MenuButton(text: String, gradient: Brush, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .width(150.dp)
            .height(100.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(gradient)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = Color.White,
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MenuPreview() {
    MenuScreen()
}

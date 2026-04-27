package lopez.ermesto.thecheesery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.*
import lopez.ermesto.thecheesery.screens.AddComboScreen
import lopez.ermesto.thecheesery.screens.AddProductScreen
import lopez.ermesto.thecheesery.screens.CombosScreen
import lopez.ermesto.thecheesery.screens.MenuScreen
import lopez.ermesto.thecheesery.screens.ProductScreen
import lopez.ermesto.thecheesery.screens.WelcomeScreen
import lopez.ermesto.thecheesery.ui.theme.TheCheezeryTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "welcome") {
                composable("welcome") {
                    WelcomeScreen { navController.navigate("menu") }
                }

                composable("menu") {
                    MenuScreen { route ->
                        when (route) {
                            "add" -> navController.navigate("add_product_form")
                            "addCombo" -> navController.navigate("add_combo_form")
                            "combos" -> navController.navigate("combos")
                            else -> navController.navigate("products/$route")
                        }
                    }
                }

                composable("add_product_form") {
                    AddProductScreen(PaddingValues(0.dp))
                }

                composable("add_combo_form") {
                    AddComboScreen(PaddingValues(0.dp))
                }

                composable("combos") {
                    CombosScreen()
                }

                composable("products/{category}") { backStackEntry ->
                    val category = backStackEntry.arguments?.getString("category") ?: ""
                    ProductScreen(category)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(text = "Hello $name!", modifier = modifier)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TheCheezeryTheme {
        Greeting("Android")
    }
}
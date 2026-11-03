package lopez.ermesto.thecheesery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import lopez.ermesto.thecheesery.screens.CartScreen
import lopez.ermesto.thecheesery.screens.LoginScreen
import lopez.ermesto.thecheesery.screens.MenuScreen
import lopez.ermesto.thecheesery.screens.ProductScreen
import lopez.ermesto.thecheesery.screens.WelcomeScreen
import lopez.ermesto.thecheesery.ui.theme.TheCheezeryTheme
import lopez.ermesto.thecheesery.viewmodel.CartViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TheCheezeryTheme {
                TheCheezeryApp()
            }
        }
    }
}

@Composable
fun TheCheezeryApp() {
    val navController = rememberNavController()
    val cartViewModel: CartViewModel = viewModel()

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "welcome",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("welcome") {
                WelcomeScreen(
                    onGetStarted = {
                        navController.navigate("login")
                    }
                )
            }
            composable("login") {
                LoginScreen(
                    onLoginSuccess = {
                        navController.navigate("menu") {
                            popUpTo("welcome") { inclusive = true }
                        }
                    }
                )
            }
            composable("menu") {
                MenuScreen(
                    onNavigateToCategory = { category ->
                        navController.navigate("product/$category")
                    },
                    onNavigateToCart = {
                        navController.navigate("cart")
                    }
                )
            }
            composable("product/{category}") { backStackEntry ->
                val category = backStackEntry.arguments?.getString("category") ?: "hot"
                ProductScreen(
                    category = category,
                    viewModel = cartViewModel,
                    onBack = { navController.popBackStack() },
                    onNavigateToCart = { navController.navigate("cart") }
                )
            }
            composable("cart") {
                CartScreen(
                    viewModel = cartViewModel,
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}

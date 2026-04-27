package lopez.ermesto.thecheesery.viewModel

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import lopez.ermesto.thecheesery.data.CombosDAO
import lopez.ermesto.thecheesery.data.DataBaseHelper
import lopez.ermesto.thecheesery.data.ProductDAO
import lopez.ermesto.thecheesery.domain.Combo
import lopez.ermesto.thecheesery.domain.Producto

class ProductsViewModel(private val dao: ProductDAO, private val combosDao: CombosDAO, private val context: Context) : ViewModel(){
    var productsListState by mutableStateOf(listOf<Producto>())
        private set

    var categoryProductsState by mutableStateOf(listOf<Producto>())
        private set


    var combosListState by mutableStateOf(listOf<Combo>())
        private set


    init {
        getAllProducts()
    }

    fun saveProduct(product: Producto){
        val newProduct = dao.insertProduct(product)
        if (newProduct != -1L){
            Toast.makeText(context, "Producto Guardado", Toast.LENGTH_SHORT).show()
            getAllProducts()
        } else {
            Toast.makeText(context, "Hubo un error al guardar", Toast.LENGTH_SHORT).show()
        }
    }

    fun saveCombo(name: String, price: Float, productIds: List<Int>) {
        val combo = Combo(name = name, price = price)
        val result = combosDao.insertCombo(combo, productIds)
        if (result != -1L) {
            Toast.makeText(context, "Combo guardado", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Error al guardar combo", Toast.LENGTH_SHORT).show()
        }
    }

    fun getAllProducts(){
        productsListState = dao.getAllProducts()
    }

    fun loadProductsByCategory(category: String) {
        categoryProductsState = dao.getProductsByCategory(category)
    }

    fun getAllCombos() {
        combosListState = combosDao.getAllCombos()
    }

    fun getComboWithProducts(comboId: Int) = combosDao.getComboWithProducts(comboId)
}

private var sharedInstance: ProductsViewModel? = null

@Composable
fun rememberProductsViewModel(): ProductsViewModel {
    val context = LocalContext.current.applicationContext
    return remember {
        if (sharedInstance == null) {
            val dbHelper = DataBaseHelper(context)
            val dao = ProductDAO(dbHelper)
            val combosDao = CombosDAO(dbHelper)
            sharedInstance = ProductsViewModel(dao, combosDao, context) // 👈
        }
        sharedInstance!!
    }
}
package lopez.ermesto.thecheesery.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import lopez.ermesto.thecheesery.database.AppDatabase
import lopez.ermesto.thecheesery.database.entity.ComboEntity
import lopez.ermesto.thecheesery.database.entity.ProductComboEntity
import lopez.ermesto.thecheesery.database.entity.ProductEntity
import lopez.ermesto.thecheesery.database.relation.ComboWithProducts
import lopez.ermesto.thecheesery.repository.CheezeryRepository
import lopez.ermesto.thecheesery.data.coldDrinks
import lopez.ermesto.thecheesery.data.hotDrinks
import lopez.ermesto.thecheesery.data.salties
import lopez.ermesto.thecheesery.data.sweets

class CheezeryViewModel(application: Application) : AndroidViewModel(application) {
    private val database = AppDatabase.getInstance(application)
    private val repository = CheezeryRepository(database)

    val allProducts: StateFlow<List<ProductEntity>> = repository.getAllProducts()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val allCombos: StateFlow<List<ComboEntity>> = repository.getAllCombos()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val combosWithProducts: StateFlow<List<ComboWithProducts>> = repository.getCombosWithProducts()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    init {
        seedInitialData()
    }

    private fun seedInitialData() {
        viewModelScope.launch {
            val allStaticProducts = coldDrinks + hotDrinks + sweets + salties
            val entities = allStaticProducts.map { p ->
                ProductEntity(
                    name = p.name,
                    price = p.price,
                    image = p.image,
                    description = p.description
                )
            }
            repository.insertProducts(entities)
        }
    }

    fun addProduct(name: String, price: Float, image: Int, description: String?) {
        viewModelScope.launch {
            repository.insertProduct(
                ProductEntity(
                    name = name,
                    price = price,
                    image = image,
                    description = description
                )
            )
        }
    }

    fun addCombo(name: String, price: Float, description: String?, productIds: List<Int>) {
        viewModelScope.launch {
            val comboId = repository.insertCombo(
                ComboEntity(
                    name = name,
                    price = price,
                    description = description
                )
            ).toInt()
            for (productId in productIds) {
                repository.insertProductCombo(
                    ProductComboEntity(
                        idProduct = productId,
                        idCombo = comboId
                    )
                )
            }
        }
    }
}

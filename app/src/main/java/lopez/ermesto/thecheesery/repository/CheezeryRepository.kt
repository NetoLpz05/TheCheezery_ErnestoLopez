package lopez.ermesto.thecheesery.repository

import kotlinx.coroutines.flow.Flow
import lopez.ermesto.thecheesery.database.AppDatabase
import lopez.ermesto.thecheesery.database.entity.ComboEntity
import lopez.ermesto.thecheesery.database.entity.ProductComboEntity
import lopez.ermesto.thecheesery.database.entity.ProductEntity
import lopez.ermesto.thecheesery.database.relation.ComboWithProducts

class CheezeryRepository(private val database: AppDatabase) {
    private val productDao = database.productDao()
    private val comboDao = database.comboDao()
    private val productComboDao = database.productComboDao()

    suspend fun insertProduct(product: ProductEntity): Long {
        return productDao.insertProduct(product)
    }

    suspend fun insertProducts(products: List<ProductEntity>) {
        productDao.insertProducts(products)
    }

    fun getAllProducts(): Flow<List<ProductEntity>> {
        return productDao.getAllProducts()
    }

    suspend fun getProductById(id: Int): ProductEntity? {
        return productDao.getProductById(productId = id)
    }

    fun searchProducts(name: String): Flow<List<ProductEntity>> {
        return productDao.searchProducts(name)
    }

    suspend fun insertCombo(combo: ComboEntity): Long {
        return comboDao.insertCombo(combo)
    }

    fun getAllCombos(): Flow<List<ComboEntity>> {
        return comboDao.getAllCombos()
    }

    fun getCombosWithProducts(): Flow<List<ComboWithProducts>> {
        return comboDao.getCombosWithProducts()
    }

    suspend fun insertProductCombo(productCombo: ProductComboEntity): Long {
        return productComboDao.insertProductCombo(productCombo)
    }
}

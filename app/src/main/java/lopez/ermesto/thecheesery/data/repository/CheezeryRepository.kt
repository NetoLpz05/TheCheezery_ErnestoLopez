package lopez.ermesto.thecheesery.data.repository

import kotlinx.coroutines.flow.Flow
import lopez.ermesto.thecheesery.data.database.AppDatabase
import lopez.ermesto.thecheesery.data.database.entity.ComboEntity
import lopez.ermesto.thecheesery.data.database.entity.ProductComboEntity
import lopez.ermesto.thecheesery.data.database.entity.ProductEntity
import lopez.ermesto.thecheesery.data.database.relation.ComboWithProducts

class CheezeryRepository(private val database: AppDatabase) {

    private val productDao = database.productDao()
    private val comboDao = database.comboDao()
    private val productComboDao = database.productComboDao()

    // Product Functions
    suspend fun insertProduct(product: ProductEntity): Long {
        return productDao.insertProduct(product)
    }

    suspend fun insertProducts(products: List<ProductEntity>) {
        productDao.insertProducts(products)
    }

    suspend fun updateProduct(product: ProductEntity) {
        productDao.updateProduct(product)
    }

    suspend fun deleteProduct(product: ProductEntity) {
        productDao.deleteProduct(product)
    }

    fun getAllProducts(): Flow<List<ProductEntity>> {
        return productDao.getAllProducts()
    }

    suspend fun getProductById(id: Int): ProductEntity? {
        return productDao.getProductById(id)
    }

    fun searchProducts(name: String): Flow<List<ProductEntity>> {
        return productDao.searchProducts(name)
    }

    // Combo Functions
    suspend fun insertCombo(combo: ComboEntity): Long {
        return comboDao.insertCombo(combo)
    }

    suspend fun updateCombo(combo: ComboEntity) {
        comboDao.updateCombo(combo)
    }

    suspend fun deleteCombo(combo: ComboEntity) {
        comboDao.deleteCombo(combo)
    }

    fun getAllCombos(): Flow<List<ComboEntity>> {
        return comboDao.getAllCombos()
    }

    fun getCombosWithProducts(): Flow<List<ComboWithProducts>> {
        return comboDao.getCombosWithProducts()
    }

    // ProductCombo Functions
    suspend fun insertProductCombo(productCombo: ProductComboEntity): Long {
        return productComboDao.insertProductCombo(productCombo)
    }
}

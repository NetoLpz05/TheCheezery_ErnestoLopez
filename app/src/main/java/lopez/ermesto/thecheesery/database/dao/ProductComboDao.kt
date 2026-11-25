package lopez.ermesto.thecheesery.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import lopez.ermesto.thecheesery.database.entity.ProductComboEntity

@Dao
interface ProductComboDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductCombo(productCombo: ProductComboEntity): Long
}

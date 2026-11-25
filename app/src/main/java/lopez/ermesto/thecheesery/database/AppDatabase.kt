package lopez.ermesto.thecheesery.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import lopez.ermesto.thecheesery.database.dao.ComboDao
import lopez.ermesto.thecheesery.database.dao.ProductComboDao
import lopez.ermesto.thecheesery.database.dao.ProductDao
import lopez.ermesto.thecheesery.database.entity.ComboEntity
import lopez.ermesto.thecheesery.database.entity.ProductComboEntity
import lopez.ermesto.thecheesery.database.entity.ProductEntity

@Database(
    entities = [
        ProductEntity::class,
        ComboEntity::class,
        ProductComboEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun comboDao(): ComboDao
    abstract fun productComboDao(): ProductComboDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "cheezery.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}

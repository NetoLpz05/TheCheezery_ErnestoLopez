package lopez.ermesto.thecheesery.data.database.relation

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import lopez.ermesto.thecheesery.data.database.entity.ComboEntity
import lopez.ermesto.thecheesery.data.database.entity.ProductComboEntity
import lopez.ermesto.thecheesery.data.database.entity.ProductEntity

data class ComboWithProducts(
    @Embedded
    val combo: ComboEntity,

    @Relation(
        parentColumn = "idCombo",
        entityColumn = "idProduct",
        associateBy = Junction(
            value = ProductComboEntity::class,
            parentColumn = "idCombo",
            entityColumn = "idProduct"
        )
    )
    val products: List<ProductEntity>
)

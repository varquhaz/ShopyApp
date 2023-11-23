package com.varqulabs.shopyapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.varqulabs.shopyapp.data.local.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HomeDao {

    @Query("SELECT * FROM ProductEntity")
    fun getAllProducts(): Flow<List<ProductEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(productsEntityList: List<ProductEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: ProductEntity)


}
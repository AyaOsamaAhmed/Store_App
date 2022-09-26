package com.aya.storeapp.data.database

import androidx.room.*
import com.aya.storeapp.domain.model.Store

@Dao
interface StoreDataBaseDao  {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
     suspend fun insertStore(item: StoreBD)


    @Query("SELECT * FROM store ")
    suspend fun getStores(): List<StoreBD>


}
package com.aya.storeapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "store")
data class StoreBD (
    @PrimaryKey(autoGenerate = true)
    val id : Int? = 0 ,

    val title : String? = null,
    val price : Double? = null  ,
    val description : String? = null ,
    val category : String? = null ,
    val image : String? = null

    )
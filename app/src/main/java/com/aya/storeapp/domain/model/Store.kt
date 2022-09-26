package com.aya.storeapp.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Store (
    @SerialName("id")
    val id : Int? = 0 ,
    val title : String? = null,
    val price : Double? = null  ,
    val description : String? = null ,
    val category : String? = null ,
    val image : String? = null,
    val rating : Rate? = null

    )
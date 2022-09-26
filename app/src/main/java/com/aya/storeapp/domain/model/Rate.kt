package com.aya.storeapp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Rate (
    val rate : Double? = 0.0 ,
    val count : Int? = 0
    )
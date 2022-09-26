package com.aya.storeapp.data.remote.apis

import com.aya.storeapp.domain.model.Store
import com.aya.storeapp.domain.response.MainResponse


interface MainApis {

    suspend fun getStores(): ArrayList<Store>



}
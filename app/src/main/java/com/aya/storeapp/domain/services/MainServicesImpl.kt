package com.aya.storeapp.domain.services

import com.aya.storeapp.data.remote.apis.MainApis
import com.aya.storeapp.data.remote.client.KtorClient
import com.aya.storeapp.domain.model.Store
import com.aya.storeapp.domain.response.MainResponse
import io.ktor.client.request.*

object MainServicesImpl : MainApis {

    private val httpClient by lazy {
        KtorClient.getInstance
    }

    override suspend fun getStores():  ArrayList<Store> {
       return httpClient.get(path = "products")
    }




}
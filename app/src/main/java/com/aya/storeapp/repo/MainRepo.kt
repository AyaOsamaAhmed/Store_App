package com.aya.storeapp.repo

import io.ktor.client.features.*
import android.util.Log
import com.aya.storeapp.domain.model.Store
import com.aya.storeapp.domain.response.MainResponse
import com.aya.storeapp.domain.services.MainServicesImpl

object MainRepo {


    suspend fun getAllStores():  ArrayList<Store>?  {
        return try {
            val response =
                MainServicesImpl.getStores()
            Log.d("MainRepo", "stores text : ${response}")
            response
        } catch (e:  ClientRequestException) {
            Log.d("MainRepo", "user exception ${e.message}")
           null
        }
    }







}
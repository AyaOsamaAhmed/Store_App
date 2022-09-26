package com.aya.storeapp.utils


import android.app.Application
import com.aya.storeapp.data.database.StoreDataBase

class App : Application() {

        companion object {
            lateinit var databaseApp: StoreDataBase


        }

        override fun onCreate() {
            super.onCreate()
              databaseApp = StoreDataBase.getInstance(applicationContext)

        }
 }
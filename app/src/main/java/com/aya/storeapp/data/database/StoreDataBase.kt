package com.aya.storeapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aya.storeapp.domain.model.Store

@Database(entities = [StoreBD::class], version = 1,  exportSchema = false)
abstract class StoreDataBase : RoomDatabase() {

    abstract val storeDataBase : StoreDataBaseDao

    companion object {

        @Volatile
        private var INSTANCE: StoreDataBase? = null

        fun getInstance(context: Context): StoreDataBase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        StoreDataBase::class.java,
                        "store_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }

}
}
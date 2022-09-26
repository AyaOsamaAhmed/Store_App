package com.aya.storeapp.ui.viewModel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aya.storeapp.data.database.StoreBD
import com.aya.storeapp.domain.model.Store
import com.aya.storeapp.repo.MainRepo
import com.aya.storeapp.utils.App
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class HomeViewModel : ViewModel() {


    var requestStoreLiveData = MutableLiveData<Any>()
    var requestUserInDatabaseLiveData = MutableLiveData<Any>()
    var storeData : ArrayList<Store> = arrayListOf()


    init {


    }

    fun getAllStores(){
        viewModelScope.launch(Dispatchers.IO) {
            storeData = MainRepo.getAllStores()!!
            requestStoreLiveData.postValue(storeData)
        }
    }
    fun getData(){
        requestStoreLiveData.postValue(storeData)
    }



    fun setUsersInDatabase(store : StoreBD){
        viewModelScope.launch(Dispatchers.IO) {
             App.databaseApp.storeDataBase.insertStore(store)
        }
    }

    fun getUsersInDatabase(){
        viewModelScope.launch(Dispatchers.IO) {
            requestUserInDatabaseLiveData.postValue(App.databaseApp.storeDataBase.getStores())
        }
    }

}
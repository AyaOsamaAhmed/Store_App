package com.aya.storeapp.ui.fragment

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.aya.storeapp.R
import com.aya.storeapp.databinding.HomeFragmentBinding
import com.aya.storeapp.domain.model.Store
import com.aya.storeapp.domain.response.MainResponse
import com.aya.storeapp.ui.adapter.StoreAdapter
import com.aya.storeapp.ui.interfaces.onClick
import com.aya.storeapp.ui.viewModel.HomeViewModel


class HomeFragment : Fragment() , onClick {

    private lateinit var binding: HomeFragmentBinding
    private lateinit var viewModel : HomeViewModel

    private lateinit var adapter : StoreAdapter

    private val navController by lazy {
        val navHostFragment = activity?.supportFragmentManager
            ?.findFragmentById(R.id.homeframelayout) as NavHostFragment
        navHostFragment.navController
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = HomeFragmentBinding.inflate(inflater , container , false)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)


       /* if(isOnline(requireContext())){
            viewModel.getAllStores()
        }else{
           viewModel.getUsersInDatabase()
        }*/
        viewModel.getAllStores()

        viewModel.requestStoreLiveData.observe(viewLifecycleOwner, Observer { it ->
            val data = it as  ArrayList<Store>
            saveUsersInDatabase(data)
            adapter = StoreAdapter(data,this)
            binding.recyclerStores.adapter = adapter
        })

        viewModel.requestUserInDatabaseLiveData.observe(viewLifecycleOwner, Observer { it ->
            val data = it as ArrayList<Store>

            if(data.size == 0){
        var  alerDialogbuilder = AlertDialog.Builder(context)
                alerDialogbuilder.setTitle("Internet / Wifi Connection For first time ! ")
                alerDialogbuilder.setMessage("Turn on Wifi/Internet ")

                alerDialogbuilder.setCancelable(false)
                alerDialogbuilder.setPositiveButton("yes",
                    DialogInterface.OnClickListener { dialogInterface, i ->
                        startActivityForResult(
                            Intent(Settings.ACTION_WIFI_SETTINGS),
                            0
                        )
                    })
            val   alertDialog = alerDialogbuilder.create()
                alertDialog.show()
            }else{
                adapter = StoreAdapter(data,this)
                binding.recyclerStores.adapter = adapter
            }


        })
        return binding.root
    }

    private fun saveUsersInDatabase(data: ArrayList<Store>) {
        repeat(data.size){item ->
        //    viewModel.setUsersInDatabase(data[item])
        }

    }

    override fun onClickDetails(model: Store) {
        navController.navigate(HomeFragmentDirections.actionHomeFragmentToStoreDetailsFragment(model.id!!))
     }


    @RequiresApi(Build.VERSION_CODES.M)
    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
    }


}

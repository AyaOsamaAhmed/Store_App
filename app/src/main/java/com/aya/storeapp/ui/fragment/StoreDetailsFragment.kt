package com.aya.storeapp.ui.fragment

import android.os.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.aya.storeapp.R
import com.aya.storeapp.databinding.StoreDetailsFragmentBinding
import com.aya.storeapp.domain.model.Store
import com.aya.storeapp.ui.adapter.StoreAdapter
import com.aya.storeapp.ui.viewModel.HomeViewModel


class StoreDetailsFragment : Fragment() {

    private lateinit var binding: StoreDetailsFragmentBinding
    private lateinit var viewModel: HomeViewModel


    private val navController by lazy {
        val navHostFragment = activity?.supportFragmentManager
            ?.findFragmentById(R.id.homeframelayout) as NavHostFragment
        navHostFragment.navController
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = StoreDetailsFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)


        var arg = arguments?.let { StoreDetailsFragmentArgs.fromBundle(it) }

        val id = arg?.id

       viewModel.getAllStores()

        viewModel.requestStoreLiveData.observe(viewLifecycleOwner, Observer { it ->
            val data = it as  ArrayList<Store>
           val item = data.filter {
               it.id == id
           }
            binding.model = item[0]
        })

    return binding.root
    }
}

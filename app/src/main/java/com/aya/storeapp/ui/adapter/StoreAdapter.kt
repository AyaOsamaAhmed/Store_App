package com.aya.storeapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.aya.storeapp.domain.model.Store
import com.aya.storeapp.ui.interfaces.onClick
import com.aya.storeapp.R
import com.aya.storeapp.BR
import com.aya.storeapp.databinding.ItemStoreBinding

class StoreAdapter (private val models : List<Store>,
                    private val onClick : onClick
):
    RecyclerView.Adapter<StoreAdapter.ViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemStoreBinding= DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_store, parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = models[position]
          holder.bind(model)

        holder.itemRowBinding.card.setOnClickListener {
            onClick.onClickDetails(model)
        }
    }
    override fun getItemCount(): Int {
        return if (models.isNotEmpty()) models.size else 0
    }


    class ViewHolder(binding: ItemStoreBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var itemRowBinding: ItemStoreBinding = binding
        fun bind(obj: Any?) {
            itemRowBinding.setVariable(BR.model, obj)
            itemRowBinding.executePendingBindings()
        }
    }

}
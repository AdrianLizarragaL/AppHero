package com.example.apphero.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.apphero.R
import com.example.apphero.databinding.HeroItemBinding
import com.example.apphero.model.HeroDataModel
import com.example.apphero.view.fragments.ClickListener
import com.example.apphero.view.viewholder.ItemViewHolder

class ItemsAdapter(private val listener: ClickListener): RecyclerView.Adapter<ItemViewHolder>() {
    private val resource = R.layout.hero_item
    lateinit var context: Context
    private val itemList = mutableListOf<HeroDataModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        context = parent.context
        val layaoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding: HeroItemBinding =
            DataBindingUtil.inflate(layaoutInflater, resource, parent, false)

        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.setItem(itemList[position])
        holder.itemView.setOnClickListener {
            listener.itemSelect(itemList[position])
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun setItems(list: List<HeroDataModel>){
        itemList.clear()
        itemList.addAll(list)
        notifyDataSetChanged()
    }
}
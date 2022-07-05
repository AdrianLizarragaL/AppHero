package com.example.apphero.view.viewholder

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apphero.databinding.HeroItemBinding
import com.example.apphero.model.HeroDataModel

class ItemViewHolder(binding: HeroItemBinding): RecyclerView.ViewHolder(binding.root) {
    private  var binding: HeroItemBinding? = null

    init{
        this.binding = binding
    }

    fun setItem(model: HeroDataModel){
        binding?.let{   view->
            view.name = model.name
            Glide.with(view.root.context).load(model.images.sm).into(view.imgCharacter)
        }
    }
}
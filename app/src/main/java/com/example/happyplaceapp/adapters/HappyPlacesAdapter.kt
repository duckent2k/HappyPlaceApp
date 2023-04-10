package com.example.happyplaceapp.adapters

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.happyplaceapp.databinding.ItemHappyPlaceBinding
import com.example.happyplaceapp.models.HappyPlaceModel

open class HappyPlacesAdapter(
    private val context: Context,
    private var list: ArrayList<HappyPlaceModel>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return MyViewHolder(ItemHappyPlaceBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val model = list[position]

        if (holder is MyViewHolder) {
            holder.civPlaceImage.setImageURI(Uri.parse(model.image))
            holder.tvTitle.text = model.title
            holder.tvDescription.text = model.description
        }
    }

}



private class MyViewHolder(binding: ItemHappyPlaceBinding) : RecyclerView.ViewHolder(binding.root){

    val civPlaceImage = binding.civPlaceImage
    val tvTitle = binding.tvTitle
    val tvDescription = binding.tvDescription

}
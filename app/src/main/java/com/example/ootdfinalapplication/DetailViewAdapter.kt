package com.example.ootdfinalapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ootdfinalapplication.Model.ContentModel
import com.example.ootdfinalapplication.databinding.FragmentEntryBinding
import com.example.ootdfinalapplication.databinding.ItemDetailBinding
import com.example.ootdfinalapplication.viewmodel.EntryViewModel
import com.google.firebase.firestore.FirebaseFirestore

class DetailViewAdapter(private val viewModel: EntryViewModel) : RecyclerView.Adapter<DetailViewAdapter.DetailViewHolder>() {

    var firestore = FirebaseFirestore.getInstance()
    var contentModels = arrayListOf<ContentModel>()

    inner class DetailViewHolder(var binding: ItemDetailBinding) : RecyclerView.ViewHolder(binding.root)

    init {
        firestore.collection("userImages").addSnapshotListener { value, error ->
            contentModels.clear()
            for (item in value!!.documents){
                var contentModel = item.toObject(ContentModel::class.java)
                if (contentModel != null) {
                    contentModels.add(contentModel)
                }
            }
            notifyDataSetChanged()
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        var view = ItemDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DetailViewHolder(view)
    }

    override fun getItemCount(): Int {
        return contentModels.size
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        var contentModel = contentModels[position]

        holder.binding.profileTextview.text = contentModel.userId
        holder.binding.explainTextview.text = contentModel.explain
        Glide.with(holder.itemView.context).load(contentModel.imageUrl).into(holder.binding.contentImageview)
    }
}

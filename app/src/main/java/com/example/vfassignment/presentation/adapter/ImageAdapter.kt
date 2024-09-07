package com.example.vfassignment.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.vfassignment.R
import com.example.vfassignment.domain.Model.ImageData

class ImageAdapter(private var images: List<ImageData>) :
    RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    private var shuffledImages = images

    class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_image, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        if (shuffledImages.isNotEmpty()) {
            val image = shuffledImages[position % shuffledImages.size]
            Glide.with(holder.itemView.context)
                .load(image.imageUrl)
                .into(holder.imageView)
        }
    }

    override fun getItemCount(): Int = Integer.MAX_VALUE // Simulate infinite scrolling

    fun updateImages(newImages: List<ImageData>) {
        images = newImages
        shuffledImages = images.shuffled()
        notifyDataSetChanged()
    }
}


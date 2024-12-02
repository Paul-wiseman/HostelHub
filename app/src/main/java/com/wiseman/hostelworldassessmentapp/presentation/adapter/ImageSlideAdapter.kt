package com.wiseman.hostelworldassessmentapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.wiseman.hostelworldassessmentapp.databinding.ImageSliderItemBinding
import com.wiseman.hostelworldassessmentapp.util.HostelWorldDiffUtil
import com.wiseman.hostelworldassessmentapp.util.loadImage

class ImageSlideAdapter() :
    RecyclerView.Adapter<ImageSlideAdapter.ImageSlideViewHolder>() {
    private var imageList: List<String> = emptyList()

    class ImageSlideViewHolder(val binding: ImageSliderItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun getItemCount() = imageList.size
    private var onImageClickListener: (() -> Unit)? = null


    override fun onBindViewHolder(holder: ImageSlideViewHolder, position: Int) {
        val imageUrl = imageList[position]
        holder.binding.propertyImage.loadImage(imageUrl)
        holder.binding.root.setOnClickListener {
            onImageClickListener?.invoke()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageSlideViewHolder {
        val binding =
            ImageSliderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageSlideViewHolder(binding)
    }


    fun setOnImageClickListener(listener: () -> Unit) {
        this.onImageClickListener = listener
    }

    fun setItemList(newList: List<String>) {
        val itemDiffUtil = HostelWorldDiffUtil(imageList, newList)
        val diffUtilResult = DiffUtil.calculateDiff(itemDiffUtil)
        imageList = newList
        diffUtilResult.dispatchUpdatesTo(this)
    }
}
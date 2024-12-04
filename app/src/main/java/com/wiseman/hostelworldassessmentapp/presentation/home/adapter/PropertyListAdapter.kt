package com.wiseman.hostelworldassessmentapp.presentation.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.wiseman.hostelworldassessmentapp.databinding.PropertyItemLayoutBinding
import com.wiseman.hostelworldassessmentapp.domain.model.Property
import com.wiseman.hostelworldassessmentapp.presentation.adapter.ImageSlideAdapter
import com.wiseman.hostelworldassessmentapp.util.HostelWorldDiffUtil
import com.wiseman.hostelworldassessmentapp.util.animate
import com.wiseman.hostelworldassessmentapp.util.formatPrice
import com.wiseman.hostelworldassessmentapp.util.formatRating

class PropertyListAdapter :
    RecyclerView.Adapter<PropertyListAdapter.PropertyViewHolder>() {
    private var onItemClickListener: ((item: Property) -> Unit)? = null
    private var properties: List<Property> = emptyList()
    private lateinit var viewPagerAdapter: ImageSlideAdapter

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PropertyViewHolder {
        val binding =
            PropertyItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PropertyViewHolder(binding)
    }

    override fun getItemCount() = properties.size


    override fun onBindViewHolder(holder: PropertyViewHolder, position: Int) {
        val currentItem = properties[position]
        holder.bind(currentItem)
        holder.binding.root.setOnClickListener {
            onItemClickListener?.invoke(currentItem)
        }
        animate(holder.binding.propertyPrice, position)
    }

    fun setItemList(newList: List<Property>) {
        val itemDiffUtil = HostelWorldDiffUtil(properties, newList)
        val diffUtilResult = DiffUtil.calculateDiff(itemDiffUtil)
        properties = newList
        diffUtilResult.dispatchUpdatesTo(this)
    }

    inner class PropertyViewHolder(val binding: PropertyItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val featuredBadge = binding.featuredBadge
        private val propertyTitle = binding.propertyTitle
        private val propertyPrice = binding.propertyPrice
        private val propertyRating = binding.propertyRating
        private val imageViewPager = binding.viewpager
        private val imageViewPagerIndicator = binding.indicator

        fun bind(property: Property) {
            featuredBadge.visibility =
                if (property.isFeatured == true) View.VISIBLE else View.INVISIBLE
            propertyTitle.text = property.name
            property.lowestPricePerNight?.let { price ->
                propertyPrice.text = formatPrice(price)
            }
            propertyRating.text = formatRating(property.overallRating)

            property.imagesGallery
                .map { it.imageUrl }
                .let { list ->
                    viewPagerAdapter = ImageSlideAdapter(binding.root.context, list)
                    viewPagerAdapter.setOnImageClickListener {
                        onItemClickListener?.let { it(property) }
                    }
                    imageViewPager.adapter = viewPagerAdapter
                    imageViewPagerIndicator.setViewPager(imageViewPager)
                }
        }

    }

    fun setOnItemClickListener(listener: (item: Property) -> Unit) {
        this.onItemClickListener = listener
    }
}
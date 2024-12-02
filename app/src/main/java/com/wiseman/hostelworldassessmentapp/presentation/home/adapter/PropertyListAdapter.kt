package com.wiseman.hostelworldassessmentapp.presentation.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.wiseman.hostelworldassessmentapp.R
import com.wiseman.hostelworldassessmentapp.databinding.PropertyItemLayoutBinding
import com.wiseman.hostelworldassessmentapp.domain.model.Property
import com.wiseman.hostelworldassessmentapp.presentation.adapter.ImageSlideAdapter
import com.wiseman.hostelworldassessmentapp.util.HostelWorldDiffUtil
import com.wiseman.hostelworldassessmentapp.util.animate
import com.wiseman.hostelworldassessmentapp.util.getCurrencySymbolFromCode
import com.wiseman.hostelworldassessmentapp.util.mapValueToScale

class PropertyListAdapter :
    RecyclerView.Adapter<PropertyListAdapter.AvailablePropertyListViewHolder>() {
    private var onItemClick: ((item: Property) -> Unit)? = null
    private var listItem: List<Property> = emptyList()
    private lateinit var viewPagerAdapter: ImageSlideAdapter

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AvailablePropertyListViewHolder {
        return AvailablePropertyListViewHolder(
            PropertyItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = listItem.size


    override fun onBindViewHolder(holder: AvailablePropertyListViewHolder, position: Int) {
        val currentItem = listItem[position]

        holder.bind(currentItem)
        holder.binding.root.setOnClickListener {
            onItemClick?.let { listener -> listener(listItem[position]) }
        }
        animate(holder.binding.propertyPrice, position)
    }

    fun setItemList(newList: List<Property>) {
        val itemDiffUtil = HostelWorldDiffUtil(listItem, newList)
        val diffUtilResult = DiffUtil.calculateDiff(itemDiffUtil)
        listItem = newList
        diffUtilResult.dispatchUpdatesTo(this)
    }

    inner class AvailablePropertyListViewHolder(val binding: PropertyItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(property: Property) {
            with(binding) {
                featuredBadge.visibility =
                    if (property.isFeatured == true) View.VISIBLE else View.INVISIBLE
                propertyTitle.text = property.name
                propertyPrice.text = String.format(
                    root.context.getString(R.string.price),
                    property.lowestPricePerNight?.currency?.let { getCurrencySymbolFromCode(it) },
                    property.lowestPricePerNight?.value
                )
                propertyRating.text = String.format(
                    root.context.getString(R.string.rating),
                    mapValueToScale(property.overallRating.overall),
                    property.overallRating.numberOfRatings
                )
                property.imagesGallery
                    .map { it.imageUrl }
                    .let { list ->
                        viewPagerAdapter = ImageSlideAdapter(root.context, list)
                        viewPagerAdapter.onItemClicked {
                            onItemClick?.let { it(property) }
                        }
                        viewpager.adapter = viewPagerAdapter
                        indicator.setViewPager(viewpager)
                    }
            }
        }
    }

    fun setOnclickListener(listener: (item: Property) -> Unit) {
        this.onItemClick = listener
    }

}
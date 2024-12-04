package com.wiseman.hostelworldassessmentapp.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.wiseman.hostelworldassessmentapp.databinding.ImageSliderItemBinding
import com.wiseman.hostelworldassessmentapp.util.loadImage

class ImageSlideAdapter(private val context: Context, private var imageList: List<String>) :
    PagerAdapter() {
    override fun getCount() = imageList.size
    private var onImageClickListener: (() -> Unit)? = null


    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): View {
        val binding = ImageSliderItemBinding.inflate(
            LayoutInflater.from(context),
            container,
            false
        )
        val imageUrl = imageList[position]
        binding.propertyImage.loadImage(imageUrl)
        val vp = container as ViewPager
        binding.root.setOnClickListener {
            onImageClickListener?.invoke()
        }
        vp.addView(binding.root, 0)
        return binding.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val vp = container as ViewPager
        val view = `object` as View
        vp.removeView(view)
    }

    fun setOnImageClickListener(listener: () -> Unit) {
        this.onImageClickListener = listener
    }
}
package com.wiseman.hostelworldassessmentapp.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.wiseman.hostelworldassessmentapp.R
import com.wiseman.hostelworldassessmentapp.util.loadImage

class ImageSlideAdapter(private val context: Context, private var imageList: List<String>) :
    PagerAdapter() {
    override fun getCount() = imageList.size
    private var listener:(()->Unit)? = null


    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): View {
        val view: View =
            (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(
                R.layout.image_slider_item,
                container,
                false
            )
        val propertyImage = view.findViewById<ImageView>(R.id.property_image)
        val imageUrl = imageList[position]
        propertyImage.loadImage(imageUrl)
        val vp = container as ViewPager
        view.setOnClickListener {
            listener?.invoke()
        }
        vp.addView(view, 0)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val vp = container as ViewPager
        val view = `object` as View
        vp.removeView(view)
    }

    fun onItemClicked(listener:()->Unit){
        this.listener = listener
    }
}
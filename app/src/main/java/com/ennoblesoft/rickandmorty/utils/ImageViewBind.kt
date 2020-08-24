package com.ennoblesoft.rickandmorty.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop

object ImageViewBind {

    @JvmStatic
    @BindingAdapter("android:photoUrl")
    fun showImageFromUrlUsingGlide(imageView: ImageView, url: String? = null) {
        if (url==null) return
        Glide.with(imageView.context)
            .load(url)
            .transform(CircleCrop())
            .into(imageView)
    }

}
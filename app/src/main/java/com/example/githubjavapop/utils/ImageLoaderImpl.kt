package com.example.githubjavapop.utils

import android.widget.ImageView
import com.squareup.picasso.Picasso

class ImageLoaderImpl: ImageLoader {
    override fun loadPicasso(url: String, view: ImageView) {
        Picasso.get().load(url).into(view)
    }

}
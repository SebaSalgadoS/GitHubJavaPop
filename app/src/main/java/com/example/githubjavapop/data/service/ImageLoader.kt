package com.example.githubjavapop.data.service

import android.widget.ImageView
import com.squareup.picasso.Picasso

interface ImageLoader {
    fun loadPicasso(url: String, view : ImageView)
}

class ImageLoaderImpl: ImageLoader{
    override fun loadPicasso(url: String, view: ImageView) {
        Picasso.get().load(url).into(view)
    }

}
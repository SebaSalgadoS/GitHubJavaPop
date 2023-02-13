package com.example.githubjavapop.utils

import android.widget.ImageView

interface ImageLoader {
    fun loadPicasso(url: String, view : ImageView)
}


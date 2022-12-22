package com.example.githubjavapop.data.model.retrofit

import com.google.gson.annotations.SerializedName

data class UserOwner(
    @SerializedName("id") val ownerId: Int,
    @SerializedName("login") val ownerName: String,
    @SerializedName("avatar_url") val ownerAvatar: String
)

package com.example.githubjavapop.data.model.retrofit

import com.google.gson.annotations.SerializedName

data class UserOwner(
    @SerializedName("id") val pullOwnerId: Int,
    @SerializedName("login") val pullOwnerName: String,
    @SerializedName("avatar_url") val pullOwnerAvatar: String
)

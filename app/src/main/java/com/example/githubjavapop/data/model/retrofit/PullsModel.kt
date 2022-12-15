package com.example.githubjavapop.data.model.retrofit

import com.google.gson.annotations.SerializedName

data class PullsModel(
    @SerializedName("id") val idPull: Int,
    @SerializedName("title") val pullTitle: String,
    @SerializedName("body") val pullBody: String,
    @SerializedName("create_at") val pullDate: String
)

data class pullOwner(
    @SerializedName("id") val pullOwnerId: Int,
    @SerializedName("login") val pullOwnerName: String,
    @SerializedName("avatar_url") val pullOwnerAvatar: String
)
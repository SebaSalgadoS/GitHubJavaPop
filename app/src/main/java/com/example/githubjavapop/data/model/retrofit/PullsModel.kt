package com.example.githubjavapop.data.model.retrofit

import com.google.gson.annotations.SerializedName

data class PullsModel(
    @SerializedName("title") val pullTitle: String,
    @SerializedName("body") val pullBody: String,
    @SerializedName("create_at") val pullDate: String,
    @SerializedName("url") val urlPull: String,
    @SerializedName("owner") val pullOwner: UserOwner
)

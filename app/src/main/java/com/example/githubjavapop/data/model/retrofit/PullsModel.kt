package com.example.githubjavapop.data.model.retrofit

import com.google.gson.annotations.SerializedName

data class PullsModel(
    @SerializedName("title") val pullTitle: String,
    @SerializedName("body") val pullBody: String,
    @SerializedName("created_at") val pullDate: String,
    @SerializedName("html_url") val urlPull: String,
    @SerializedName("user") val pullOwner: UserOwner,
    @SerializedName("state") val pullStatus: String
)

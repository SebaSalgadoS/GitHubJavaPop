package com.example.githubjavapop.data.model.retrofit

import com.google.gson.annotations.SerializedName

data class RepoModel(
    @SerializedName("incomplete_results") val incomplete_results: Boolean,
    @SerializedName("items") val items: List<RepoItems>,
    @SerializedName("total_count") val total_count: Int
)
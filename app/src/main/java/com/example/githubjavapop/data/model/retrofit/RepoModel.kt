package com.example.githubjavapop.data.model.retrofit

import com.google.gson.annotations.SerializedName

data class RepoModel(
    @SerializedName("id") val idRepo: Int,
    @SerializedName("name") val repoName: String,
    @SerializedName("description") val repoDescription: String,
    @SerializedName("forks_count") val forks: Int,
    @SerializedName("stargazers_count") val stars: Int,
    @SerializedName("owner") val repoOwner: RepoOwner
)

data class RepoOwner(
    @SerializedName("id") val repoOwnerId: Int,
    @SerializedName("login") val repoOwnerName: String,
    @SerializedName("avatar_url") val repoOwnerAvatar: String
)
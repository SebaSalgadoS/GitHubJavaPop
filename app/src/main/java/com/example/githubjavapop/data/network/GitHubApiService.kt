package com.example.githubjavapop.data.network


import com.example.githubjavapop.data.model.retrofit.PullsModel
import com.example.githubjavapop.data.model.retrofit.RepoItems
import com.example.githubjavapop.data.model.retrofit.RepoModel
import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApiService {

    @GET("repos/{user}/{repo}/pulls")
    suspend fun getPullRequest(@Path("user") user: String, @Path("repo") repo: String): Response<List<PullsModel>>

    @GET("search/repositories?q=language:Java&sort=stars")
    suspend fun getAllRepositories(): Response<RepoModel>

}
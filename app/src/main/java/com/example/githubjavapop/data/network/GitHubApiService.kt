package com.example.githubjavapop.data.network

import com.example.githubjavapop.data.model.retrofit.PullsModel
import com.example.githubjavapop.data.model.retrofit.RepoModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApiService {

    @GET("search/repositories?q=language:Java&sort=stars")
    suspend fun getRepositories(): Response<List<RepoModel>>

    @GET("repos/{user}/{repo}/pulls")
    suspend fun getPullRequest(@Path("user") user: String, @Path("repo") repo: String): Response<List<PullsModel>>

}
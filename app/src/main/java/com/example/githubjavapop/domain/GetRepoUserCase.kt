package com.example.githubjavapop.domain

import android.widget.Toast
import com.example.githubjavapop.data.model.retrofit.Repo
import com.example.githubjavapop.data.model.retrofit.RepoModel
import com.example.githubjavapop.data.network.GitHubApiService
import com.example.githubjavapop.di.ApiRepoModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call

class GetRepoUserCase {

    suspend fun getRepository(): RepoModel? {
        val repoCallBack: GitHubApiService = ApiRepoModule.retrofit(ApiRepoModule.baseUrl()).create(GitHubApiService::class.java)
        val callResult: Call<RepoModel> = repoCallBack.getRepositories()
        val repoResult: RepoModel? = callResult.execute().body()
        return repoResult
    }
}
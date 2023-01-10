package com.example.githubjavapop.domain


import com.example.githubjavapop.data.model.retrofit.RepoModel
import com.example.githubjavapop.data.network.GitHubApiService
import com.example.githubjavapop.di.ApiRepoModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import javax.inject.Inject

class GetRepoUserCase@Inject constructor() {

    suspend fun getRepository(): RepoModel? {
        return withContext(Dispatchers.IO){
            val repoCallBack: GitHubApiService = ApiRepoModule.retrofit(ApiRepoModule.baseUrl()).create(GitHubApiService::class.java)
            val callResult: Call<RepoModel> = repoCallBack.getRepositories()
            val repoResult: RepoModel? = callResult.execute().body()
            repoResult
        }
    }
}
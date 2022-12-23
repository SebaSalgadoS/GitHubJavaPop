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

    // cambiar los nombres a ingles y usar nombre mas descriptivos

    suspend fun traerRepos(): RepoModel? {
        val llamada: GitHubApiService = ApiRepoModule.retrofit(ApiRepoModule.baseUrl()).create(GitHubApiService::class.java)
        val resultado: Call<RepoModel> = llamada.getRepositories()
        val p: RepoModel? = resultado.execute().body()
        return p
    }
}
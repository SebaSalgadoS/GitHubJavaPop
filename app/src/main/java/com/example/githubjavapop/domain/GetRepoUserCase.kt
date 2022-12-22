package com.example.githubjavapop.domain

import android.widget.Toast
import com.example.githubjavapop.data.model.retrofit.Repo
import com.example.githubjavapop.data.model.retrofit.RepoModel
import com.example.githubjavapop.data.network.GitHubApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetRepoUserCase(private val repoAPi: GitHubApiService) {

    suspend fun getAllRepos() {
        return withContext(Dispatchers.IO){
            val response = repoAPi.getRepositories()
            if(response.isSuccessful){
                val repos = response.body()?: emptyList()
                repos
            }
        }
    }
}
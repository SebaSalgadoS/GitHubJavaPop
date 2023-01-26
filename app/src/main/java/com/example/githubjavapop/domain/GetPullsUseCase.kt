package com.example.githubjavapop.domain

import android.util.Log
import com.example.githubjavapop.data.model.retrofit.PullsModel
import com.example.githubjavapop.data.network.GitHubApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetPullsUseCase@Inject constructor(private val apiService: GitHubApiService) {

    suspend fun loadPullList(repoUser: String , repoName: String): List<PullsModel>{
        return withContext(Dispatchers.IO){
            val response = apiService.getPullRequest(repoUser, repoName)
            if (response.isSuccessful){
                val repo = response.body()?: emptyList()
                repo
            }else{
                Log.e("ERROR CARGAR", response.errorBody().toString())
                emptyList()
            }

        }
    }
}
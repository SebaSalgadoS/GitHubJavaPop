package com.example.githubjavapop.domain

import android.util.Log
import com.example.githubjavapop.data.model.ApiState
import com.example.githubjavapop.data.model.retrofit.PullsModel
import com.example.githubjavapop.data.model.retrofit.RepoItems
import com.example.githubjavapop.data.network.GitHubApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class GetPullsUseCase@Inject constructor(private val apiService: GitHubApiService) {

    suspend fun loadPullList(user: String , repo: String): ApiState<List<PullsModel>, String>{
        return withContext(Dispatchers.IO){
            val response = apiService.getPullRequest(
                user = user,
                repo = repo)
            if (response.isSuccessful){
                val pulls = response.body()?: emptyList()
                if (pulls.isEmpty()){
                    ApiState.Error("This repository does not have pull requests")
                }else{
                    ApiState.Success(pulls)
                }
            }else{
                ApiState.Error("ERROR loading pulls request")
            }
        }
    }

}
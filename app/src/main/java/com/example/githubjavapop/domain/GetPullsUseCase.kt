package com.example.githubjavapop.domain

import com.example.githubjavapop.data.model.ApiState
import com.example.githubjavapop.data.model.retrofit.PullsModel
import com.example.githubjavapop.data.network.GitHubApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
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
                    ApiState.Error("Error lista de pulls vacia")
                }else{
                    ApiState.Success(pulls)
                }
            }else{
                ApiState.Error("Error al cargar")
            }

        }
    }
}
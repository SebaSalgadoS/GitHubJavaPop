package com.example.githubjavapop.domain


import com.example.githubjavapop.data.model.ApiState
import com.example.githubjavapop.data.model.retrofit.RepoItems
import com.example.githubjavapop.data.network.GitHubApiService
import com.example.githubjavapop.utils.apiResponse
import com.example.githubjavapop.utils.toRepoMapperResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetRepoUseCase@Inject constructor(private val apiService: GitHubApiService) {

    suspend fun loadList(): ApiState<List<RepoItems>, String>{
        return withContext(Dispatchers.IO){
            val response = apiService.getAllRepositories()
            response.toRepoMapperResponse().apiResponse()
        }
    }

}
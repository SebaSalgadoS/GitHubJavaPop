package com.example.githubjavapop.domain


import android.util.Log
import com.example.githubjavapop.data.model.ApiState
import com.example.githubjavapop.data.model.retrofit.RepoItems
import com.example.githubjavapop.data.network.GitHubApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetRepoUseCase@Inject constructor(private val apiService: GitHubApiService) {


    /*
    //TODO revisar la implementacion del viewModel en esta funcion

     fun loadList2(): List<RepoItems>{
        var respuesta = emptyList<RepoItems>()
         CoroutineScope(Dispatchers.IO).launch{
            kotlin.runCatching {
                apiService.getAllRepositories()
            }.onSuccess { response ->
                respuesta = response.body()?.items ?: emptyList()
                Log.e("DATA SUCCCESS", response.body()?.items.toString())
            }.onFailure {
                Log.e("ERROR CARGAR", it.toString())
            }
        }
        return respuesta
    }
     */

    suspend fun loadList(): ApiState<List<RepoItems>, String>{
        return withContext(Dispatchers.IO){
            val response = apiService.getAllRepositories()
            if (response.isSuccessful){
                val repo = response.body()?.items ?: emptyList<RepoItems>()
                if (repo.isEmpty()){
                    ApiState.Error("Error Lista Vacia")
                }else{
                    ApiState.Success(repo)
                }
            }else{
                Log.e("ERROR CARGAR", response.errorBody().toString())
                ApiState.Error("Error al Cargar")
            }
        }
    }




}
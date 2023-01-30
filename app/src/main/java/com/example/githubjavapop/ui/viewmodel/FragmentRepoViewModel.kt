package com.example.githubjavapop.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubjavapop.data.model.ApiState
import com.example.githubjavapop.data.model.retrofit.RepoItems
import com.example.githubjavapop.domain.GetRepoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FragmentRepoViewModel @Inject constructor(val interactor: GetRepoUseCase): ViewModel() {

    val repositoryModel = MutableLiveData<ApiState<List<RepoItems>, String>>()

    fun getAllRepos(){
        viewModelScope.launch{
            repositoryModel.postValue(interactor.loadList())
            println("DATA ITERACTOR" + interactor.loadList())
        }

    }
}
package com.example.githubjavapop.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubjavapop.data.model.retrofit.RepoModel
import com.example.githubjavapop.domain.GetRepoUserCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FragmentRepoViewModel @Inject constructor(val interactor: GetRepoUserCase): ViewModel() {

    val repositoryModel = MutableLiveData<RepoModel>()

    fun getAllRepos(){
        viewModelScope.launch{
            repositoryModel.postValue(interactor.getRepository())
        }

    }
}
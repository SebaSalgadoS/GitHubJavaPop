package com.example.githubjavapop.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubjavapop.data.model.ApiState
import com.example.githubjavapop.data.model.retrofit.PullsModel
import com.example.githubjavapop.domain.GetPullsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FragmentPullsViewModel @Inject constructor(private val data: GetPullsUseCase) : ViewModel() {

    val pullsRequestModel = MutableLiveData<ApiState<List<PullsModel>, String>>()

    fun getAllPulls(user: String, repo: String) {
        viewModelScope.launch {
            pullsRequestModel.postValue(data.loadPullList(user = user, repo = repo))
            println("DATA PULL VIEW MODEL" + data.loadPullList(user = user, repo = repo).toString())
        }
    }
}
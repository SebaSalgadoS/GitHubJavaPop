package com.example.githubjavapop.utils

import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.githubjavapop.data.model.ApiState
import com.example.githubjavapop.data.model.retrofit.RepoItems
import com.example.githubjavapop.data.model.retrofit.RepoModel
import retrofit2.Response


fun ImageView.setBackAction() {
    this.setOnClickListener { this.findNavController().navigateUp() }
}

fun SwipeRefreshLayout.onRefreshList(vModel: () -> Unit) {
    this.setOnRefreshListener {
        vModel()
        this.isRefreshing = false
    }
}

fun <T> MutableLiveData<ApiState<List<T>, String>>.clearList() {
    val list: ApiState<List<T>, String> = ApiState.Success(emptyList())
    this.value = list
}

fun Response<RepoModel>.toRepoMapperResponse(): Response<List<RepoItems>> {
    return if (this.isSuccessful) {
        Response.success(this.body()?.items, this.raw())
    } else {
        Response.error(this.errorBody(), this.raw())
    }
}

fun <T> Response<List<T>>.apiResponse(): ApiState<List<T>, String> {
    return if (this.isSuccessful) {
        val body = this.body() ?: emptyList()
        return if (body.isEmpty()) {
            ApiState.Error(LIST_EMPTY)
        } else {
            ApiState.Success(body)
        }
    } else {
        ApiState.Error(NO_DATA)
    }
}
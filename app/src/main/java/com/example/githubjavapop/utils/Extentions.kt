package com.example.githubjavapop.utils

import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.githubjavapop.data.model.ApiState


fun ImageView.setBackAction() {
    this.setOnClickListener { this.findNavController().navigateUp() }
}

fun SwipeRefreshLayout.onRefreshList(vModel: () -> Unit) {
    this.setOnRefreshListener {
        vModel()
        this.isRefreshing = false
    }
}

fun <T> MutableLiveData<ApiState<List<T>, String>>.clearList(){
    val list: ApiState<List<T>, String> = ApiState.Success(emptyList())
    this.value = list
}
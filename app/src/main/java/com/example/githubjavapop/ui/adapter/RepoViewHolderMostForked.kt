package com.example.githubjavapop.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.githubjavapop.data.model.retrofit.RepoItems
import com.example.githubjavapop.databinding.ItemsReposBinding

class RepoViewHolderMostForked(view: View,private val adapterManager: RepoAdapter.AdapterManager) : RecyclerView.ViewHolder(view) {

    private val binding = ItemsReposBinding.bind(view)

    fun initViewHolder(repo: RepoItems) = with(binding){

            txtRepoName.text = repo.repoName
            txtRepoDescription.text = repo.repoDescription
            txtRepoFork.text = repo.forks.toString()
            txtRepoStars.text = repo.stars.toString()
            txtRepoUsername.text = repo.repoOwner.ownerName

        repoContent.setOnClickListener {
            adapterManager.onClickListener(repo)
        }

        adapterManager.provideImageLoader().loadPicasso(repo.repoOwner.ownerAvatar, imgRepoUser)
    }
}
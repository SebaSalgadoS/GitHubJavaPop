package com.example.githubjavapop.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.githubjavapop.data.model.retrofit.RepoItems
import com.example.githubjavapop.databinding.ItemsReposBinding
import com.squareup.picasso.Picasso

class RepoViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding = ItemsReposBinding.bind(view)


    val txtRepoName = binding.txtRepoName
    val txtRepoDescription = binding.txtRepoDescription
    val txtRepoFork = binding.txtRepoFork
    val txtRepoStars = binding.txtRepoStars
    val txtRepoUsername = binding.txtRepoUsername
    val imgRepoUser = binding.imgRepoUser
    val repoContent = binding.repoContent


    fun initViewHolder(repo: RepoItems,onClickListener:(RepoItems) -> Unit) {

        txtRepoName.text = repo.repoName
        txtRepoDescription.text = repo.repoDescription
        txtRepoFork.text = repo.forks.toString()
        txtRepoStars.text = repo.stars.toString()
        txtRepoUsername.text = repo.repoOwner.ownerName

        repoContent.setOnClickListener {
            onClickListener(repo)
        }

        Picasso.get().load(repo.repoOwner.ownerAvatar).into(imgRepoUser)

    }
}
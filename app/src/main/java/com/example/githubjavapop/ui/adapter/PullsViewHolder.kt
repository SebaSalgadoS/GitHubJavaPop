package com.example.githubjavapop.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.githubjavapop.data.model.retrofit.PullsModel
import com.example.githubjavapop.databinding.ItemsPullsRepoBinding
import com.squareup.picasso.Picasso

class PullsViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = ItemsPullsRepoBinding.bind(view)
    private val imgPullUser = binding.imgPullUser
    private val pullsContent = binding.pullsContent

    fun initViewHolder(pulls: PullsModel, onClickListener:(PullsModel) -> Unit){

        with(binding){
            txtTittlePull.text = pulls.pullTitle
            txtBodyPull.text = pulls.pullBody
            txtBodyPull.text = pulls.pullDate
            txtPullUsername.text = pulls.pullOwner.ownerName
        }

        Picasso.get().load(pulls.pullOwner.ownerAvatar).into(imgPullUser)

        pullsContent.setOnClickListener {
            onClickListener(pulls)
        }
    }
}
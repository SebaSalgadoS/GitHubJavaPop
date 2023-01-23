package com.example.githubjavapop.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.githubjavapop.data.model.retrofit.PullsModel
import com.example.githubjavapop.databinding.ItemsPullsRepoBinding
import com.squareup.picasso.Picasso

class PullsViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val binding = ItemsPullsRepoBinding.bind(view)

    val txtTitlePulls = binding.txtTittlePull
    val txtBodyPulls = binding.txtBodyPull
    val txtDatePulls = binding.txtDatePull
    val txtPullUsername = binding.txtPullUsername
    val imgPullUser = binding.imgPullUser
    val pullsContent = binding.pullsContent

    fun initViewHolder(pulls: PullsModel, onClickListener:(PullsModel) -> Unit){

        txtTitlePulls.text = pulls.pullTitle
        txtBodyPulls.text = pulls.pullBody
        txtDatePulls.text = pulls.pullDate
        txtPullUsername.text = pulls.pullOwner.ownerName

        Picasso.get().load(pulls.pullOwner.ownerAvatar).into(imgPullUser)

        pullsContent.setOnClickListener {
            onClickListener(pulls)
        }
    }
}
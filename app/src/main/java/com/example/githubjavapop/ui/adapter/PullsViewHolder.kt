package com.example.githubjavapop.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.githubjavapop.data.model.retrofit.PullsModel
import com.example.githubjavapop.databinding.ItemsPullsRepoBinding
import com.squareup.picasso.Picasso

class PullsViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = ItemsPullsRepoBinding.bind(view)

    private val txtTitlePulls = binding.txtTittlePull
    private val txtBodyPulls = binding.txtBodyPull
    private val txtDatePulls = binding.txtDatePull
    private val txtPullUsername = binding.txtPullUsername
    private val imgPullUser = binding.imgPullUser
    private val pullsContent = binding.pullsContent

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
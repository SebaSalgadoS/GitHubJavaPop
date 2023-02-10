package com.example.githubjavapop.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.githubjavapop.data.model.retrofit.PullsModel
import com.example.githubjavapop.databinding.ItemsPullsOpenBinding
import com.squareup.picasso.Picasso

class PullsViewHolderClose(view: View, private val adapterManager: PullsAdapter.AdapterManager) :
    RecyclerView.ViewHolder(view) {

    private val binding = ItemsPullsOpenBinding.bind(view)

    fun initViewHolder(pulls: PullsModel, onClickListener: (PullsModel) -> Unit) = with(binding) {

        txtTittlePull.text = pulls.pullTitle
        txtBodyPull.text = pulls.pullBody
        txtDatePull.text = pulls.pullDate
        txtPullUsername.text = pulls.pullOwner.ownerName
        txtPullStatus.text = pulls.pullStatus.toUpperCase()

        adapterManager.provideImageLoader().loadPicasso(pulls.pullOwner.ownerAvatar, imgPullUser)

        pullsContent.setOnClickListener {
            onClickListener(pulls)
        }
    }
}
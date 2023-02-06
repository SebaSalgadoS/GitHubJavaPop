package com.example.githubjavapop.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubjavapop.R
import com.example.githubjavapop.data.model.retrofit.PullsModel

class PullsAdapter( private val onClickListener:(PullsModel) -> Unit): RecyclerView.Adapter<PullsViewHolder>() {

    val items : MutableList<PullsModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PullsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_pulls_repo, parent, false)
        return PullsViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: PullsViewHolder, position: Int) {
        val pulls = items.get(position)
        viewHolder.initViewHolder(pulls, onClickListener)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateAdapter(newItems: List<PullsModel>){
        items += newItems
        notifyDataSetChanged()
    }
}
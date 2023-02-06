package com.example.githubjavapop.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubjavapop.R
import com.example.githubjavapop.data.model.retrofit.RepoItems

class RepoAdapter( private val onClickListener:(RepoItems) -> Unit): RecyclerView.Adapter<RepoViewHolder>() {

    val items : MutableList<RepoItems> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_repos, parent, false)
        return RepoViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: RepoViewHolder, position: Int) {
        val repo = items.get(position)
        viewHolder.initViewHolder(repo, onClickListener)
    }
    override fun getItemCount(): Int {
        return items.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateAdapter(newItems: List<RepoItems>){
        items += newItems
        notifyDataSetChanged()
    }
}
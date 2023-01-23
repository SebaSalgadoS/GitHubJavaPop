package com.example.githubjavapop.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubjavapop.R
import com.example.githubjavapop.data.model.retrofit.RepoItems

class RepoAdapter(private val items: List<RepoItems>, private val onClickListener:(RepoItems) -> Unit): RecyclerView.Adapter<RepoViewHolder>() {


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
}
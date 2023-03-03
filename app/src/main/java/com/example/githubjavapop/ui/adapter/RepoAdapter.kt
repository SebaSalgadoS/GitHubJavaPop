package com.example.githubjavapop.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.githubjavapop.R
import com.example.githubjavapop.data.model.retrofit.RepoItems
import com.example.githubjavapop.utils.ImageLoader
import com.example.githubjavapop.utils.REPOADAPTER_MOST_FORKED
import com.example.githubjavapop.utils.REPOADAPTER_NORMAL

class RepoAdapter(private val adapterManager: AdapterManager): RecyclerView.Adapter<ViewHolder>() {

    private val items : MutableList<RepoItems> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewMostForked = LayoutInflater.from(parent.context).inflate(R.layout.items_repo_most_forked, parent, false)
        val viewNormal = LayoutInflater.from(parent.context).inflate(R.layout.items_repos, parent, false)
        return when(viewType){
            REPOADAPTER_MOST_FORKED ->{RepoViewHolderMostForked(viewMostForked, adapterManager)}
            REPOADAPTER_NORMAL -> {RepoViewHolder(viewNormal, adapterManager)}
            else ->{throw RuntimeException("ERROR REPOADAPTER VIEWHOLDER onCreateViewHolder")}
        }
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val repo = items[position]
        when(viewHolder){
            is RepoViewHolder ->{viewHolder.initViewHolder(repo = repo)}
            is RepoViewHolderMostForked ->{viewHolder.initViewHolder(repo = repo)}
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    //TODO sacar la logica al modelo
    override fun getItemViewType(position: Int): Int {
        val repo = items[position]
        return when{
            repo.forks <= 5000 -> {
                REPOADAPTER_NORMAL}
            repo.forks > 5000 -> {
                REPOADAPTER_MOST_FORKED
            }else ->{
                throw RuntimeException("ERROR REPOADAPTER VIEWHOLDER TYPES")
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateAdapter(newItems: List<RepoItems>){
        items += newItems
        notifyDataSetChanged()
    }

    interface AdapterManager {
        fun provideImageLoader(): ImageLoader
        fun onClickListener(item: RepoItems)
    }
}
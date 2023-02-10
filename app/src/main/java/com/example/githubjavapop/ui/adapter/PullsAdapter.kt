package com.example.githubjavapop.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.githubjavapop.R
import com.example.githubjavapop.data.model.retrofit.PullsModel
import com.example.githubjavapop.data.service.ImageLoader
import com.example.githubjavapop.utils.PULLADAPTER_CLOSE
import com.example.githubjavapop.utils.PULLADAPTER_OPEN

class PullsAdapter(private val adapterManager: AdapterManager, private val onClickListener:(PullsModel) -> Unit): RecyclerView.Adapter<ViewHolder>() {

    private val items : MutableList<PullsModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewOpen = LayoutInflater.from(parent.context).inflate(R.layout.items_pulls_open, parent, false)
        val viewClose = LayoutInflater.from(parent.context).inflate(R.layout.items_pulls_close, parent, false)
        return when(viewType){
            PULLADAPTER_OPEN ->{PullsViewHolder(viewOpen, adapterManager)}
            PULLADAPTER_CLOSE ->{PullsViewHolderClose(viewClose, adapterManager)}
            else ->{throw RuntimeException("ERROR PULLADAPTER VIEWHOLDER onCreateViewHolder")}
        }
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val pulls = items[position]
        when(viewHolder){
            is PullsViewHolder -> { viewHolder.initViewHolder(pulls = pulls, onClickListener = onClickListener)}
            is PullsViewHolderClose -> { viewHolder.initViewHolder(pulls = pulls, onClickListener = onClickListener)}
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        val pulls = items[position]
        return when (pulls.pullStatus) {
            "open" -> { PULLADAPTER_OPEN }
            "close" -> { PULLADAPTER_CLOSE }
            else -> { throw RuntimeException("ERROR PULLSADAPTER VIEWHOLDER TYPES") }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateAdapter(newItems: List<PullsModel>){
        items += newItems
        notifyDataSetChanged()
    }

    interface AdapterManager {
        fun provideImageLoader(): ImageLoader
    }
}
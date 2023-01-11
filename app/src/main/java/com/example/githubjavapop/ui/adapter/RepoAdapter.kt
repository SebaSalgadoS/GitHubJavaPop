package com.example.githubjavapop.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.githubjavapop.R
import com.example.githubjavapop.data.model.retrofit.Item
import com.example.githubjavapop.data.model.retrofit.RepoModel
import com.squareup.picasso.Picasso

class RepoAdapter(private val datos: List<Item>): RecyclerView.Adapter<RepoAdapter.ViewHolder>() {


    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val txtRepoName: TextView
        val txtRepoDescription: TextView
        val txtRepoFork: TextView
        val txtRepoStars: TextView
        val txtRepoUsername: TextView
        val imgRepoUser: ImageView
        val repoContent: CardView

        init{
            txtRepoName = view.findViewById(R.id.txtRepoName)
            txtRepoDescription = view.findViewById(R.id.txtRepoDescription)
            txtRepoFork = view.findViewById(R.id.txtRepoFork)
            txtRepoStars = view.findViewById(R.id.txtRepoStars)
            txtRepoUsername = view.findViewById(R.id.txtRepoUsername)
            imgRepoUser =view.findViewById(R.id.imgRepoUser)
            repoContent = view.findViewById(R.id.repoContent)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_repos, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repo = datos.get(position)

        holder.txtRepoName.text = repo.repoName
        holder.txtRepoDescription.text = repo.repoDescription
        holder.txtRepoFork.text = repo.forks.toString()
        holder.txtRepoStars.text = repo.stars.toString()
        holder.txtRepoUsername.text = repo.repoOwner.ownerName

        holder.repoContent.setOnClickListener {}

        Picasso.get().load(repo.repoOwner.ownerAvatar).into(holder.imgRepoUser)

    }

    override fun getItemCount(): Int {
        return datos.size
    }
}
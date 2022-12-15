package com.example.githubjavapop.data.model.retrofit

interface Repo {

    val idRepo: Int
    val repoName: String
    val repoDescription: String
    val forks: Int
    val Stars: Int
    val repoOwnerId: Int

}
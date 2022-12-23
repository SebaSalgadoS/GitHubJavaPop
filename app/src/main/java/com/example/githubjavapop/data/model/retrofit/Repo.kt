package com.example.githubjavapop.data.model.retrofit

// checkear como implementarlo con Juli y Gerardo
interface Repo {

    val idRepo: Int
    val repoName: String
    val repoDescription: String
    val forks: Int
    val Stars: Int
    val repoOwnerId: Int
    val repoOwner: UserOwner

}
package com.example.githubjavapop.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubjavapop.R
import com.example.githubjavapop.databinding.FragmentRepoViewBinding
import com.example.githubjavapop.ui.adapter.RepoAdapter
import com.example.githubjavapop.ui.viewmodel.FragmentRepoViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FragmentRepoView : Fragment() {

    private val repoViewModel: FragmentRepoViewModel by viewModels()

    private lateinit var binding: FragmentRepoViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_repo_view, container, false)


       // val recyclerView = binding.repoRecyclerView
       // recyclerView.layoutManager = LinearLayoutManager(this)


        repoViewModel.getAllRepos()


        repoViewModel.repositoryModel.observe(viewLifecycleOwner , Observer{
           // binding.repoRecyclerView.adapter = RepoAdapter(it) //recibe un List<Item>
            println("DATA FRAGMENTO " + repoViewModel.getAllRepos())
        })



    }


}
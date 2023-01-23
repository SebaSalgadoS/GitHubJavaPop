package com.example.githubjavapop.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubjavapop.data.model.retrofit.RepoItems
import com.example.githubjavapop.databinding.FragmentRepoViewBinding
import com.example.githubjavapop.ui.adapter.RepoAdapter
import com.example.githubjavapop.ui.viewmodel.FragmentRepoViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FragmentRepoView : Fragment() {

    private val repoViewModel: FragmentRepoViewModel by viewModels()

    private val binding by lazy { FragmentRepoViewBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = binding.repoRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)

        repoViewModel.getAllRepos()

        repoViewModel.repositoryModel.observe(viewLifecycleOwner, Observer {
            recyclerView.adapter = RepoAdapter(it) {repos -> onItemSelected(repos) }
            Log.e("DATA FRAGMENTO", it.toString())
        })

    }

    fun onItemSelected(repo: RepoItems){
        Toast.makeText(context, "CLICK CLICK CLICK!!", Toast.LENGTH_SHORT).show()
        val next_action = FragmentRepoViewDirections.actionFragmentRepoViewToFragmentPullsView()
        findNavController().navigate(next_action)

    }

}
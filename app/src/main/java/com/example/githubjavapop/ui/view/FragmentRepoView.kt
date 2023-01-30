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
import com.example.githubjavapop.data.model.ApiState
import com.example.githubjavapop.data.model.retrofit.RepoItems
import com.example.githubjavapop.databinding.FragmentRepoViewBinding
import com.example.githubjavapop.ui.adapter.RepoAdapter
import com.example.githubjavapop.ui.viewmodel.FragmentRepoViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FragmentRepoView : Fragment() {

    private val repoViewModel: FragmentRepoViewModel by viewModels()

    private val binding by lazy { FragmentRepoViewBinding.inflate(layoutInflater) }

    private val repoAdapter by lazy { RepoAdapter() { repos -> onItemSelected(repos) } }

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

        recyclerView.adapter = repoAdapter

        repoViewModel.repositoryModel.observe(viewLifecycleOwner, Observer {state ->

            when(state){
                is ApiState.Loading -> {
                    // mostrar cargando
                }
                is ApiState.Error -> {
                    Log.e("Error Fragmento", state.error)
                }
                is ApiState.Success -> {
                    repoAdapter.updateAdapter(state.value)
                }
            }

            Log.e("DATA FRAGMENTO", state.toString())
        })

    }

    fun onItemSelected(repo: RepoItems){
        Toast.makeText(context, "CLICK CLICK CLICK!!", Toast.LENGTH_SHORT).show()
        val next_action = FragmentRepoViewDirections.actionFragmentRepoViewToFragmentPullsView(
            repositoriesUser = repo.repoOwner.ownerName,
            repositoriesTitle = repo.repoName)

        findNavController().navigate(next_action)

        //findNavController().navigate(R.id.action_fragmentRepoView_to_fragmentPullsView, bundle)
    }

}
package com.example.githubjavapop.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubjavapop.data.model.ApiState
import com.example.githubjavapop.data.model.retrofit.PullsModel
import com.example.githubjavapop.databinding.FragmentPullsViewBinding
import com.example.githubjavapop.ui.adapter.PullsAdapter
import com.example.githubjavapop.ui.viewmodel.FragmentPullsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentPullsView : Fragment() {

    private val binding by lazy { FragmentPullsViewBinding.inflate(layoutInflater) }
    private val args: FragmentPullsViewArgs by navArgs()
    private val pullViewModel: FragmentPullsViewModel by viewModels()
    private val pullsAdapter by lazy { PullsAdapter() { pulls -> onItemSelected(pulls = pulls) } }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val recyclerView = binding.pullsRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)

        recyclerView.adapter = PullsAdapter(){ pulls ->
            onItemSelected(pulls) }

        pullViewModel.getAllPulls(user = args.repositoriesUser,repo = args.repositoriesTitle)
        pullViewModel.pullsRequestModel.observe(viewLifecycleOwner, Observer { state->
            when(state){
                is ApiState.Loading -> {
                    //TODO agregar elemento de carga
                }
                is ApiState.Error -> {
                    Log.e("ERROR FRAGMENTO PULLS", state.error)
                }
                is ApiState.Success -> {
                    pullsAdapter.updateAdapter(state.value)
                }
            }
        })

        binding.iconBack.setOnClickListener { findNavController().navigateUp() }
        binding.viewTittle.text = args.repositoriesTitle
    }

    private fun onItemSelected(pulls: PullsModel) {

    }

}
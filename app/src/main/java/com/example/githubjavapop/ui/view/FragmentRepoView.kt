package com.example.githubjavapop.ui.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.githubjavapop.data.model.ApiState
import com.example.githubjavapop.data.model.retrofit.RepoItems
import com.example.githubjavapop.utils.ImageLoader
import com.example.githubjavapop.databinding.FragmentRepoViewBinding
import com.example.githubjavapop.ui.adapter.RepoAdapter
import com.example.githubjavapop.ui.viewmodel.FragmentRepoViewModel
import com.example.githubjavapop.utils.ERROR_STATE
import com.example.githubjavapop.utils.LOADING_STATE
import com.example.githubjavapop.utils.SUCCESS_STATE
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class FragmentRepoView : Fragment() {

    @Inject
    lateinit var picasso: ImageLoader
    private val repoViewModel: FragmentRepoViewModel by viewModels()
    private val binding by lazy { FragmentRepoViewBinding.inflate(layoutInflater) }
    private val repoAdapter by lazy {
        RepoAdapter(adapterManager = RepoManager())
    }

    inner class RepoManager : RepoAdapter.AdapterManager {
        override fun provideImageLoader(): ImageLoader = picasso
        override fun onClickListener(item: RepoItems) {
            onItemSelected(item)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        context?.generateToast()
        initRecyclerView()
        initViewModel()
        initObserver()

    }

    private fun onItemSelected(repo: RepoItems) {
        val action = FragmentRepoViewDirections.actionFragmentRepoViewToFragmentPullsView(
            repositoriesUser = repo.repoOwner.ownerName,
            repositoriesTitle = repo.repoName
        )
        findNavController().navigate(action)
    }

    // extension de lifecycleowner
    fun <T, L : MutableLiveData<T>> LifecycleOwner.observe(liveData: L, body: (T) -> Unit) =
        liveData.observe(this, Observer(body))

    private fun handleState(state: ApiState<List<RepoItems>, String>) {
        when (state) {
            is ApiState.Loading -> {
                binding.viewFlipper.displayedChild = LOADING_STATE
            }
            is ApiState.Error -> {
                binding.viewFlipper.displayedChild = ERROR_STATE
                binding.txtError.text = state.error
            }
            is ApiState.Success -> {
                binding.viewFlipper.displayedChild = SUCCESS_STATE
                repoAdapter.updateAdapter(state.value)
            }
        }
    }

    private fun initObserver() {
        observe(repoViewModel.repositoryModel, this@FragmentRepoView::handleState)
    }

    private fun initViewModel() {
        repoViewModel.getAllRepos()
    }

    private fun initRecyclerView() {
        val recyclerView = binding.repoRecyclerView
        recyclerView.layoutManager
        recyclerView.adapter = repoAdapter
    }

    private fun Context.generateToast() {
        Toast.makeText(this, "Holaa :D", Toast.LENGTH_SHORT).show()
    }
}
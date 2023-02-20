package com.example.githubjavapop.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.githubjavapop.data.model.ApiState
import com.example.githubjavapop.data.model.retrofit.PullsModel
import com.example.githubjavapop.databinding.FragmentPullsViewBinding
import com.example.githubjavapop.ui.adapter.PullsAdapter
import com.example.githubjavapop.ui.viewmodel.FragmentPullsViewModel
import com.example.githubjavapop.utils.*
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FragmentPullsView : Fragment() {

    @Inject
    lateinit var picassoImage: ImageLoader
    private val binding by lazy { FragmentPullsViewBinding.inflate(layoutInflater) }
    private val args: FragmentPullsViewArgs by navArgs()
    private val pullViewModel: FragmentPullsViewModel by viewModels()
    private val pullsAdapter by lazy {
        PullsAdapter(adapterManager = PullsManager())
    }

    inner class PullsManager : PullsAdapter.AdapterManager {
        override fun provideImageLoader(): ImageLoader = picassoImage
        override fun onClickListener(item: PullsModel) {
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

        initRecyclerView()
        initVIewModel()
        initObserver()
        initToolBar()
        initSwipeRefresh()

    }

    private fun handleState(state: ApiState<List<PullsModel>, String>) {
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
                pullsAdapter.updateAdapter(state.value)
            }
        }
    }

    private fun <T, L : MutableLiveData<T>> LifecycleOwner.observe(liveData: L, body: (T) -> Unit) =
        liveData.observe(this, Observer(body))

    private fun onItemSelected(pulls: PullsModel) {
        val action = FragmentPullsViewDirections.actionFragmentPullsViewToFragmentPullsBrowser(
            urlPulls = pulls.urlPull,
            pullsTitle = pulls.pullTitle
        )
        findNavController().navigate(action)
    }

    private fun initToolBar() {
        binding.iconBack.setBackAction()
        //binding.iconBack.setOnClickListener { findNavController().navigateUp() }
        binding.viewTittle.text = args.repositoriesTitle
    }

    val bloque: (body: ApiState<List<PullsModel>, String>) -> Unit ={ state ->
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
                pullsAdapter.updateAdapter(state.value)
            }
        }
    }

    private fun initObserver() {
        observe(pullViewModel.pullsRequestModel, bloque)
    }

    private fun initRecyclerView() {
        val recyclerView = binding.pullsRecyclerView
        recyclerView.layoutManager
        recyclerView.adapter = pullsAdapter
    }

    private fun initVIewModel() {
        pullViewModel.getAllPulls(user = args.repositoriesUser, repo = args.repositoriesTitle)
    }

    private fun initSwipeRefresh() = with(binding){
        pullSwipeRefresh.onRefreshList{
            pullViewModel.pullsRequestModel.clearList()
            initVIewModel()
        }
    }

}
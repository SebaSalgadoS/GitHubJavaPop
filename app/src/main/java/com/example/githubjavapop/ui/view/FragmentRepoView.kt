package com.example.githubjavapop.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.githubjavapop.R

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class FragmentRepoView : Fragment() {

    // (2) hacer la logica de recycler con el adaptador
    // (3) crear el viewModel del fragment
    // (complementario) usar safeArg para pasar datos al sig fragment (depende del api)
    // (complementario) usar bundle para pasar valores
    //(4) implementar sealed class para menajar los errores

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
        return inflater.inflate(    R.layout.fragment_repo_view, container, false)
    }


}
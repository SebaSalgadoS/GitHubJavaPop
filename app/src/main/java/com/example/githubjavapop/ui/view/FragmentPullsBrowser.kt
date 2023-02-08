package com.example.githubjavapop.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.githubjavapop.databinding.FragmentPullsBrowserBinding

class FragmentPullsBrowser : Fragment() {

    private val binding by lazy { FragmentPullsBrowserBinding.inflate(layoutInflater) }
    private val args: FragmentPullsBrowserArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initWebView()
        initToolbar()

    }

    fun initToolbar(){
        setBackUpButton()
        initToolbarTitle()
    }

    private fun initWebView(){
        binding.myBrowser.loadUrl(args.urlPulls)
    }

    private fun initToolbarTitle(){
        binding.viewTittle.text = args.pullsTitle
    }

    private fun setBackUpButton(){
        binding.iconBack.setOnClickListener { findNavController().navigateUp() }
    }

}
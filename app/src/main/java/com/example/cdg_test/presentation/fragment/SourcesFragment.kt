package com.example.cdg_test.presentation.fragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.cdg_test.R

class SourcesFragment : Fragment() {

    companion object {
        fun newInstance() = SourcesFragment()
    }

    private lateinit var viewModel: SourcesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.sources_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SourcesViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
package com.samet.orderapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.samet.orderapp.R
import com.samet.orderapp.adapter.MainFragmentAdapter
import com.samet.orderapp.databinding.FragmentMainBinding
import com.samet.orderapp.ui.viewmodels.MainFragmentViewModel
import com.samet.orderapp.util.replace


class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel:MainFragmentViewModel
    private lateinit var mainFragmentAdapter: MainFragmentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:MainFragmentViewModel by viewModels()
        viewModel=tempViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding= FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.foodList.observe(viewLifecycleOwner, Observer {
            mainFragmentAdapter.differ.submitList(it.yemekler)
        })
        setupRecyclerView()



    }

    private fun setupRecyclerView() {
        mainFragmentAdapter = MainFragmentAdapter()
        binding.recyclerviewMain.apply {
        adapter=mainFragmentAdapter
        layoutManager= GridLayoutManager(requireContext(),2)
        }
    }


}
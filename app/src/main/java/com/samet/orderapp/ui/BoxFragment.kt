package com.samet.orderapp.ui

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.samet.orderapp.R
import com.samet.orderapp.adapter.BoxFragmentAdapter
import com.samet.orderapp.adapter.MainFragmentAdapter
import com.samet.orderapp.databinding.FragmentBoxBinding
import com.samet.orderapp.databinding.FragmentMainBinding
import com.samet.orderapp.model.SepetYemekler
import com.samet.orderapp.model.Yemekler
import com.samet.orderapp.ui.viewmodels.BoxFragmentViewModel
import com.samet.orderapp.ui.viewmodels.MainFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BoxFragment : Fragment() {

    private lateinit var binding: FragmentBoxBinding
    private lateinit var viewModel: BoxFragmentViewModel
    private lateinit var boxFragmentAdapter: BoxFragmentAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: BoxFragmentViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBoxBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        viewModel.getBoxFoods()
        viewModel.boxList.observe(viewLifecycleOwner, Observer {


            if (!it?.sepet_yemekler.isNullOrEmpty()) {
                boxFragmentAdapter.differ.submitList(it?.sepet_yemekler)
                binding.recyclerViewSepet.isVisible = true
                binding.textViewToplam.isVisible = true
                binding.textViewLastAmount.isVisible = true
                binding.textviewApproveToBoxButton.isVisible = true
                binding.noItemsBox.isVisible = false
                var totalAmountText = showTotalAmount(it!!.sepet_yemekler)
                binding.textViewLastAmount.text = totalAmountText.toString() + " " + "TL"
            }
        })
        viewModel.emptyBoxText.observe(viewLifecycleOwner) { emptyText ->
            binding.noItemsBox.text = emptyText
            binding.noItemsBox.isVisible = true
            binding.recyclerViewSepet.isVisible = false
            binding.textViewToplam.isVisible = false
            binding.textViewLastAmount.isVisible = false
            binding.textviewApproveToBoxButton.isVisible = false
        }

        binding.buttonOk.setOnClickListener {
            binding.cvEmpty.isVisible = false

        }
        binding.textviewApproveToBoxButton.setOnClickListener {
            binding.cvEmpty.isVisible = true
            binding.textViewLastAmount.isVisible = false
            viewModel.ok()
        }
    }

    private fun setupRecyclerView() {
        boxFragmentAdapter = BoxFragmentAdapter(viewModel)
        binding.recyclerViewSepet.apply {
            adapter = boxFragmentAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun showTotalAmount(list: List<SepetYemekler>): Double {
        var totalAmount = 0.0
        for (yemek in list) {
            totalAmount += yemek.yemek_fiyat.toDouble() * yemek.yemek_siparis_adet.toDouble()
        }
        return totalAmount
    }


}



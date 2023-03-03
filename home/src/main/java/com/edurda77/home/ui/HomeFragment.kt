package com.edurda77.home.ui

import android.R.layout
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.edurda77.domain.model.Category
import com.edurda77.domain.model.ElementFlashSale
import com.edurda77.domain.model.ElementLatest
import com.edurda77.domain.model.ElementSearch
import com.edurda77.home.R
import com.edurda77.home.databinding.FragmentHomeBinding
import com.edurda77.home.presentation.*
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.categories.value?.let { initCategoryRecyclerView(it) }
        viewModel.stateSearch.observe(viewLifecycleOwner) {state->
                when (state) {
                    is SearcheState.Success -> {
                        val result = state.resultSearch
                        ArrayAdapter(requireContext(), layout.simple_dropdown_item_1line, result).also {adapter->
                            binding.searchEv.setAdapter(adapter)
                        }
                    }
                    is SearcheState.Error -> {

                    }
                    is SearcheState.Empty -> {

                    }
                    is SearcheState.Loading -> {

                    }
                }
        }
        viewModel.state.observe(viewLifecycleOwner) {state->
            when (state) {
                is HomeFragmetnState.Success -> {
                    binding.latestRv.isVisible = true
                    binding.flashSalesRv.isVisible = true
                    initLatestRecyclerView(state.latest)
                    initFlashSaleRecyclerView(state.flashSales)
                }
                is HomeFragmetnState.Error -> {
                    binding.latestRv.isVisible = false
                    binding.flashSalesRv.isVisible = false
                }
                is HomeFragmetnState.Loading -> {
                    binding.latestRv.isVisible = false
                    binding.flashSalesRv.isVisible = false
                }
            }
        }

        binding.searchEv.doOnTextChanged { text, start, before, count ->
            viewModel.searchByChars(text.toString())
        }
    }

    private fun initFlashSaleRecyclerView(flashSales: List<ElementFlashSale>) {
        val recyclerView: RecyclerView = binding.flashSalesRv
        recyclerView.layoutManager = LinearLayoutManager(
            requireContext(), LinearLayoutManager.HORIZONTAL, false
        )
        val adapter = FlashSaleAdapter(flashSales)
        recyclerView.adapter = adapter
        (recyclerView.layoutManager as LinearLayoutManager).scrollToPosition((Integer.MAX_VALUE/2)-1)
    }

    private fun initLatestRecyclerView(latest: List<ElementLatest>) {
        val recyclerView: RecyclerView = binding.latestRv
        recyclerView.layoutManager = LinearLayoutManager(
            requireContext(), LinearLayoutManager.HORIZONTAL, false
        )
        val adapter = LatestAdapter(latest)
        recyclerView.adapter = adapter
        (recyclerView.layoutManager as LinearLayoutManager).scrollToPosition((Integer.MAX_VALUE/2)-1)
    }

    private fun initCategoryRecyclerView(data: List<Category>) {
        val recyclerView: RecyclerView = binding.categoryRv
        recyclerView.layoutManager = LinearLayoutManager(
            requireContext(), LinearLayoutManager.HORIZONTAL, false
        )
        val adapter = CategoryAdapter(data)
        recyclerView.adapter = adapter
        (recyclerView.layoutManager as LinearLayoutManager).scrollToPosition((Integer.MAX_VALUE/2)-3)
    }
}
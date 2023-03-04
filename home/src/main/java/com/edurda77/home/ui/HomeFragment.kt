package com.edurda77.home.ui

import android.R.layout
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.edurda77.domain.model.Category
import com.edurda77.domain.model.ElementFlashSale
import com.edurda77.domain.model.ElementLatest
import com.edurda77.domain.navigation.Action
import com.edurda77.domain.navigation.AppNavigation
import com.edurda77.domain.utils.brands
import com.edurda77.home.databinding.FragmentHomeBinding
import com.edurda77.home.presentation.*
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<HomeViewModel>()
    @Inject
    lateinit var coordinator: AppNavigation

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBrandRecyclerView(brands)
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

    private fun initBrandRecyclerView(brands: List<Int>) {
        val recyclerView: RecyclerView = binding.brandRv
        recyclerView.layoutManager = LinearLayoutManager(
            requireContext(), LinearLayoutManager.HORIZONTAL, false
        )
        val adapter = BrandAdapter(brands)
        recyclerView.adapter = adapter
        (recyclerView.layoutManager as LinearLayoutManager).scrollToPosition((Integer.MAX_VALUE/2)-1)
    }

    private fun initFlashSaleRecyclerView(flashSales: List<ElementFlashSale>) {
        val recyclerView: RecyclerView = binding.flashSalesRv
        recyclerView.layoutManager = LinearLayoutManager(
            requireContext(), LinearLayoutManager.HORIZONTAL, false
        )
        val stateClickListener: FlashSaleAdapter.OnStateClickListener =
            object : FlashSaleAdapter.OnStateClickListener {
                override fun onStateClick(item: ElementFlashSale, position: Int) {
                    coordinator.execute(Action.HomeToProduct, "")
                }
            }
        val adapter = FlashSaleAdapter(flashSales, stateClickListener)
        recyclerView.adapter = adapter
        //(recyclerView.layoutManager as LinearLayoutManager).scrollToPosition((Integer.MAX_VALUE/2)-1)
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
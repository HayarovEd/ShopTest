package com.edurda77.home.ui

import android.R.layout
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.edurda77.domain.model.Category
import com.edurda77.domain.model.ElementSearch
import com.edurda77.home.R
import com.edurda77.home.databinding.FragmentHomeBinding
import com.edurda77.home.presentation.CategoryAdapter
import com.edurda77.home.presentation.HomeViewModel
import com.edurda77.home.presentation.SearcheState
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
f
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
        binding.searchEv.doOnTextChanged { text, start, before, count ->
            viewModel.searchByChars(text.toString())
        }
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
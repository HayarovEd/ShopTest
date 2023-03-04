package com.edurda77.product.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.edurda77.domain.navigation.AppNavigation
import com.edurda77.product.databinding.FragmentProductBinding
import com.edurda77.product.presentation.BigPhotoAdapter
import com.edurda77.product.presentation.PhotoCarouselAdapter
import com.edurda77.product.presentation.ProductFragmentState
import com.edurda77.product.presentation.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProductFragment : Fragment() {
    private var _binding: FragmentProductBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<ProductViewModel>()

    @Inject
    lateinit var coordinator: AppNavigation
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ProductFragmentState.Success -> {
                    initSmallRecycler(
                        imageUrls = state.data.imageUrls
                    )
                    initBigRecycler(
                        imageUrls = state.data.imageUrls)
                }
                is ProductFragmentState.Error -> {

                }
                is ProductFragmentState.Loading -> {

                }
            }
        }
    }

    private fun initBigRecycler(imageUrls: List<String>) {
        val bigRecyclerView: RecyclerView = binding.photoRv
        bigRecyclerView.layoutManager = LinearLayoutManager(
            requireContext(), LinearLayoutManager.HORIZONTAL, false
        )
        val bigStateClickListener: BigPhotoAdapter.OnStateClickListener =
            object : BigPhotoAdapter.OnStateClickListener {
                override fun onStateClick(item: String, position: Int) {
                }
            }
        val bigAdapter = BigPhotoAdapter(imageUrls, bigStateClickListener)
        bigRecyclerView.adapter = bigAdapter

    }

    private fun initSmallRecycler(imageUrls: List<String>) {
        val recyclerView: RecyclerView = binding.photoVp
        recyclerView.layoutManager = LinearLayoutManager(
            requireContext(), LinearLayoutManager.HORIZONTAL, false
        )
        val stateClickListener: PhotoCarouselAdapter.OnStateClickListener =
            object : PhotoCarouselAdapter.OnStateClickListener {
                override fun onStateClick(item: String, position: Int) {
                }
            }
        val adapter = PhotoCarouselAdapter(imageUrls, stateClickListener)
        recyclerView.adapter = adapter
        //(recyclerView.layoutManager as LinearLayoutManager).scrollToPosition(viewModel)

    }

}
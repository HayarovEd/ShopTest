package com.edurda77.product.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.edurda77.domain.navigation.AppNavigation
import com.edurda77.domain.R
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
                        imageUrls = state.data.imageUrls,
                        position = state.position
                    )
                    initBigRecycler(
                        imageUrls = state.data.imageUrls,
                        position = state.position
                    )
                }
                is ProductFragmentState.Error -> {

                }
                is ProductFragmentState.Loading -> {

                }
            }
        }
    }

    private fun initBigRecycler(imageUrls: List<String>, position: Int) {
        val bigRecyclerView: RecyclerView = binding.photoRv
        bigRecyclerView.layoutManager = LinearLayoutManager(
            requireContext(), LinearLayoutManager.HORIZONTAL, false
        )
        val bigStateClickListener: BigPhotoAdapter.OnStateClickListener =
            object : BigPhotoAdapter.OnStateClickListener {
                override fun onStateClick(item: String, position: Int) {
                    viewModel.getPosition(position)
                }
            }
        val bigAdapter = BigPhotoAdapter(imageUrls, bigStateClickListener)
        bigRecyclerView.adapter = bigAdapter
        /* bigRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
             override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                 val position = (recyclerView.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()
                 viewModel.getPosition(position)
             }
         })*/
        (bigRecyclerView.layoutManager as LinearLayoutManager).scrollToPosition(position)
    }

    private fun initSmallRecycler(imageUrls: List<String>, position: Int) {
        val recyclerView: RecyclerView = binding.photoVp
        recyclerView.layoutManager = LinearLayoutManager(
            requireContext(), LinearLayoutManager.HORIZONTAL, false
        )
        val stateClickListener: PhotoCarouselAdapter.OnStateClickListener =
            object : PhotoCarouselAdapter.OnStateClickListener {
                override fun onStateClick(item: String, position: Int) {
                    viewModel.getPosition(position)
                }
            }
        val adapter = PhotoCarouselAdapter(imageUrls, stateClickListener)
        recyclerView.adapter = adapter

    }

}
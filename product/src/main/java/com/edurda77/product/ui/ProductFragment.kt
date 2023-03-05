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
import com.edurda77.domain.navigation.Action
import com.edurda77.product.databinding.FragmentProductBinding
import com.edurda77.product.presentation.BigPhotoAdapter
import com.edurda77.product.presentation.PhotoCarouselAdapter
import com.edurda77.product.presentation.ProductFragmentState
import com.edurda77.product.presentation.ProductViewModel
import com.mig35.carousellayoutmanager.CarouselLayoutManager
import com.mig35.carousellayoutmanager.CarouselZoomPostLayoutListener
import com.mig35.carousellayoutmanager.CenterScrollListener
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
        binding.leftIv.setOnClickListener {
            coordinator.execute(
                action = Action.ProductToHome,
                user = ""
            )
        }
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
        val layoutManager = LinearLayoutManager(
            requireContext(), LinearLayoutManager.HORIZONTAL, false
        )
        bigRecyclerView.layoutManager = layoutManager

        val bigAdapter = BigPhotoAdapter(imageUrls)
        bigRecyclerView.adapter = bigAdapter
        bigRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            var lastPosition = 0
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val currentVisibleItemPosition =
                    layoutManager.findFirstVisibleItemPosition()

                if (lastPosition != currentVisibleItemPosition && currentVisibleItemPosition != RecyclerView.NO_POSITION) {
                    lastPosition = currentVisibleItemPosition

                }
            }
        })
        (bigRecyclerView.layoutManager as LinearLayoutManager).scrollToPosition(position)
    }

    private fun initSmallRecycler(imageUrls: List<String>, position: Int) {
        val recyclerView: RecyclerView = binding.photoVp
        val layoutManager = CarouselLayoutManager( CarouselLayoutManager.HORIZONTAL)
        layoutManager.setPostLayoutListener(CarouselZoomPostLayoutListener())
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        val stateClickListener: PhotoCarouselAdapter.OnStateClickListener =
            object : PhotoCarouselAdapter.OnStateClickListener {
                override fun onStateClick(item: String, position: Int) {
                    viewModel.getPosition(position)
                    recyclerView.scrollToPosition(position)

                }
            }
        val adapter = PhotoCarouselAdapter(imageUrls, stateClickListener)
        recyclerView.adapter = adapter
        recyclerView.addOnScrollListener(CenterScrollListener())

    }



}
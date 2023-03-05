package com.edurda77.product.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.edurda77.domain.model.ProductDetail
import com.edurda77.domain.navigation.Action
import com.edurda77.domain.navigation.AppNavigation
import com.edurda77.product.databinding.FragmentProductBinding
import com.edurda77.product.presentation.*
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

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.leftIv.setOnClickListener {
            coordinator.execute(
                action = Action.ProductToHome,
                user = ""
            )
        }
        viewModel.count.observe(viewLifecycleOwner) {
            binding.countTv.text = it.toString()
        }
        viewModel.amount.observe(viewLifecycleOwner) {
            binding.amountsTv.text = "# $it"
        }
        binding.decrBt.setOnClickListener {
            viewModel.changeCount(-1)
        }
        binding.incrBt.setOnClickListener {
            viewModel.changeCount(1)
        }

        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ProductFragmentState.Success -> {
                    initSmallRecycler(
                        imageUrls = state.data.imageUrls
                    )
                    initBigRecycler(
                        imageUrls = state.data.imageUrls,
                        position = state.position
                    )
                    initData(state.data)
                }
                is ProductFragmentState.Error -> {

                }
                is ProductFragmentState.Loading -> {

                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initData(data: ProductDetail) {
        binding.nameTv.text = data.name
        binding.priceTv.text = "$ ${data.price}"
        binding.descriptionTv.text = data.description
        binding.rangTv.text = data.rating.toString()
        binding.reviewsTv.text = "(${data.numberOfReviews})"
        val recyclerView: RecyclerView = binding.colorRv
        recyclerView.layoutManager = LinearLayoutManager(
            requireContext(), LinearLayoutManager.HORIZONTAL, false
        )
        val stateClickListener: ColorAdapter.OnStateClickListener =
            object : ColorAdapter.OnStateClickListener {
                override fun onStateClick(item: String, position: Int) {
                    Toast.makeText(requireContext(), item, Toast.LENGTH_LONG).show()
                }
            }
        val adapter = ColorAdapter(data.colors, stateClickListener)
        recyclerView.adapter = adapter
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

    private fun initSmallRecycler(imageUrls: List<String>) {
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
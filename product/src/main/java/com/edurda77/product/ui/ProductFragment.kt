package com.edurda77.product.ui

import android.content.res.Resources
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.edurda77.domain.navigation.AppNavigation
import com.edurda77.product.R
import com.edurda77.product.databinding.FragmentProductBinding
import com.edurda77.product.presentation.PhotoCarouselAdapter
import com.edurda77.product.presentation.ProductFragmentState
import com.edurda77.product.presentation.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Math.abs
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
        viewModel.state.observe(viewLifecycleOwner) {state->
            when (state) {
                is ProductFragmentState.Success -> {
                    initPager(state.data.imageUrls)
                }
                is ProductFragmentState.Error -> {

                }
                is ProductFragmentState.Loading -> {

                }
            }
        }
    }

    private fun initPager(imageUrls: List<String>) {
        val recyclerView: RecyclerView = binding.photoVp
        recyclerView.layoutManager = LinearLayoutManager(
            requireContext(), LinearLayoutManager.HORIZONTAL, false
        )
        val adapter = PhotoCarouselAdapter(imageUrls)
        recyclerView.adapter = adapter
        /*adapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onChanged() {
                val sidePadding = (width / 2) - (getChildAt(0).width / 2)
                setPadding(sidePadding, 0, sidePadding, 0)
                scrollToPosition(0)
                addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)
                        onScrollChanged()
                    }
                })
            }
        })*/
    }

    /*private fun onScrollChanged() {
        post {
            (0 until childCount).forEach { position ->
                val child = getChildAt(position)
                val childCenterX = (child.left + child.right) / 2
                val scaleValue = getGaussianScale(childCenterX, 1f, 1f, 150.toDouble())
                child.scaleX = scaleValue
                child.scaleY = scaleValue
                colorView(child, scaleValue)
            }
        }
    }*/
}
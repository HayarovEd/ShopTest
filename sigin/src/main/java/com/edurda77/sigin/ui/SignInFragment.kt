package com.edurda77.sigin.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.edurda77.sigin.databinding.FragmentSignInBinding
import com.edurda77.sigin.presentation.SignInFragmentState
import com.edurda77.sigin.presentation.SignInFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : Fragment() {

    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<SignInFragmentViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is SignInFragmentState.Loading -> {
                }
                is SignInFragmentState.Success -> {
                    Toast.makeText(requireContext(), state.data, Toast.LENGTH_LONG).show()
                }
                is SignInFragmentState.Error -> {
                    Toast.makeText(requireContext(), state.error, Toast.LENGTH_LONG).show()
                }
                is SignInFragmentState.Empty -> {

                }
            }
        }
        binding.signInBt.setOnClickListener {
            viewModel.registerNewUser(
                userName = binding.firstNameEv.text.toString(),
                password = binding.lastNameEv.text.toString(),
                email = binding.emailEv.text.toString()
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
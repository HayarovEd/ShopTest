package com.edurda77.login.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.edurda77.domain.navigation.Action
import com.edurda77.domain.navigation.AppNavigation
import com.edurda77.login.R
import com.edurda77.login.databinding.FragmentLogInBinding
import com.edurda77.login.presentation.LogInViewModel
import com.edurda77.login.presentation.LoginFragmentState
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LogInFragment : Fragment() {
    private var _binding: FragmentLogInBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<LogInViewModel>()
    @Inject
    lateinit var coordinator: AppNavigation

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLogInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is LoginFragmentState.Loading -> {
                }
                is LoginFragmentState.Success -> {
                    Toast.makeText(requireContext(), state.data, Toast.LENGTH_LONG).show()
                    coordinator.execute(
                        Action.LogInToHome, state.user
                    )
                }
                is LoginFragmentState.Error -> {
                    Toast.makeText(requireContext(), state.error, Toast.LENGTH_LONG).show()
                }
                is LoginFragmentState.Empty -> {

                }
            }
        }
        binding.logInBt.setOnClickListener {
            viewModel.logInTo(
                userName = binding.loginEv.text.toString(),
                password = binding.passwordEv.text.toString()
            )
        }
    }

}
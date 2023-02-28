package com.edurda77.profile.ui

import android.app.Activity
import android.content.Intent
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore.Images.Media
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.edurda77.domain.navigation.Action
import com.edurda77.domain.navigation.AppNavigation
import com.edurda77.domain.utils.PICK_IMAGE_REQUEST
import com.edurda77.profile.databinding.FragmentProfileBinding
import com.edurda77.profile.presentation.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<ProfileViewModel>()
    @Inject
    lateinit var coordinator: AppNavigation

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.logOutTv.setOnClickListener {
            coordinator.execute(
                Action.ProfileToSignIn, ""
            )
        }
        binding.logOotIv.setOnClickListener {
            coordinator.execute(
                Action.ProfileToSignIn, ""
            )
        }

        binding.uploadBt.setOnClickListener{
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(
                Intent.createChooser(intent, "Select Picture"),
                PICK_IMAGE_REQUEST
            )

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1 && resultCode == Activity.RESULT_OK && data != null) {

            val selectedPhotoUri = data.data
            try {
                selectedPhotoUri?.let {
                    if(Build.VERSION.SDK_INT < 28) {
                        val bitmap = Media.getBitmap(
                            requireActivity().contentResolver,
                            selectedPhotoUri
                        )
                        binding.photoIv.setImageBitmap(bitmap)
                    } else {
                        val source = ImageDecoder.createSource(requireActivity().contentResolver, selectedPhotoUri)
                        val bitmap = ImageDecoder.decodeBitmap(source)
                        binding.photoIv.setImageBitmap(bitmap)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
package com.edurda77.profile.presentation

import android.content.Intent
import androidx.lifecycle.ViewModel

class ProfileViewModel : ViewModel() {


    fun loadImage() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
    }
}
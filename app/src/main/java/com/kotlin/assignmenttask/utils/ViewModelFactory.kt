package com.kotlin.circleapisdemo.utils

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kotlin.assignmenttask.repo.RepoistoryClass
import com.kotlin.assignmenttask.viewModel.ImageDetailsViewModel


class ViewModelFactory(
    val app: Application,
    val appRepository: RepoistoryClass
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(ImageDetailsViewModel::class.java)) {
            return ImageDetailsViewModel(app, appRepository) as T
        }

        throw IllegalArgumentException("Unknown class name")
    }

}
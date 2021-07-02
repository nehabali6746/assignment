package com.kotlin.assignmenttask.repo

import com.kotlin.circleapisdemo.retrofit.RetrofitBuilder

class RepoistoryClass() {

    /* repo */
    suspend fun getImagesList()= RetrofitBuilder.apiEndPoints.getImagesData()
}
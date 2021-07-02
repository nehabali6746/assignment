package com.kotlin.circleapisdemo.retrofit
import com.kotlin.finaltarget.data.image.ImageDetail
import retrofit2.Response
import retrofit2.http.GET


interface ApiEndPoints {

    /*end point of base Url */
    @GET("c4ab4c1c-9a55-4174-9ed2-cbbe0738eedf")
    suspend fun getImagesData():  Response<ImageDetail>

}
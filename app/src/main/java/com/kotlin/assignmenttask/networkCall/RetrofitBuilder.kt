package com.kotlin.circleapisdemo.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    /* base Url */
    private const val baseUrl ="https://run.mocky.io/v3/"

    /* network call to server */
    private fun getRetrofit(): Retrofit{
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BASIC


        val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()
        return Retrofit.Builder()
            .baseUrl(baseUrl)
                .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }
    val apiEndPoints: ApiEndPoints = getRetrofit().create(ApiEndPoints::class.java)


}
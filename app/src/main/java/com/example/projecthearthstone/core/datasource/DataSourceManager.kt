package com.example.projecthearthstone.core.datasource

import com.example.projecthearthstone.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DataSourceManager: DataSource {


    override val PARAM_HEADER_X_RAPID_API_KEY: String = "X-RapidAPI-Key"

    override val PARAM_HEADER_X_RAPID_API_HOST: String = "X-RapidAPI-Host"

    override fun <T> createService(
        url: HttpUrl,
        classType: Class<T>
    ): T = Retrofit.Builder()
            .baseUrl(url)
            .client(createOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
            .create(classType)

    private fun createOkHttpClient() = OkHttpClient.Builder().apply {
        addInterceptor(createGeneralInterceptor())
        addInterceptor(createHttpErrorInterceptor())
        addInterceptor(createLogInterceptor())
    }.build()

    private fun createGeneralInterceptor() = Interceptor { chain ->
        val originalRequest = chain.request()

        val builder = originalRequest.newBuilder().apply {
            header(PARAM_HEADER_X_RAPID_API_KEY, BuildConfig.X_RAPID_API_KEY)
            header(PARAM_HEADER_X_RAPID_API_HOST, BuildConfig.X_RAPID_API_HOST)
            method(originalRequest.method, originalRequest.body)
        }

        val newRequest = builder.build()
        chain.proceed(newRequest)
    }

    private fun createHttpErrorInterceptor() = Interceptor { chain ->
        val originalRequest = chain.request()
        val response = chain.proceed(originalRequest)
        response
    }

    private fun createLogInterceptor() = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }

}
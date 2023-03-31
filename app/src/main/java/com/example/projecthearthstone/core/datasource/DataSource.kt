package com.example.projecthearthstone.core.datasource

import okhttp3.HttpUrl

interface DataSource {

    val PARAM_HEADER_X_RAPID_API_KEY: String
    val PARAM_HEADER_X_RAPID_API_HOST: String
    val API_TIMEOUT: Long

    fun <T> createService(url: HttpUrl, classType: Class<T>): T

}
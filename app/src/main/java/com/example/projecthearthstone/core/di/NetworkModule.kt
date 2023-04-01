package com.example.projecthearthstone.core.di

import com.example.projecthearthstone.BuildConfig
import com.example.projecthearthstone.core.interceptor.HttpInterceptorImpl
import com.example.projecthearthstone.data.network.CardApi
import okhttp3.HttpUrl.Companion.toHttpUrl
import org.koin.core.qualifier.named
import org.koin.dsl.module

val networkModule = module {

    single(named(EndpointModule.NAMED_CARDS)) {
        get<HttpInterceptorImpl>().createService(
            BuildConfig.BASE_URL.toHttpUrl(),
            CardApi::class.java
        )
    }

}
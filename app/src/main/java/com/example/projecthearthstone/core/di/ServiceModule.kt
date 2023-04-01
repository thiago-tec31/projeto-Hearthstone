package com.example.projecthearthstone.core.di

import com.example.projecthearthstone.core.interceptor.HttpInterceptorImpl
import org.koin.dsl.bind
import org.koin.dsl.module
import javax.sql.DataSource

val serviceModule = module {
    single { HttpInterceptorImpl() } bind DataSource::class
}
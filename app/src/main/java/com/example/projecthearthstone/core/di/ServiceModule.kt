package com.example.projecthearthstone.core.di

import com.example.projecthearthstone.core.datasource.DataSourceManager
import org.koin.dsl.bind
import org.koin.dsl.module
import javax.sql.DataSource

val serviceModule = module {
    single { DataSourceManager() } bind DataSource::class
}
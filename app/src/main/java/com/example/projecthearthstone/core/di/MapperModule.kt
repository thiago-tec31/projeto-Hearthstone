package com.example.projecthearthstone.core.di

import com.example.projecthearthstone.data.mapper.InfoCardsMapper
import org.koin.dsl.module

val mapperModule = module {

    single { InfoCardsMapper() }


}
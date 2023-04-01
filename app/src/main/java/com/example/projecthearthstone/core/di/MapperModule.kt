package com.example.projecthearthstone.core.di

import com.example.projecthearthstone.data.mapper.FiguresCardsMapper
import com.example.projecthearthstone.data.mapper.InfoCardsMapper
import org.koin.dsl.module
import kotlin.math.sin

val mapperModule = module {
    single { InfoCardsMapper() }
    single { FiguresCardsMapper() }
}
package com.example.projecthearthstone.core.di

import com.example.projecthearthstone.domain.usecase.HomeHearthstoneUseCase
import com.example.projecthearthstone.domain.usecase.HomeHearthstoneUseCaseImpl
import org.koin.dsl.bind
import org.koin.dsl.module

val usecaseModule = module {
    factory { HomeHearthstoneUseCaseImpl(get()) } bind HomeHearthstoneUseCase::class
}
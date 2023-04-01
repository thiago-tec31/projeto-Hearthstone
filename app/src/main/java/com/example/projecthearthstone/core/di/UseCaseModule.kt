package com.example.projecthearthstone.core.di

import com.example.projecthearthstone.domain.usecase.HomeHearthstoneUseCase
import com.example.projecthearthstone.domain.usecase.HomeHearthstoneUseCaseImpl
import com.example.projecthearthstone.domain.usecase.InsiderHearthstoneUseCase
import com.example.projecthearthstone.domain.usecase.InsiderHearthstoneUseImpl
import org.koin.dsl.bind
import org.koin.dsl.module

val usecaseModule = module {
    factory { HomeHearthstoneUseCaseImpl(get()) } bind HomeHearthstoneUseCase::class
    factory { InsiderHearthstoneUseImpl(get()) } bind InsiderHearthstoneUseCase::class
}
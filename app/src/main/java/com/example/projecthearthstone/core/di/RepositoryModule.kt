package com.example.projecthearthstone.core.di

import com.example.projecthearthstone.data.repository.HomeHearthstoneRepositoryImpl
import com.example.projecthearthstone.domain.repository.HomeHearthstoneRepository
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {
    factory {
        HomeHearthstoneRepositoryImpl(get(named(EndpointModule.NAMED_CARDS_INFO)), get())
    } bind HomeHearthstoneRepository::class
}
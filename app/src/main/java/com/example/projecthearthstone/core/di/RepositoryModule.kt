package com.example.projecthearthstone.core.di

import com.example.projecthearthstone.data.repository.HomeHearthstoneRepositoryImpl
import com.example.projecthearthstone.data.repository.InsiderHearthstoneRepositoryImpl
import com.example.projecthearthstone.domain.repository.HomeHearthstoneRepository
import com.example.projecthearthstone.domain.repository.InsiderHearthstoneRepository
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {
    factory {
        HomeHearthstoneRepositoryImpl(get(named(EndpointModule.NAMED_CARDS)), get())
    } bind HomeHearthstoneRepository::class

    factory {
        InsiderHearthstoneRepositoryImpl(get(named(EndpointModule.NAMED_CARDS)), get())
    } bind InsiderHearthstoneRepository::class
}
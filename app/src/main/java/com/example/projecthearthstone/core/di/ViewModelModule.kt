package com.example.projecthearthstone.core.di

import com.example.projecthearthstone.presentation.home.HomeHearthstoneViewModel
import com.example.projecthearthstone.presentation.insider.InsiderHeartstoneViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeHearthstoneViewModel(get()) }
    viewModel { InsiderHeartstoneViewModel(get()) }
}
package com.example.projecthearthstone.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projecthearthstone.core.model.Resource
import com.example.projecthearthstone.domain.model.InfoCards
import com.example.projecthearthstone.domain.usecase.HomeHearthstoneUseCase
import kotlinx.coroutines.launch

class HomeHearthstoneViewModel(
    private val homeHearthstoneUseCase: HomeHearthstoneUseCase
): ViewModel() {

    private val _cards = MutableLiveData<InfoCards>()
    val cards: LiveData<InfoCards> = _cards

    fun getInfoCards() {
        viewModelScope.launch {
            val infoCards = (homeHearthstoneUseCase.getInfoCards() as Resource.Success).data
            _cards.value = infoCards
        }
    }
}
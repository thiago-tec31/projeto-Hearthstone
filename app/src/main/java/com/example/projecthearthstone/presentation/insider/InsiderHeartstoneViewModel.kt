package com.example.projecthearthstone.presentation.insider

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projecthearthstone.core.model.Resource
import com.example.projecthearthstone.domain.model.CardType
import com.example.projecthearthstone.domain.model.FigureCards
import com.example.projecthearthstone.domain.usecase.InsiderHearthstoneUseCase
import kotlinx.coroutines.launch

class InsiderHeartstoneViewModel(
    private val insiderHearthstoneUseCase: InsiderHearthstoneUseCase
): ViewModel() {

    private val _figures = MutableLiveData<MutableList<FigureCards>>()
    val figures: LiveData<MutableList<FigureCards>> = _figures

    fun getFigures(filter: CardType?, cardName: String?) {
        viewModelScope.launch {
            val figures = insiderHearthstoneUseCase.getFiguresAll(filter, cardName)
            if (figures is Resource.Success) {
                _figures.value = figures.data as MutableList
            } else {
                _figures.value = mutableListOf()
            }
        }
    }
}
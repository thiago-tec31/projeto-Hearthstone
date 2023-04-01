package com.example.projecthearthstone.domain.repository

import com.example.projecthearthstone.core.model.Resource
import com.example.projecthearthstone.domain.model.CardType
import com.example.projecthearthstone.domain.model.FigureCards

interface InsiderHearthstoneRepository{
    suspend fun getFiguresAll(filter: CardType, cardName: String): Resource<List<FigureCards>>
}
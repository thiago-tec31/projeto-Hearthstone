package com.example.projecthearthstone.data.mapper

import com.example.projecthearthstone.data.model.request.InfoCardsResponse
import com.example.projecthearthstone.domain.model.InfoCards

open class InfoCardsMapper {

    open fun map(infoCardsResponse: InfoCardsResponse): InfoCards =
        InfoCards(
            classes = infoCardsResponse.classes ?: arrayListOf(),
            types = infoCardsResponse.types ?: arrayListOf(),
            races = infoCardsResponse.races ?: arrayListOf()
        )
}
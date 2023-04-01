package com.example.projecthearthstone.data.mapper

import com.example.projecthearthstone.data.model.response.FigureCardsResponse
import com.example.projecthearthstone.domain.model.FigureCards

open class FiguresCardsMapper {

    open fun map(figureCardsResponse: List<FigureCardsResponse>): List<FigureCards> {
        return figureCardsResponse.flatMap { item ->
            map(item)
        }
    }

    private fun map(figureCardsResponse: FigureCardsResponse): List<FigureCards> {
        val figures = ArrayList<FigureCards>()
        figures.add(mapFigureCardsResponseToFigureCards(figureCardsResponse))
        return figures
    }

    private fun mapFigureCardsResponseToFigureCards(figureCardsResponse: FigureCardsResponse): FigureCards =
        FigureCards(figureCardsResponse.img ?: "")

}
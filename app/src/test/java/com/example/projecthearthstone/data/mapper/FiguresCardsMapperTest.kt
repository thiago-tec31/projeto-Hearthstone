package com.example.projecthearthstone.data.mapper

import com.example.projecthearthstone.data.model.response.FigureCardsResponse
import org.junit.Assert
import org.junit.Before
import org.junit.Test


class FiguresCardsMapperTest {

    private lateinit var figuresCardsMapper: FiguresCardsMapper


    @Before
    fun setup() {
        figuresCardsMapper = FiguresCardsMapper()
    }

    @Test
    fun testMap() {
        // Arrange
        val figureCardsResponse = FigureCardsResponse("img.jpg")

        // Act
        val result =  figuresCardsMapper.map(arrayListOf(figureCardsResponse))

        // Assert
        with(result) {
            Assert.assertEquals("img.jpg", this[0].img)
        }

    }
}
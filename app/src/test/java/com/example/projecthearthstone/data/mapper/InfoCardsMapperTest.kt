package com.example.projecthearthstone.data.mapper

import com.example.projecthearthstone.data.model.response.InfoCardsResponse
import com.example.projecthearthstone.presentation.Constants.classMage
import com.example.projecthearthstone.presentation.Constants.raceOrc
import com.example.projecthearthstone.presentation.Constants.typeDruid
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class InfoCardsMapperTest {

    private lateinit var infoCardsMapper: InfoCardsMapper


    @Before
    fun setup() {
        infoCardsMapper = InfoCardsMapper()
    }

    @Test
    fun testMap() {
        // Arrange
        val infoCardsResponse = InfoCardsResponse(
            classes = arrayListOf(classMage),
            types = arrayListOf(typeDruid),
            races = arrayListOf(raceOrc)
        )

        // Act
        val result =  infoCardsMapper.map(infoCardsResponse)

        // Assert
        with(result) {
            Assert.assertEquals(classMage, this.classes[0])
            Assert.assertEquals(typeDruid, this.types[0])
            Assert.assertEquals(raceOrc, this.races[0])
        }

    }
}
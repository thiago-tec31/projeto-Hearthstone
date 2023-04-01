package com.example.projecthearthstone.domain.model

import com.example.projecthearthstone.presentation.Constants.classMage
import com.example.projecthearthstone.presentation.Constants.raceOrc
import com.example.projecthearthstone.presentation.Constants.typeDruid
import org.junit.Assert
import org.junit.Before
import org.junit.Test


class ItemCardTest {

    private val names = arrayListOf(classMage, typeDruid, raceOrc)

    private lateinit var itemCard: ItemCard

    @Before
    fun setup() {
        itemCard = ItemCard(
            CardType.CLASSES,
            names
        )
    }

    @Test
    fun testItemCard() {
        // Assert
        with(itemCard) {
            Assert.assertEquals(CardType.CLASSES, this.title)
            Assert.assertEquals(classMage, this.names[0])
            Assert.assertEquals(typeDruid, this.names[1])
            Assert.assertEquals(raceOrc, this.names[2])
        }
    }
}
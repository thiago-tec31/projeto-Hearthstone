package com.example.projecthearthstone.domain.model

data class ItemCard(
    val title: CardType,
    val names: List<String>
)

enum class CardType {
    CLASSES, TYPES, RACES
}



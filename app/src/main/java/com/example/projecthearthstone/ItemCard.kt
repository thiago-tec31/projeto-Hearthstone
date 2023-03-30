package com.example.projecthearthstone

data class ItemCard(
    val title: CardType,
    val names: List<String>
)

enum class CardType {
    CLASSES, TYPES, RACES
}



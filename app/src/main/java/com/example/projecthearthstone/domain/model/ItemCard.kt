package com.example.projecthearthstone.domain.model

data class ItemCard(
    val title: CardType,
    val names: List<String>
)

enum class CardType(val type: String) {
    CLASSES("Classes"),
    TYPES("Types"),
    RACES("Races")
}



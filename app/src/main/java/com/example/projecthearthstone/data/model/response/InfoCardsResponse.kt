package com.example.projecthearthstone.data.model.response

data class InfoCardsResponse(
    val classes: ArrayList<String>? = arrayListOf(),
    val types: ArrayList<String>? = arrayListOf(),
    val races: ArrayList<String>? = arrayListOf(),
)
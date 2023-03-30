package com.example.projecthearthstone

import android.os.Bundle
import com.example.projecthearthstone.databinding.ActivityMainBinding

class HomeHearthstoneActivity: BaseActivity<ActivityMainBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configureLayoutClass()
        configureLayoutTypes()
        configureLayoutRaces()
    }

    private fun configureLayoutClass() {
        val itemCard = ItemCard(
            CardType.CLASSES,
            arrayListOf("Druid", "Hunter", "Mage")
        )
        binding.homeHearthstoneLayoutClass.also {
            it.setTextTitle(getString(R.string.class_name))
            it.updateCards(itemCard)
        }
    }

    private fun configureLayoutTypes() {
        binding.homeHearthstoneLayoutTypes.also {
            val itemCard = ItemCard(
                CardType.TYPES,
                arrayListOf("Druid", "Hunter", "Mage")
            )
            it.setTextTitle(getString(R.string.types))
            it.updateCards(itemCard)
        }
    }

    private fun configureLayoutRaces() {
        binding.homeHearthstoneLayoutRaces.also {
            val itemCard = ItemCard(
                CardType.RACES,
                arrayListOf("Druid", "Hunter", "Mage")
            )
            it.setTextTitle(getString(R.string.races))
            it.updateCards(itemCard)
        }
    }

    override fun getViewBinding(): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)
}
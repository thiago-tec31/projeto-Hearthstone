package com.example.projecthearthstone.presentation.home


import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.projecthearthstone.R
import com.example.projecthearthstone.core.bases.BaseFragment
import com.example.projecthearthstone.databinding.FragmentHomeHearthstoneBinding
import com.example.projecthearthstone.domain.model.CardType
import com.example.projecthearthstone.domain.model.ItemCard

class HomeHearthstoneFragment: BaseFragment<FragmentHomeHearthstoneBinding>(FragmentHomeHearthstoneBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
            it.setOnClickCardListener {
                findNavController().navigate(R.id.action_HomeHearthstoneFragment_to_InsiderHearthstoneFragment)
            }
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
            it.setOnClickCardListener {
                findNavController().navigate(R.id.action_HomeHearthstoneFragment_to_InsiderHearthstoneFragment)
            }
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
            it.setOnClickCardListener {
                findNavController().navigate(R.id.action_HomeHearthstoneFragment_to_InsiderHearthstoneFragment)
            }
        }
    }

}
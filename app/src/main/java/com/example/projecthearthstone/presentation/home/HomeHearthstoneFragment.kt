package com.example.projecthearthstone.presentation.home


import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.projecthearthstone.R
import com.example.projecthearthstone.core.bases.BaseFragment
import com.example.projecthearthstone.databinding.FragmentHomeHearthstoneBinding
import com.example.projecthearthstone.domain.model.CardType
import com.example.projecthearthstone.domain.model.ItemCard
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeHearthstoneFragment: BaseFragment<FragmentHomeHearthstoneBinding>(FragmentHomeHearthstoneBinding::inflate) {

    private val homeHearthstoneViewModel: HomeHearthstoneViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
        homeHearthstoneViewModel.getInfoCards()
    }

    private fun setObservers() {
        homeHearthstoneViewModel.cards.observe(requireActivity()) {
            configureLayoutClass(it.classes)
            configureLayoutTypes(it.types)
            configureLayoutRaces(it.races)
        }
    }

    private fun configureLayoutClass(classes: ArrayList<String>) {
        binding.homeHearthstoneLayoutClass.also {
            val itemCard = ItemCard(CardType.CLASSES, classes)
            it.setTextTitle(getString(R.string.class_name))
            it.updateCards(itemCard)
            it.setOnClickCardListener {
                findNavController().navigate(R.id.action_HomeHearthstoneFragment_to_InsiderHearthstoneFragment)
            }
        }
    }

    private fun configureLayoutTypes(types: ArrayList<String>) {
        binding.homeHearthstoneLayoutTypes.also {
            val itemCard = ItemCard(CardType.TYPES, types)
            it.setTextTitle(getString(R.string.types))
            it.updateCards(itemCard)
            it.setOnClickCardListener { findNavController().navigate(R.id.action_HomeHearthstoneFragment_to_InsiderHearthstoneFragment) }
        }
    }

    private fun configureLayoutRaces(races: ArrayList<String>) {
        binding.homeHearthstoneLayoutRaces.also {
            val itemCard = ItemCard(CardType.RACES, races)
            it.setTextTitle(getString(R.string.races))
            it.updateCards(itemCard)
            it.setOnClickCardListener {
                findNavController().navigate(R.id.action_HomeHearthstoneFragment_to_InsiderHearthstoneFragment)
            }
        }
    }

}
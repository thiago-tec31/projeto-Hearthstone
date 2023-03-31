package com.example.projecthearthstone.presentation.home


import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.projecthearthstone.R
import com.example.projecthearthstone.core.bases.BaseFragment
import com.example.projecthearthstone.core.util.hide
import com.example.projecthearthstone.core.util.show
import com.example.projecthearthstone.databinding.FragmentHomeHearthstoneBinding
import com.example.projecthearthstone.domain.model.CardType
import com.example.projecthearthstone.domain.model.ItemCard
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeHearthstoneFragment: BaseFragment<FragmentHomeHearthstoneBinding>(FragmentHomeHearthstoneBinding::inflate) {

    private val homeHearthstoneViewModel: HomeHearthstoneViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showLoading()
        setObservers()
        loadInfoCards()
    }

    private fun loadInfoCards() {
        homeHearthstoneViewModel.getInfoCards()
    }

    private fun showLoading() {
        binding.homeHearthstoneLayoutAnimation.show()
        binding.homeHearthstoneLottieAnimation.playAnimation()
        binding.homeHearthstoneLayoutError.hide()
        binding.homeHearthstoneScrollview.hide()
    }

    private fun setObservers() {
        homeHearthstoneViewModel.cards.observe(viewLifecycleOwner) {
            configureLayoutClass(it.classes)
            configureLayoutTypes(it.types)
            configureLayoutRaces(it.races)
            hideLoading()
        }

        homeHearthstoneViewModel.apiError.observe(viewLifecycleOwner) {
            showError()
            setTextError(it.status.type, it.message)
        }

    }

    private fun setTextError(titleError: String, subTitleError: String){
        binding.homeHearthstoneTitleErrorText.text = titleError
        binding.homeHearthstoneSubtitleErrorText.text = subTitleError
    }

    private fun hideLoading() {
        binding.homeHearthstoneLayoutAnimation.hide()
        binding.homeHearthstoneLottieAnimation.pauseAnimation()
        binding.homeHearthstoneLayoutError.hide()
        binding.homeHearthstoneScrollview.show()
    }

    private fun showError() {
        binding.homeHearthstoneLayoutAnimation.hide()
        binding.homeHearthstoneLottieAnimation.pauseAnimation()
        binding.homeHearthstoneLayoutError.show()
        binding.homeHearthstoneScrollview.hide()
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
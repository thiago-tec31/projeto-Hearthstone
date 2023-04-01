package com.example.projecthearthstone.presentation.insider

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.projecthearthstone.core.bases.BaseFragment
import com.example.projecthearthstone.core.util.hide
import com.example.projecthearthstone.core.util.show
import com.example.projecthearthstone.databinding.FragmentInsiderHearthstoneBinding
import com.example.projecthearthstone.domain.model.CardType
import org.koin.androidx.viewmodel.ext.android.viewModel

class InsiderHearthstoneFragment: BaseFragment<FragmentInsiderHearthstoneBinding>(FragmentInsiderHearthstoneBinding::inflate) {

    companion object {
        const val PARAM_TITLE = "PARAM_TITLE"
        const val PARAM_CARD_NAME = "PARAM_CARD_NAME"
    }

    private val figureAdapter by lazy { FigureAdapter(binding.root.context) }

    private val title by lazy { arguments?.getString(PARAM_TITLE) }

    private val cardName by lazy { arguments?.getString(PARAM_CARD_NAME) }

    private val insiderHeartstoneViewModel: InsiderHeartstoneViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showLoading()
        initView()
        setObservers()
        loadFigureCards()
    }

    private fun initView() {
        setTitle()
        configureRecyclerView()
    }

    private fun showLoading() {
        binding.insiderHearthstoneLottieAnimation.show()
        binding.insiderHearthstoneLottieAnimation.playAnimation()
        binding.insiderHearthstoneLayoutBody.hide()
    }

    private fun hideLoading() {
        binding.insiderHearthstoneLottieAnimation.hide()
        binding.insiderHearthstoneLottieAnimation.pauseAnimation()
        binding.insiderHearthstoneLayoutBody.show()
    }

    private fun setTitle() {
        binding.insiderHearthstoneText.text = title
    }

    private fun setObservers() {
        binding.insiderHearthstoneFloatingButton.setOnClickListener {
            findNavController().popBackStack()
        }

        insiderHeartstoneViewModel.figures.observe(viewLifecycleOwner) { figures ->
            val newFigures = figures.filter { it.img.isNotEmpty() }
            figureAdapter.updatesFigures(newFigures)
            hideLoading()
        }
    }

    private fun loadFigureCards() {
        val cardType = title?.let { CardType.valueOf(it.uppercase()) } ?: run { null }
        insiderHeartstoneViewModel.getFigures(cardType, cardName)
    }

    private fun configureRecyclerView() {
        binding.insiderHearthstoneRecyclerView.also {
            it.setEmptyView(binding.insiderHearthstoneLayoutEmpty)
            it.layoutManager = GridLayoutManager(it.context, 2, GridLayoutManager.VERTICAL, false)
            it.adapter = figureAdapter
        }
    }
}
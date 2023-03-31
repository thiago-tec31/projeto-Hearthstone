package com.example.projecthearthstone.presentation.insider

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.projecthearthstone.core.bases.BaseFragment
import com.example.projecthearthstone.databinding.FragmentInsiderHearthstoneBinding

class InsiderHearthstoneFragment: BaseFragment<FragmentInsiderHearthstoneBinding>(FragmentInsiderHearthstoneBinding::inflate) {

    private val figureAdapter by lazy {
        FigureAdapter(binding.root.context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureRecyclerView()
        binding.insiderHearthstoneFloatingButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun configureRecyclerView() {
        val list = mutableListOf<String>()
        binding.insiderHearthstoneRecyclerView.also {
            it.layoutManager = GridLayoutManager(it.context, 2, GridLayoutManager.VERTICAL, false)
            it.adapter = figureAdapter
        }

        for (i in 1..20) {
            val url = "https://d15f34w2p8l1cc.cloudfront.net/hearthstone/a322b4f93177189a87791c99dddef2da1ba6e9ab0d38b94bd59e62c44be9d81c.png"
            list.add(url)
        }

        figureAdapter.updatesFigures(list)
    }
}